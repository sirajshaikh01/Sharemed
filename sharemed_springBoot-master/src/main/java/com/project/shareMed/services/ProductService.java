package com.project.shareMed.services;

import java.util.List;

import com.project.shareMed.entites.Product;

public interface ProductService {
	
	public Product createProduct(Product product);
	
	public List<Product> getProductList();
	
	public Product getProduct(long productId);
}
