package com.pricing.loader.dao;

import java.util.List;

import com.pricing.loader.model.PricingFeed;

public interface PricingFeedDAO {

	public void loadPricingFeed(List<PricingFeed> priceFeed) throws Exception ;  
	
}
