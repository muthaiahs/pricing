package com.pricing.loader.model;

import java.io.Serializable;

public class PricingFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3941310764560195025L;

	public PricingFeed(String itemId, String itemTitle, String storeName, 
			 Double price ,  String itemCategory, String itemSubCategory  ) {  

		this.itemId = itemId;
		this.itemTitle = itemTitle;
		this.storeName = storeName;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
		this.price = price;

	}

	private String itemId;
	private String itemTitle;
	private String storeName;
	private String itemCategory;
	private String itemSubCategory;
	private Double price;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSubCategory() {
		return itemSubCategory;
	}

	public void setItemSubCategory(String itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
