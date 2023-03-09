package com.project.shareMed.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.shareMed.entites.MedRequest;

public interface MedRequestDAO extends JpaRepository<MedRequest, Long>{

	public List<MedRequest> findAllByRequestStatus(String requestStatus);
	public MedRequest findByMedRequestId(long medRequestIdd);
	public List<MedRequest> findAllByPartyId(int partyId);
}	
