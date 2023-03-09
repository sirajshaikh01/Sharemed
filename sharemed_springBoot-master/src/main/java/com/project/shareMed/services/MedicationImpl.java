package com.project.shareMed.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.MedicationDAO;
import com.project.shareMed.DAO.ProductDao;
import com.project.shareMed.entites.Donation;
import com.project.shareMed.entites.MedRequest;
import com.project.shareMed.entites.Medication;
import com.project.shareMed.entites.Product;

@Service
public class MedicationImpl implements MedicationService {
	
	@Autowired
	MedicationDAO medicationDao;

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Medication> getMedicationByType(String medicationType) {
		return medicationDao.findAllByMedicationType(medicationType);
	}

	@Override
	public Medication createMedication(Medication medication) {
		return medicationDao.save(medication);
	}

	@Override
	public List<Medication> getMedUsingProdId(int productId) {
		Product product = productDao.findByProductId(Long.valueOf(productId));
		List<Medication> medicationList = new ArrayList<Medication>();
		if(product != null) {
			String medicationType = product.getMedicationType();
			medicationList = medicationDao.findAllByMedicationType(medicationType);
		}
		return medicationList;
	}
	


}
