package com.project.shareMed.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.shareMed.DAO.PartyDAO;
import com.project.shareMed.entites.MedRequest;
import com.project.shareMed.healper.FileUploadHelper;
import com.project.shareMed.model.EmailConfig;
import com.project.shareMed.services.MedRequestService;

@RestController
public class MedRequestController {
	
	@Autowired
	private MedRequestService medRequestService;
	
	@Autowired
	PartyDAO partyDao;
	
	public EmailConfig emailConfig;
	
	@Autowired
	private FileUploadHelper fileUploadHealper;
	
	@PostMapping(path="/addMedRequest",consumes="application/json")
	public MedRequest addMedRequest(@RequestBody MedRequest medRequest) {
		return  this.medRequestService.createMedRequest(medRequest);
	}
	
	@GetMapping("/getPendingRequest")
	public List<MedRequest> getNotRecivedDonation(){
		return medRequestService.getPendingRequests();
	}
	
	@GetMapping("/getMedReqInfo/{medRequestId}")
	public Map<String, Object> getDonationInfo(@PathVariable String medRequestId){
		return medRequestService.getMedReqDetailInfo(Integer.parseInt(medRequestId));
	}
	
	@PostMapping("/updateMedRequet/{medRequestId}")
	public void updateMedRequet(@PathVariable String medRequestId) {
		medRequestService.updateMedRequets(Long.parseLong(medRequestId));
	}
	
	@PostMapping("/uploadPrescription/{medRequestId}")
	public ResponseEntity<HttpStatus> uploadPrescription(@RequestParam("file") MultipartFile file,@PathVariable String medRequestId){
		try {
			if(file.isEmpty()) {
				return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else {
				String uploadFilePath = fileUploadHealper.uploadPrescription(file);
				System.out.println("uploqade "+uploadFilePath);
				if(uploadFilePath != null) {
					medRequestService.addFilePathToDb(uploadFilePath, Long.parseLong(medRequestId));
					return new ResponseEntity<HttpStatus>(HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		
		return null;
		
	}
	
	@GetMapping("/getPartyRequest/{partyId}")
	public List<Map<String, Object>> getPartyRequest(@PathVariable String partyId){
		return medRequestService.getPartyRequests(Integer.parseInt(partyId));
	}
}
