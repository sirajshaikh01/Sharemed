package com.project.shareMed.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventory {
	
	@Id
	@GeneratedValue
	private long id;
	private long medicineId;
	private int readyToPromise;
	private String medicationName;
	
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(long id, long medicineId, int readyToPromise ,String medicationName) {
		super();
		this.id = id;
		this.medicineId = medicineId;
		this.readyToPromise = readyToPromise;
		this.medicationName = medicationName;
	}

	
	public Inventory(long medicineId, int readyToPromise, String medicationName) {
		super();
		this.medicineId = medicineId;
		this.readyToPromise = readyToPromise;
		this.medicationName = medicationName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}

	public int getReadyToPromise() {
		return readyToPromise;
	}

	public void setReadyToPromise(int readyToPromise) {
		this.readyToPromise = readyToPromise;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	
	
	
	
}
