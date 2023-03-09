package com.project.shareMed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.InventoryDao;
import com.project.shareMed.entites.Inventory;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public List<Inventory> getInventoryInfo() {
		return inventoryDao.findAll();
	}

}
