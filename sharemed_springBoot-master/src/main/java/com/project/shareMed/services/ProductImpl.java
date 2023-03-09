package com.project.shareMed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shareMed.DAO.ProductDao;
import com.project.shareMed.entites.Product;

@Service
public class ProductImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductList() {
		return productDao.findAll();
	}

	@Override
	public Product getProduct(long productId) {
		Product p = null;
		for(Product product : productDao.findAll()) {
			if(product.getProductId() == productId) {
				p = product;
			}
		}
		return p;
	}

}
