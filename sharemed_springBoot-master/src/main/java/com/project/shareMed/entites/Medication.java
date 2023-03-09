package com.project.shareMed.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Medication {
	
	@Id
	@GeneratedValue
	private long medicationId;
	private String medicationName;
	private String medicationType;
	private String medicationDescription;
	
	public Medication() {
		super();
	}

	public Medication(Long medicationId, String medicationName, String medicationType, String medicationDescription) {
		super();
		this.medicationId = medicationId;
		this.medicationName = medicationName;
		this.medicationType = medicationType;
		this.medicationDescription = medicationDescription;
	}

	public Long getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}

	public String getMedicationDescription() {
		return medicationDescription;
	}

	public void setMedicationDescription(String medicationDescription) {
		this.medicationDescription = medicationDescription;
	}
	
	
	
}
