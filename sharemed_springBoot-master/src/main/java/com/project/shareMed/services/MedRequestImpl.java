package com.project.shareMed.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.InventoryDao;
import com.project.shareMed.DAO.MedRequestDAO;
import com.project.shareMed.DAO.MedicationDAO;
import com.project.shareMed.DAO.PartyDAO;
import com.project.shareMed.DAO.ProductDao;
import com.project.shareMed.entites.Inventory;
import com.project.shareMed.entites.MedRequest;
import com.project.shareMed.entites.Medication;
import com.project.shareMed.entites.Party;
import com.project.shareMed.entites.Product;
import com.project.shareMed.model.EmailConfig;

@Service
public class MedRequestImpl implements MedRequestService {
	
	@Autowired
	MedRequestDAO medRequestDao;
	
	@Autowired
	PartyDAO partyDao;
	
	@Autowired
    ProductDao productDao;
	
	@Autowired
	InventoryDao inventoryDao;
	
	@Autowired
	MedicationDAO medicationDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	public EmailConfig emailConfig;
	
	@Override
	public MedRequest createMedRequest(MedRequest medRequest) {
		long partyId = medRequest.getPartyId();
		Party party = partyDao.findById(partyId);
		String email = party.getEmail();
		System.out.println("Email Id is " +email);
	
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailConfig.getUserName());
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Thank You for you Donation");
		simpleMailMessage.setText("We recived your request for "+medRequest.getNumberOfDoses()+" dose of "+medRequest.getMedicationName() +" medications.We will reach you shortly to deliver the medications");
		
		javaMailSender.send(simpleMailMessage);
		
		System.out.println("Mail Sent....");
		
		return medRequestDao.save(medRequest);
	}

	@Override
	public List<MedRequest> getPendingRequests() {
		List<MedRequest> requestList = medRequestDao.findAllByRequestStatus("Yet_To_Deliver");
		return requestList;
	}

	@Override
	public Map<String, Object> getMedReqDetailInfo(int medReqId) {
		Map<String, Object> medRequestDetailMap = new HashMap<String, Object>();
		MedRequest medRequest = medRequestDao.findByMedRequestId(Long.valueOf(medReqId));
		
		if(medRequest != null) {
			medRequestDetailMap.put("medRequest", medRequest);
			
			int productId = medRequest.getProductId();
			Product product = productDao.findByProductId(productId);
			if(product != null) {
				medRequestDetailMap.put("product", product);
			}
			
			int partyId = medRequest.getPartyId();
			Party party = partyDao.findById(partyId);
			if(party!= null) {
				medRequestDetailMap.put("party", party);
			}
			
		}
		return medRequestDetailMap;
	}

	@Override
	public MedRequest addFilePathToDb(String fileUrl, long medRequestId) {
		MedRequest medRequest = medRequestDao.findByMedRequestId(medRequestId);
		if(medRequest !=null) {
			fileUrl = fileUrl.substring(fileUrl.indexOf("\\upload_images")+1);
			fileUrl = fileUrl.replace("\\", "/");
			medRequest.setPrescriptionUrl(fileUrl);
			medRequestDao.save(medRequest);
		}
		return medRequest;
	}

	@Override
	public void updateMedRequets(long medRequestId) {
		// TODO Auto-generated method stub
		MedRequest medReq = medRequestDao.findByMedRequestId(medRequestId);
		if(medReq != null && !medReq.getRequestStatus().equals("Delivered")) {
			medReq.setRequestStatus("Delivered");
			medRequestDao.save(medReq);
			//update Inventory

			Medication medication = medicationDao.findBymedicationName(medReq.getMedicationName());
			Inventory inventory = inventoryDao.findByMedicineId(medication.getMedicationId());
			
			if(inventory != null) {
				int currentMedCount = inventory.getReadyToPromise();
				//check if its greater than 0
				if(currentMedCount > 0 ) {
					if(medReq.getNumberOfDoses() < currentMedCount) {
						inventory.setReadyToPromise(currentMedCount-medReq.getNumberOfDoses());
						inventoryDao.save(inventory);
					}
					
					//else Send Whatevere we have along with an email
					else {
						int currentCountInInv = inventory.getReadyToPromise();
						inventory.setReadyToPromise(0);
						inventoryDao.save(inventory);
						//send Email About MedCount Changes
						long partyId = medReq.getPartyId();
						Party party = partyDao.findById(partyId);
						String email = party.getEmail();
						System.out.println("Email Id is " +email);
						
						SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
						simpleMailMessage.setFrom(emailConfig.getUserName());
						simpleMailMessage.setTo(email);
						simpleMailMessage.setSubject("Sorry For Inconvience!");
						simpleMailMessage.setText("We Could Not Fullfill Your medication request for the Medication Name "+medReq.getMedicationName()+" for "+medReq.getNumberOfDoses()+" number of doses as we are falling shortage of medication ,currently we have "+currentCountInInv+" we are send it immediately");
						
						javaMailSender.send(simpleMailMessage);
						
						System.out.println("Mail Sent....");
					}
				}
			}
			
		}else {
			System.out.println("Already Delivered");
		}
		
	}

	@Override
	public List<Map<String, Object>> getPartyRequests(int partyId) {
		List<MedRequest> medReqList = medRequestDao.findAllByPartyId(partyId);
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		for(MedRequest medRequest : medReqList) {
			Map<String, Object> medRequestMap = new HashMap<String, Object>();
			medRequestMap.put("medRequest", medRequest);
			
			Product product = productDao.findByProductId(Long.valueOf(medRequest.getProductId()));
			if(product != null) {
				medRequestMap.put("productInfo",product);
			}
			
			returnList.add(medRequestMap);
		}
		
		return returnList;
	}
	
	
}
