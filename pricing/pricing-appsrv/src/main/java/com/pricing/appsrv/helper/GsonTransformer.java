package com.pricing.appsrv.helper;

import spark.ResponseTransformer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTransformer implements ResponseTransformer {

	Gson gson = new GsonBuilder().disableHtmlEscaping()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting().serializeNulls().create();

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}
	
	

}
