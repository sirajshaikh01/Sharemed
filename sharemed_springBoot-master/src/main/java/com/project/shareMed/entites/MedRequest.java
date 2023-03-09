package com.project.shareMed.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedRequest {
	@Id
	@GeneratedValue
	private long medRequestId;
	private String medicationType;
 	private String medicationName;
 	private int numberOfDoses;
 	private String dropAddress1;
 	private String dropAddress2;
 	private String state;
 	private String city;
 	private String requestStatus;
 	private int partyId;
 	private int productId;
 	private String prescriptionUrl;
 	
	public MedRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedRequest(long medRequestId, String medicationType, String medicationName, int numberOfDoses,
			String dropAddress1, String dropAddress2, String state, String city, String requestStatus, int partyId,
			int productId,String prescriptionUrl) {
		super();
		this.medRequestId = medRequestId;
		this.medicationType = medicationType;
		this.medicationName = medicationName;
		this.numberOfDoses = numberOfDoses;
		this.dropAddress1 = dropAddress1;
		this.dropAddress2 = dropAddress2;
		this.state = state;
		this.city = city;
		this.requestStatus = requestStatus;
		this.partyId = partyId;
		this.productId = productId;
		this.prescriptionUrl = prescriptionUrl;
	}

	public long getMedRequestId() {
		return medRequestId;
	}

	public void setMedRequestId(long medRequestId) {
		this.medRequestId = medRequestId;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public int getNumberOfDoses() {
		return numberOfDoses;
	}

	public void setNumberOfDoses(int numberOfDoses) {
		this.numberOfDoses = numberOfDoses;
	}

	public String getDropAddress1() {
		return dropAddress1;
	}

	public void setDropAddress1(String dropAddress1) {
		this.dropAddress1 = dropAddress1;
	}

	public String getDropAddress2() {
		return dropAddress2;
	}

	public void setDropAddress2(String dropAddress2) {
		this.dropAddress2 = dropAddress2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPrescriptionUrl() {
		return prescriptionUrl;
	}

	public void setPrescriptionUrl(String prescriptionUrl) {
		this.prescriptionUrl = prescriptionUrl;
	}

 	
 	
}
