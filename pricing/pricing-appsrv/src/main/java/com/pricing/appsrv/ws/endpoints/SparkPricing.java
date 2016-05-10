package com.pricing.appsrv.ws.endpoints;

import com.pricing.appsrv.processor.PricingProcessor;

import spark.Spark;


public class SparkPricing {

    public static void main(String[] args) {

    	
    	Spark.awaitInitialization(); // Wait for server to be initialized
    	
        Spark.get("/test", (req, res) -> " Test 1   " ); 
        
        Spark.get("/test1", (req, res) -> { 
        	
        	res.type("application/json");
        	
        	return new PricingProcessor().loadPricingFeedDetails();
        	
        });
        
  //      Spark.get("/stop", (req, res) -> Spark.stop(); );  
        
    }
	
    
    
	
}
