package com.project.shareMed.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shareMed.entites.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, Long> {

	public Inventory findByMedicineId(long medicineId);
	public Inventory findById(long id);
}
