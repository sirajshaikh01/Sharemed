package com.project.shareMed.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.PartyDAO;
import com.project.shareMed.entites.Party;
import com.project.shareMed.model.EmailConfig;

@Service
public class PartyImpl implements PartyService{
	
	@Autowired
	PartyDAO partyDao;
	
	@Autowired
	EmailConfig emailConfig;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public Map<String, Object> createParty(Party party) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//check if user exist
		List<Party> singlePartyRecord = partyDao.findAllByEmail(party.getEmail());
		System.out.println("Single PartyRecord "+singlePartyRecord);
		if(singlePartyRecord.size() == 0) {
			returnMap.put("isUserExit", false);
			Party createParty = partyDao.save(party);
			System.out.println(createParty);
			returnMap.put("party", partyDao.save(party));
			
			//Send Email
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(emailConfig.getUserName());
			simpleMailMessage.setTo(party.getEmail());
			simpleMailMessage.setSubject("Registration");
			simpleMailMessage.setText("Thank You For Registring with Share Med");
			
			javaMailSender.send(simpleMailMessage);
			
			System.out.println("Mail Sent....");
		}else {
			returnMap.put("isUserExit", true);
		}
		return returnMap;
	}

	@Override
	public Map<String, Object> checkLogin(Party party) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Party singlePartyRecord = partyDao.findByEmailAndPassword(party.getEmail(), party.getPassword());
		
		if(singlePartyRecord != null) {
			returnMap.put("isValidUser", true);
			returnMap.put("partyId", singlePartyRecord.getId());
			returnMap.put("partyType", singlePartyRecord.getPartyType());
		}else {
			returnMap.put("isValidUser", false);
		}
		return returnMap;

	}

	@Override
	public Party getParty(long partyId) {
		Party p = null;
		for(Party party : partyDao.findAll()) {
			if(party.getId() == (int)partyId) {
				p = party;
			}
		}
		return p;
	}

}
