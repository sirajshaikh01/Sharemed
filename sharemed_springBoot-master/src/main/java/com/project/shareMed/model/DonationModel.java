package com.project.shareMed.model;

public class DonationModel {

	private long donationId;
 	private String medicationType;
 	private String medicationName;
 	private int numberOfDoses;
 	private String pickUpAddress1;
 	private String pickUpAddress2;
 	private String state;
 	private String city;
 	private String donationStatus;
 	private int partyId;
 	
	public DonationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonationModel(long donationId, String medicationType, String medicationName, int numberOfDoses,
			String pickUpAddress1, String pickUpAddress2, String state, String city, String donationStatus,
			int partyId) {
		super();
		this.donationId = donationId;
		this.medicationType = medicationType;
		this.medicationName = medicationName;
		this.numberOfDoses = numberOfDoses;
		this.pickUpAddress1 = pickUpAddress1;
		this.pickUpAddress2 = pickUpAddress2;
		this.state = state;
		this.city = city;
		this.donationStatus = donationStatus;
		this.partyId = partyId;
	}

	public long getDonationId() {
		return donationId;
	}

	public void setDonationId(long donationId) {
		this.donationId = donationId;
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

	public String getPickUpAddress1() {
		return pickUpAddress1;
	}

	public void setPickUpAddress1(String pickUpAddress1) {
		this.pickUpAddress1 = pickUpAddress1;
	}

	public String getPickUpAddress2() {
		return pickUpAddress2;
	}

	public void setPickUpAddress2(String pickUpAddress2) {
		this.pickUpAddress2 = pickUpAddress2;
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

	public String getDonationStatus() {
		return donationStatus;
	}

	public void setDonationStatus(String donationStatus) {
		this.donationStatus = donationStatus;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}
 	
 	
}
