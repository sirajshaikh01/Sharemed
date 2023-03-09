package com.project.shareMed.services;

import java.util.List;
import java.util.Map;

import com.project.shareMed.entites.Donation;

public interface DonationService {
	public Donation createDonationRequest(Donation donation);
	
	public int medicationShareCount(long partyId);
	
	public List<Map<String, Object>> getPartyDonations(long partyId);
	
	public List<Donation>  getNotRecivedDonation();
	
	public Map<String, Object> getDonationDetailInfo(int donationId);
	
	public void updateDonation(Donation donation);
	
}
