package com.pricing.appsrv.helper;

import java.io.File;

import spark.ResponseTransformer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTransformer implements ResponseTransformer {

	@Override
	public String render(Object model) {

		ObjectMapper mapper = new ObjectMapper();

		/**
		 * Convert Map to JSON and write to a file
		 */
		try {
			mapper.writeValue(new File("result.json"), model);

			return mapper.writeValueAsString(model);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
