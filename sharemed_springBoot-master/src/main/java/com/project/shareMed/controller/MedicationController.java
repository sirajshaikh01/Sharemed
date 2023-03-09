package com.project.shareMed.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.shareMed.entites.Medication;
import com.project.shareMed.entites.MedicationType;
import com.project.shareMed.services.MedicationService;
import com.project.shareMed.services.MedicationTypeService;

@RestController
public class MedicationController {
	@Autowired
	private MedicationService medicationService;

	@Autowired
	private MedicationTypeService medicationTypeService; 
	
	
	@GetMapping("/getMedicationList/{medicationType}")
	public List<Medication> getMedicationListByType(@PathVariable String medicationType){
		return medicationService.getMedicationByType(medicationType);
	}
	@PostMapping("/createMedication")
	public Medication createMedication(@RequestBody Medication medication) {
		return medicationService.createMedication(medication);
	}
	
	@GetMapping("/getMedicationTypeList")
	public List<MedicationType> getMedicationtTypeList() {
		return medicationTypeService.getMedicationtTypeList();
	}
	
	@GetMapping("/getMedsByProdId/{productId}")
	public List<Medication> getMedicationListFromProductId(@PathVariable String productId){
		return medicationService.getMedUsingProdId(Integer.parseInt(productId));
	}
	

}
