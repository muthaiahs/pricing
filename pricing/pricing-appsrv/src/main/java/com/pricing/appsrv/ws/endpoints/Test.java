package com.pricing.appsrv.ws.endpoints;

import spark.Spark;

import com.google.gson.Gson;

public class Test {

	

	

    public static void main(String args[]) {

    	Gson gson = new Gson();
    	
    	Spark.get("/posts", (request, response) -> {  
    	    response.status(200);
    	    response.type("application/json");
//    	    return dataToJson(model.getAllPosts());
    	    return gson.toJson(null);  
    	});
    	
    	
        
    }
    
    
	



	
	
}
