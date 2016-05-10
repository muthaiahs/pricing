package com.pricing.appsrv.ws.endpoints;

import spark.Spark;

import com.pricing.appsrv.helper.JsonTransformer;
import com.pricing.appsrv.processor.PricingProcessor;
import com.pricing.common.model.PricingFeed;

public class PricingEndpoint {

	// staticFileLocation("/public");

	public static void main(String args[]) {
		
	   	
    	Spark.awaitInitialization(); // Wait for server to be initialized
    	
        Spark.get("/test", (req, res) -> " Test 1   " ); 
        
        Spark.get("/test1", (req, res) -> { 
        	
        	res.type("application/json");
        	
        	return new PricingProcessor().loadPricingFeedDetails();
        	
        });
        

		Spark.get("/testPricingFeed", "application/json", (request, response) -> {
			
			response.type("application/json");
			
			return new PricingFeed(null, null, null, null, null, null);			

		}, new JsonTransformer());
        
        

	}

}
