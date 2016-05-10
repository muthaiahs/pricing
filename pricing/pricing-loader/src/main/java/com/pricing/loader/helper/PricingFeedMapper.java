package com.pricing.loader.helper;

import java.util.function.Function;

import com.pricing.loader.model.PricingFeed;

public class PricingFeedMapper implements Function<String, PricingFeed> {

	@Override
	public PricingFeed apply(String input) {

	//	String[] record = input.split(",");
		String[] record = input.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		
	//	System.out.println(" record    :       " + Arrays.toString(record));
		
		return new PricingFeed(record[0], record[1], record[2],   
				Double.valueOf(record[3]) ,  record[4], record[5] );
	}
}
