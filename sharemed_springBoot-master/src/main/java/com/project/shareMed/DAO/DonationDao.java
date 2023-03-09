package com.project.shareMed.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shareMed.entites.Donation;

public interface DonationDao extends JpaRepository<Donation, Long>{
	
	public List<Donation> findAllByPartyIdAndDonationStatus(int partyId,String donationStatus);
	public List<Donation> findAllByDonationStatus(String donationStatus);
	public List<Donation> findAllByPartyId(int partyId);
	public Donation findByDonationId(long donationId);
}
