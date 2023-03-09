package com.project.shareMed.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.shareMed.entites.Donation;
import com.project.shareMed.entites.Inventory;
import com.project.shareMed.entites.Party;
import com.project.shareMed.entites.Product;
import com.project.shareMed.healper.FileUploadHelper;
import com.project.shareMed.model.EmailConfig;
import com.project.shareMed.services.DonationService;
import com.project.shareMed.services.InventoryService;
import com.project.shareMed.services.PartyService;
import com.project.shareMed.services.ProductService;

@RestController
public class Controller {
	
	public EmailConfig emailConfig;
	public Controller(EmailConfig emailConfig) {
		this.emailConfig=emailConfig;
	}
	
	@Autowired
	private FileUploadHelper fileUploadHealper;
		
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private ProductService productServive;
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private InventoryService inventoryService;
	@GetMapping("/")
	public Map<String, Object> home() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("response", "Connected Successfully");
		return returnMap;
	}
	
	@PostMapping(path="/addUser",consumes="application/json")
	public Map<String, Object> addUser(@RequestBody Party party) {
		return (Map<String, Object>) this.partyService.createParty(party);
	}
	
	@GetMapping("/getParty/{partyId}")
	public Party getParty(@PathVariable String partyId) {
		return partyService.getParty(Long.parseLong(partyId));
	}
	
	@PostMapping("/checkLogin")
	public Map<String, Object> checkLogin(@RequestBody Party party){
		return  partyService.checkLogin(party);
	}
	
	@GetMapping("/getProductList")
	public List<Product> getProductList() {
		return productServive.getProductList();
	}
	
	@GetMapping("/getProduct/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return productServive.getProduct(Long.parseLong(productId));
	}
	
	@PostMapping(path="/addDonation",consumes="application/json")
	public Donation addDonation(@RequestBody Donation donation) {
		return  this.donationService.createDonationRequest(donation);
	}
	@GetMapping("/getNotRecivedDonation")
	public List<Donation> getNotRecivedDonation(){
		return donationService.getNotRecivedDonation();
	}
	
	@GetMapping("/getPartyDonationList/{partyId}")
	public List<Map<String, Object>> getPartyDonationList(@PathVariable String partyId) {
		return donationService.getPartyDonations(Long.parseLong(partyId));
	}
	
	@GetMapping("/getDonationDetailInfo/{donationId}")
	public Map<String, Object> getDonationInfo(@PathVariable String donationId){
		return donationService.getDonationDetailInfo(Integer.parseInt(donationId));
	}
	
	@PutMapping("/updateDonation")
	public ResponseEntity<HttpStatus> updateDonation(@RequestBody Donation donation){
		donationService.updateDonation(donation);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("/getMedicationCharedCount/{partyId}")
	public Map<String, Object> getMedicationCharedCount(@PathVariable String partyId) {
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		returnMap.put("totalMedicationDonated", donationService.medicationShareCount(Long.parseLong(partyId)));
		return returnMap;
	}
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		try {
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
		}
		if(file.getContentType().equals("image/jpeg")) {
			boolean f = fileUploadHealper.uploadFile(file);
			if(f) {
				ResponseEntity.ok("File is Successfully uploaded");
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("Working");
	}
	
	@GetMapping("/getInventoryInfo")
	public List<Inventory> getInventoryInfo(){
		return inventoryService.getInventoryInfo();
	}
	
}
