package com.pricing.common.model;

import java.io.Serializable;
import java.util.Objects;

public class PricingFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3941310764560195025L;

	public PricingFeed(String itemId, String itemTitle, String storeName,
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.itemId);
		hash = 79 * hash + Objects.hashCode(this.itemTitle);
		hash = 79 * hash + Objects.hashCode(this.storeName);
		hash = 79 * hash + Objects.hashCode(this.itemCategory);
		hash = 79 * hash + Objects.hashCode(this.itemSubCategory);
		hash = 79 * hash + Objects.hashCode(this.price);

		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}

		final PricingFeed other = (PricingFeed) obj;
		
		if (!Objects.equals(this.itemId, other.itemId)) {
			return false;
		}
		if (!Objects.equals(this.itemTitle, other.itemTitle)) {
			return false;
		}
		if (!Objects.equals(this.storeName, other.storeName)) {
			return false;
		}
		if (!Objects.equals(this.itemCategory, other.itemCategory)) {
			return false;
		}
		if (!Objects.equals(this.itemSubCategory, other.itemSubCategory)) {
			return false;
		}
		if (!Objects.equals(this.price, other.price)) {
			return false;
		}

		return true;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" PricingFeed  [    ")
		.append(itemId)
		.append(",")
		.append(itemTitle)
		.append(",")
		.append(storeName)
		.append(",")
		.append(itemCategory)
		.append(",")
		.append(itemSubCategory)
		.append(",")
		.append(price)
		.append(" ]  "); 
		
		return sb.toString();  
		
			
		
		
		
	}
	
    public static class CollectorTypes {
    	
    	private String storeName;
    	private String itemCategory; 
    	
        public CollectorTypes(String storeName, String itemCategory) {  
            
        	this.storeName = storeName;
        	this.itemCategory = itemCategory; 
        	
        }
        
        @Override
    	public int hashCode() {
    		int hash = 7;

    		hash = 37 * hash + Objects.hashCode(this.storeName);
    		hash = 37 * hash + Objects.hashCode(this.itemCategory);
   // 		hash = 37 * hash + Objects.hashCode(this.itemSubCategory);

    		return hash;
    	}

    	@Override
    	public boolean equals(Object obj) {

    		if (this == obj) {

    			return true;
    		}

    		if (obj == null) {
    			return false;
    		}
    		
    		if (getClass() != obj.getClass()) {
    			return false;
    		}

    		final PricingFeed other = (PricingFeed) obj;

    		if (!Objects.equals(this.storeName, other.storeName)) {
    			return false;
    		}
    		if (!Objects.equals(this.itemCategory, other.itemCategory)) {
    			return false;
    		}
    		/*
    		
    		if (!Objects.equals(this.itemSubCategory, other.itemSubCategory)) {
    			return false;
    		}
    		
    		  */  


    		return true;
    	}
    	
        
    }

    public CollectorTypes getNameAge() {
        return new CollectorTypes(storeName, itemCategory);
    }
    
    

}
