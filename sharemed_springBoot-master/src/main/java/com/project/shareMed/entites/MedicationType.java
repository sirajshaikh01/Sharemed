package com.project.shareMed.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationType {

	@Id
	@GeneratedValue
	private long medicationId;
	private String medicationType;
	private String medicationDesc;
	
	
	public MedicationType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MedicationType(long medicationId, String medicationType, String medicationDesc) {
		super();
		this.medicationId = medicationId;
		this.medicationType = medicationType;
		this.medicationDesc = medicationDesc;
	}


	public long getMedicationId() {
		return medicationId;
	}


	public void setMedicationId(long medicationId) {
		this.medicationId = medicationId;
	}


	public String getMedicationType() {
		return medicationType;
	}


	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}


	public String getMedicationDesc() {
		return medicationDesc;
	}


	public void setMedicationDesc(String medicationDesc) {
		this.medicationDesc = medicationDesc;
	}
	
	
	
}
