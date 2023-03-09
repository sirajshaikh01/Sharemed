package com.project.shareMed.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.shareMed.entites.Product;

public interface ProductDao extends JpaRepository<Product, Long>{

	public Product findByProductId(long productId);
	
}
