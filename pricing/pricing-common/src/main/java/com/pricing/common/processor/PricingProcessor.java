package com.pricing.common.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pricing.common.model.PricingFeed;

public class PricingProcessor {

	public PricingProcessor() {

		setUp();
	}

	public List<PricingFeed> inputList = null;

	public static void main(String args[]) {

		PricingProcessor test = new PricingProcessor();

		test.loadPricingFeedDetails();

	}

	public Map<String, Map<String, Double>> loadPricingFeedDetails() {

	//	Collection<PricingFeed> result = null;
		
		Map<String, Map<String, Double>> result = null; 

		Stream<PricingFeed> stream = inputList.stream();

		System.out.println(" stream  :         " + stream);

	//	result = inputList.stream().collect(Collectors.toList());

		Map<String, List<PricingFeed>> test = inputList.stream().collect(
				Collectors.groupingBy(PricingFeed::getStoreName));

		System.out.println(" test  :         " + test);

		Map<String, Map<String, List<PricingFeed>>> output = inputList
				.stream()
				.collect(Collectors.groupingBy(PricingFeed::getStoreName,
						 Collectors.groupingBy(PricingFeed::getItemCategory
						)));


		System.out.println(" output  :         " + output);
		


		Double avg_price = null;

	//	List<PricingFeed> pricingFeedList = output.get("TestStoreName1");
		
		

		Map<String, Map<String, Double>> test1 = inputList.stream().collect(
				Collectors.groupingBy(PricingFeed::getStoreName,Collectors.groupingBy(PricingFeed::getItemCategory, Collectors
						.collectingAndThen(Collectors
								.summarizingDouble(PricingFeed::getPrice),
								dss -> dss.getAverage()))));

		System.out.println(" test1  :         " + test1);
		
		Function<PricingFeed, List<Object>> keyExtractor = p ->  Arrays.<Object>asList(p.getStoreName() , p.getItemCategory() );
		Map<Object, Object> test_1 = inputList.stream().collect(
				Collectors.groupingBy( keyExtractor , Collectors
						.collectingAndThen(Collectors
								.summarizingDouble(PricingFeed::getPrice),
								dss -> dss.getAverage())));

		System.out.println(" test_1  :         " + test_1);
		
				
	    Map< List<Object>, Object> aggregatedData = inputList.stream().collect(
	      Collectors.groupingBy(keyExtractor,  Collectors
					.collectingAndThen(Collectors
							.summarizingDouble(PricingFeed::getPrice),
							dss -> dss.getAverage())));
	
	    System.out.println(" output  :         " + aggregatedData  );
	    System.out.println(" output  :         " + new ArrayList<>(aggregatedData.keySet()).get(0) );  
	    System.out.println(" output  :         " + new ArrayList<>(aggregatedData.keySet()).get(0).size() );

	    
	    

		/*
		 * 
		 * peopleByManyParams = people.collect(Collectors.groupingBy( p -> new
		 * KeyObj(p.age, p.other1, p.other2), Collectors.mapping((Person p) ->
		 * p, toList())));
		 */

		Integer[] numbersArray = new Integer[] { 1, 2, 3, 4, 5 };

		System.out.println(Arrays.stream(numbersArray).collect(
				Collectors.counting()));

		System.out.println(Arrays.stream(numbersArray).collect(
				Collectors.summingInt((Integer x) -> x)));

		System.out.println(Arrays.stream(numbersArray).collect(
				Collectors.averagingInt((Integer x) -> x)));

		System.out.println(Arrays.stream(numbersArray)
				.collect(Collectors.maxBy(Integer::compare)).get());

		System.out.println(Arrays.stream(numbersArray)
				.collect(Collectors.minBy(Integer::compare)).get());

		System.out.println(Arrays.stream(numbersArray).collect(
				Collectors.summarizingInt((Integer x) -> x)));

		return result;
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
