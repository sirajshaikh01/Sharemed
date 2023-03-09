package com.project.shareMed.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.shareMed.entites.Medication;

public interface MedicationDAO extends JpaRepository<Medication, Long> {
	
	public List<Medication> findAllByMedicationType(String medicationType);
	public Medication findBymedicationName(String medicationName);
	
}
