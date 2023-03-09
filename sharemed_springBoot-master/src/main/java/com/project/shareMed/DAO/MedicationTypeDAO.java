package com.project.shareMed.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shareMed.entites.MedicationType;

public interface MedicationTypeDAO extends JpaRepository<MedicationType, Long>{

}
