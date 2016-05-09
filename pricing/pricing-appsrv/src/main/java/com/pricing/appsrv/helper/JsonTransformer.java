package com.pricing.appsrv.helper;

import spark.ResponseTransformer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTransformer implements ResponseTransformer {

	// private Gson gson = new Gson();

	// Gson gson = new GsonBuilder().setPrettyPrinting().create();

	Gson gson = new GsonBuilder().disableHtmlEscaping()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting().serializeNulls().create();

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}

}
