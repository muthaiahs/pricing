package com.pricing.loader.helper;

import java.util.Arrays;
import java.util.function.Function;

import com.pricing.loader.model.PricingFeed;

public class PricingFeedMapper implements Function<String, PricingFeed> {

	@Override
	public PricingFeed apply(String input) {

		String[] record = input.split(",");
	//	System.out.println(" record    :       " + Arrays.toString(record));
		
		return new PricingFeed(record[0], record[1], record[2], record[4], record[5] ,  
				Double.valueOf(record[3]));
	}
}
