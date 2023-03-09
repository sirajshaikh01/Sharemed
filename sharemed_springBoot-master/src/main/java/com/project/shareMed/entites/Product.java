package com.project.shareMed.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private long productId;
	private String productName;
	private String productDesc;
	private String productDesc2;
	private String productDesc3;
	private String productImageUrl;
	private String tags;
	private float donationRecived;
	private int doationCount;
	private String medicationType;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(long productId, String productName, String productDesc, String productDesc2, String productDesc3,
			String productImageUrl, String tags, float donationRecived, int doationCount,String medicationType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productDesc2 = productDesc2;
		this.productDesc3 = productDesc3;
		this.productImageUrl = productImageUrl;
		this.tags = tags;
		this.donationRecived = donationRecived;
		this.doationCount = doationCount;
		this.medicationType = medicationType;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public float getDonationRecived() {
		return donationRecived;
	}

	public void setDonationRecived(float donationRecived) {
		this.donationRecived = donationRecived;
	}

	public int getDoationCount() {
		return doationCount;
	}

	public void setDoationCount(int doationCount) {
		this.doationCount = doationCount;
	}

	public String getProductDesc2() {
		return productDesc2;
	}

	public void setProductDesc2(String productDesc2) {
		this.productDesc2 = productDesc2;
	}

	public String getProductDesc3() {
		return productDesc3;
	}

	public void setProductDesc3(String productDesc3) {
		this.productDesc3 = productDesc3;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}
	
	
	
}
