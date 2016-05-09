package com.pricing.common.model;

import java.io.Serializable;

public class PriceDetails implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5789716032487029436L;

	public PriceDetails(String itemId, String itemTitle, String storeName,
			String itemCategory, String itemSubCategory, Double price) {

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
