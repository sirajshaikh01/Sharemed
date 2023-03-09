package com.project.shareMed.services;

import java.util.List;
import java.util.Map;

import com.project.shareMed.entites.Medication;

public interface MedicationService {
	
	public List<Medication> getMedicationByType(String medicationType);
	public Medication createMedication(Medication medication);
	public List<Medication> getMedUsingProdId(int productId);
}
