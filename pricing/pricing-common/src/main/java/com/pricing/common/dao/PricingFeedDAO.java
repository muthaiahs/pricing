package com.pricing.common.dao;

import java.util.List;

import com.pricing.common.model.PricingFeed;

public interface PricingFeedDAO {

	public void loadPricingFeed(List<PricingFeed> priceFeed) throws Exception ;  
	
}
