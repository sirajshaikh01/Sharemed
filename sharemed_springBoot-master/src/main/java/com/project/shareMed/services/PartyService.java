package com.project.shareMed.services;

import java.util.Map;

import com.project.shareMed.entites.Party;


public interface PartyService {
	
	public Map<String, Object> createParty(Party party);
	
	public Map<String, Object> checkLogin(Party party);
	
	public Party getParty(long partyId);
	
}
