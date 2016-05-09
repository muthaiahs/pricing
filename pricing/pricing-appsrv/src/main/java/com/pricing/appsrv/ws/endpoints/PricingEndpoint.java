package com.pricing.appsrv.ws.endpoints;

import spark.Spark;

import com.pricing.appsrv.helper.JsonTransformer;
import com.pricing.common.model.PricingFeed;

public class PricingEndpoint {

	// staticFileLocation("/public");

	public static void main(String args[]) {

		Spark.get("/test", "application/json", (request, response) -> {
			
			response.type("application/json");
			
			return new PricingFeed(null, null, null, null, null, null);

		}, new JsonTransformer());

	}

}
