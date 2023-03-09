package com.project.shareMed.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.project.shareMed.DAO.DonationDao;
import com.project.shareMed.DAO.InventoryDao;
import com.project.shareMed.DAO.MedicationDAO;
import com.project.shareMed.DAO.PartyDAO;
import com.project.shareMed.DAO.ProductDao;
import com.project.shareMed.entites.Donation;
import com.project.shareMed.entites.Inventory;
import com.project.shareMed.entites.Medication;
import com.project.shareMed.entites.Party;
import com.project.shareMed.entites.Product;
import com.project.shareMed.model.EmailConfig;

@Service
public class DonationImpl implements DonationService {
	
	@Autowired
	DonationDao donationDao;
	
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
	
	public DonationImpl(EmailConfig emailConfig) {
		super();
		this.emailConfig = emailConfig;
	}
	
	@Override
	public Donation createDonationRequest(Donation donation) {
		long partyId = donation.getPartyId();
		Party party = partyDao.findById(partyId);
		String email = party.getEmail();
		System.out.println("Email Id is " +email);
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailConfig.getUserName());
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Thank You for you Donation");
		simpleMailMessage.setText("We recived your request to donate "+donation.getNumberOfDoses()+" dose of "+donation.getMedicationName() +" medications.We will reach you shortly to collect the medications");
		
		javaMailSender.send(simpleMailMessage);
		
		System.out.println("Mail Sent....");
		
		
		return donationDao.save(donation);
	}
	
	@Override
	public void updateDonation(Donation donation) {
		long donationId = donation.getDonationId();
		String donationStatus = donation.getDonationStatus();
		
		Donation matchedDonation  = donationDao.findByDonationId(donationId);
		if(!matchedDonation.getDonationStatus().equals("recived")) {
			matchedDonation.setDonationStatus(donationStatus);
			donationDao.save(matchedDonation);
			
			//update Inventory
			Medication medication = medicationDao.findBymedicationName(matchedDonation.getMedicationName());
			
			Inventory inventory = inventoryDao.findByMedicineId(medication.getMedicationId());
			
			if(inventory == null) {
				Inventory inven = new Inventory(medication.getMedicationId(), matchedDonation.getNumberOfDoses(), matchedDonation.getMedicationName());
				inventoryDao.save(inven);
			}else {
				int currentMedCount = inventory.getReadyToPromise();
				inventory.setReadyToPromise(currentMedCount+matchedDonation.getNumberOfDoses());
				inventoryDao.save(inventory);
			}
		}else {
			System.out.println("Already Status Changed");
		}

		
	}

	@Override
	public int medicationShareCount(long partyId) {
		List<Donation> donationList = donationDao.findAllByPartyIdAndDonationStatus((int)partyId,"recived");
		int totalCount = 0;
		for(Donation donation : donationList) {
			totalCount = totalCount+donation.getNumberOfDoses();
		}
		return totalCount;
	}

	@Override
	public List<Map<String, Object>> getPartyDonations(long partyId) {
		List<Donation> donationList = donationDao.findAllByPartyId(((int)partyId));
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		for(Donation donation : donationList) {
			Map<String, Object> donationMap = new HashMap<String, Object>();
			donationMap.put("donationId", donation.getDonationId());
			donationMap.put("medicationType", donation.getMedicationType());
			donationMap.put("medicationName", donation.getMedicationName());
			donationMap.put("numberOfDoses", donation.getNumberOfDoses());
			donationMap.put("pickUpAddress1", donation.getPickUpAddress1());
			donationMap.put("pickUpAddress2", donation.getPickUpAddress2());
			donationMap.put("state", donation.getState());
			donationMap.put("city", donation.getCity());
			donationMap.put("donationStatus", donation.getDonationStatus());
			donationMap.put("partyId", donation.getPartyId());
			donationMap.put("productId", donation.getProductId());
			
			Product product = productDao.findByProductId(Long.valueOf(donation.getProductId()));
			if(product != null) {
				donationMap.put("productInfo",product);
			}
			
			returnList.add(donationMap);
		}
		return returnList;
	}

	@Override
	public List<Donation> getNotRecivedDonation() {
		List<Donation> donationList = donationDao.findAllByDonationStatus("not_recived");
		return donationList;
	}

	@Override
	public Map<String, Object> getDonationDetailInfo(int donationId) {
		Map<String, Object> donationDetailMap = new HashMap<String, Object>();
		Donation donation = donationDao.findByDonationId(Long.valueOf(donationId));
		if(donation != null) {
			donationDetailMap.put("donation", donation);
			
			int productId = donation.getProductId();
			Product product = productDao.findByProductId(productId);
			if(product != null) {
				donationDetailMap.put("product", product);
			}
			
			int partyId = donation.getPartyId();
			Party party = partyDao.findById(partyId);
			if(party!= null) {
				donationDetailMap.put("party", party);
			}
		}
		return donationDetailMap;
	}
	
}
