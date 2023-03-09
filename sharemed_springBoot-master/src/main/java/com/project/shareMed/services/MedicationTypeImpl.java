package com.project.shareMed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.MedicationTypeDAO;
import com.project.shareMed.entites.MedicationType;

@Service
public class MedicationTypeImpl implements MedicationTypeService{
	
	@Autowired
	MedicationTypeDAO  medicationTypeDAO ;

	@Override
	public List<MedicationType> getMedicationtTypeList() {
		return medicationTypeDAO.findAll();
	}
}
