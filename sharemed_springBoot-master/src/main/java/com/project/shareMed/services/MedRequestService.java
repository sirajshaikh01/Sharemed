package com.project.shareMed.services;

import java.util.List;
import java.util.Map;

import com.project.shareMed.entites.MedRequest;

public interface MedRequestService {
	public MedRequest createMedRequest(MedRequest medRequest);
	public List<MedRequest> getPendingRequests();
	public Map<String, Object> getMedReqDetailInfo(int medReqId);
	
	public MedRequest addFilePathToDb(String fileUrl,long medRequestId);
	
	public void updateMedRequets(long medRequestId);
	
	public List<Map<String, Object>> getPartyRequests(int partyId);
	
}
