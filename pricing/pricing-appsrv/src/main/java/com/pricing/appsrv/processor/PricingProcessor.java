package com.pricing.appsrv.processor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pricing.appsrv.helper.PricingUtil;
import com.pricing.common.model.PricingFeed;

public class PricingProcessor {

	public PricingProcessor() {

		setUp();
	}

	public List<PricingFeed> inputList = null;

	public Map<String, Map<String, Double>> loadPricingFeedDetails() throws SQLException {
		
	//	PricingUtil input = new PricingUtil();
		
	//	inputList = input.loadAllPricingFeed();

		Map<String, List<PricingFeed>> test = inputList.stream().collect(
				Collectors.groupingBy(PricingFeed::getStoreName));

		System.out.println(" test  :         " + test);

		Map<String, Map<String, Double>> result = inputList
				.stream()
				.collect(Collectors.groupingBy(PricingFeed::getStoreName,
										Collectors.groupingBy(	PricingFeed::getItemCategory,
														Collectors.collectingAndThen(Collectors.summarizingDouble(PricingFeed::getPrice),
																		dss -> dss.getAverage()))));

		System.out.println(" test1  :         " + result);
		
		

		return result;
	}
	

	public static void main(String args[]) throws SQLException {

		PricingProcessor test = new PricingProcessor();

		test.loadPricingFeedDetails();

	}
	
	

	private void setUp() {

		inputList = new ArrayList<>();

		PricingFeed pricingFeed = new PricingFeed("ItemId_1", "TestItemName_1",
				"TestStoreName1", "TestItemCategory_1",
				"TestItemSubCategory_1", 5.0);

		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_2", "TestItemName_2",
				"TestStoreName1", "TestItemCategory_1",
				"TestItemSubCategory_1", 10.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_3", "TestItemName_3",
				"TestStoreName1", "TestItemCategory_1",
				"TestItemSubCategory_1", 15.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_4", "TestItemName_4",
				"TestStoreName1", "TestItemCategory_2",
				"TestItemSubCategory_1", 20.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName1", "TestItemCategory_3",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName2", "TestItemCategory_4",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName3", "TestItemCategory_5",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName4", "TestItemCategory_5",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName5", "TestItemCategory_5",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

	}

}
