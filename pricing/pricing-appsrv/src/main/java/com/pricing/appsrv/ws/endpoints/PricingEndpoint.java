package com.pricing.appsrv.ws.endpoints;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import spark.Spark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pricing.appsrv.helper.GsonTransformer;
import com.pricing.appsrv.helper.JsonTransformer;
import com.pricing.appsrv.processor.PricingProcessor;

public class PricingEndpoint {

	// staticFileLocation("/public");

	public static void main(String args[]) {

		Spark.init();

		Spark.awaitInitialization(); // Wait for server to be initialized

		Spark.get("/test", (req, res) -> " Test 1   ");

		Spark.get("/loadPricingFeedDetails",
				(req, res) -> new PricingProcessor().loadPricingFeedDetails(),
				new GsonTransformer());

		Spark.get("/test1", (req, res) -> {

			res.status(200);
			res.type("application/json");
			return dataToJson(new PricingProcessor().loadPricingFeedDetails());

		});

		Spark.get(
				"/testPricingFeed",
				"application/json",
				(request, response) -> {

					response.type("application/json");

					Map result = new PricingProcessor()
							.loadPricingFeedDetails();

					System.out.println(" result    :         " + result);

					return result;

				}, new JsonTransformer());

	}

	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			System.out.println(" check    :         " + sw.toString());

			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter?");
		}
	}

}
