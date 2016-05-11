package com.pricing.appsrv.processor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.sql2o.Connection;
import org.sql2o.ResultSetIterable;
import org.sql2o.Sql2o;

import com.pricing.common.model.PricingFeed;

public class PricingProcessor {

	public PricingProcessor() {

		setUp();
	}

	public List<PricingFeed> inputList = null;
	public List<Map<String , Map<String, String> > > priceFeedList = null;  

	public Map<String, Map<String, Double>> loadPricingFeedDetails() throws SQLException {  
		
		Map<String, Map<String, String  >> result = new HashMap<>();			
		
	//	List<Map<String, Map<String, String>>> priceSumList = loadAllPricingFeed();
		List<Map<String, Map<String, String>>> priceSumList = setUp();
		
		System.out.println(" priceSumList  :         " + priceSumList.size());
		
		
		
		for(Map<String, Map<String, String>> inputPriceSum : priceSumList ) {
			
			for(Map.Entry<String, Map<String, String>> inputStoreCategoryEntry  : inputPriceSum.entrySet()   ) {
				
				String storeName = inputStoreCategoryEntry.getKey();	
				
				for(Map.Entry<String, String> inputPriceSumEntry : inputStoreCategoryEntry.getValue().entrySet() ) {
					
					String itemCategoryName = inputPriceSumEntry.getKey();  
					
					if(result.containsKey(storeName)  ) {
						
						if(result.get(storeName).containsKey(itemCategoryName)    ) {
							
							String sumCount =  result.get(storeName).get(itemCategoryName);
							String[] sumCountArr = sumCount.split("\\|");
							
							System.out.println("  sumCountArr sumCount  :         " + sumCount);
							System.out.println(" sumCountArr  :         " + Arrays.toString(sumCountArr));							
							System.out.println(" sum  :         " + sumCountArr[0]);
							System.out.println(" price  :         " + sumCountArr[1]);
							
							
							System.out.println(" inputPriceSumEntry  :         " + inputPriceSumEntry.getValue());
							System.out.println(" inputPriceSumEntry price  :         " + Arrays.toString(inputPriceSumEntry.getValue().split("\\|")));							
							System.out.println(" sum  :         " + inputPriceSumEntry.getValue().split("\\|")[0]);
							System.out.println(" price  :         " + inputPriceSumEntry.getValue().split("\\|")[1]);
							
							
							Double sum = Double.valueOf(sumCountArr[0]) + Double.valueOf(inputPriceSumEntry.getValue().split("\\|")[0]);  
							Double count = Double.valueOf(sumCountArr[1]) + Double.valueOf(inputPriceSumEntry.getValue().split("\\|")[1]);							
							result.get(storeName).put(itemCategoryName, String.valueOf(sum + "|" + count) );  
							
						} else {
							
							result.get(storeName).put(itemCategoryName,  inputPriceSumEntry.getValue());
						}
						
					} else {
						
						Map<String, String> itemCategoryMap = new HashMap<>();							
						itemCategoryMap.put(itemCategoryName,  inputPriceSumEntry.getValue()   );
						
						result.put(storeName, itemCategoryMap);
					}
					
				}
				
			}
		
			
		}
	
		

		System.out.println(" test1  :         " + result);
		
		

		return processItemCategoryStorePrice(result);
	}
	
	private Map<String, Map<String, Double> >  processItemCategoryStorePrice(Map<String, Map<String, String>> inputMap ) {  
		
		Map<String, Map<String, Double  >> resultMap = new HashMap<>();	
		Map<String, Double> itemCategoryPriceMap = null; 
				
		for(Map.Entry<String, Map<String, String>> itemStoreCategorySumEntry : inputMap.entrySet() ) {			
			
			String storeName = itemStoreCategorySumEntry.getKey();
			
			for(Map.Entry<String, String> itemCategorySumEntry  :  itemStoreCategorySumEntry.getValue().entrySet()   ) {
				
				String itemCategoryName = itemCategorySumEntry.getKey();							
				String[] sumCountArr = itemCategorySumEntry.getValue().split("\\|");				
				Double avg_price = Double.valueOf(sumCountArr[0]) /  Double.valueOf(sumCountArr[1]);							
				
				if(resultMap.containsKey(storeName)  ) {					
					if(resultMap.get(storeName).containsKey(itemCategoryName)    ) {
						
						resultMap.get(storeName).put(itemCategoryName, avg_price);		
	//					resultMap.get(storeName).put(itemCategoryName, String.valueOf(avg_price + "{" + diff_avg_price) + "}"  );						
					} else { 
						
						resultMap.get(storeName).put(itemCategoryName, avg_price);
					}		

				} else {
															
					itemCategoryPriceMap = new HashMap<>();
					itemCategoryPriceMap.put(itemCategoryName, avg_price);
					
					resultMap.put(storeName, itemCategoryPriceMap  );
				}	
				
			}
		}

		
		
		return resultMap; 
		
	}
	

	Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/tarun", "root", "root");

	private static final int BATCH_SIZE = 3000;

	public List<Map<String , Map<String, String> > > loadAllPricingFeed() throws SQLException {  

		String sql = "SELECT ITEM_ID, ITEM_TITLE, STORE , ITEM_CATEGORY , ITEM_SUB_CATEGORY , PRICE  "
				+ "FROM tarun.price_feed";

		List<PricingFeed> batch = new ArrayList<PricingFeed>(BATCH_SIZE);
		List<Map<String , Map<String, String >>> resultMap = new ArrayList<>();
		
		
		Map<String, Map<String, String>> result = null;

		try (Connection con = sql2o.open()) {
			try (ResultSetIterable<PricingFeed> priceFeedList = con
					.createQuery(sql).addColumnMapping("ITEM_ID", "itemId")
					.addColumnMapping("ITEM_TITLE", "itemTitle")
					.addColumnMapping("STORE", "storeName")
					.addColumnMapping("ITEM_CATEGORY", "itemCategory")
					.addColumnMapping("ITEM_SUB_CATEGORY", "itemSubCategory")
					.addColumnMapping("PRICE", "price")
					.executeAndFetchLazy(PricingFeed.class)) {

				for (PricingFeed priceFeed : priceFeedList) {
					if (batch.size() == BATCH_SIZE) {
						
						
						result = batch
								.stream()
								.collect(Collectors.groupingBy(PricingFeed::getStoreName,
											Collectors.groupingBy(	PricingFeed::getItemCategory,
													Collectors.collectingAndThen(Collectors.summarizingDouble(PricingFeed::getPrice),
															dss -> String.valueOf(dss.getSum() + "|" + dss.getCount()  )))));

	//					System.out.println(" test1  :         " + result);								
	//					System.out.println(" test1 count  :         "+ result.size());
						
						resultMap.add(result);

						batch.clear();
					}
					
					batch.add(priceFeed);
				}

				result = batch
						.stream()
						.collect(Collectors.groupingBy(PricingFeed::getStoreName,
												Collectors.groupingBy(	PricingFeed::getItemCategory,
																Collectors.collectingAndThen(Collectors.summarizingDouble(PricingFeed::getPrice),
																		dss -> String.valueOf(dss.getSum() + "|" + dss.getCount()  )))));
				resultMap.add(result);
				
			}
		}
		
		return resultMap;  

	}
	
	

	public static void main(String args[]) throws SQLException {

		PricingProcessor test = new PricingProcessor();

		Map output =  test.loadPricingFeedDetails();
		
		System.out.println("  output    :         " + output);        
		
		

	}
	
	

	private List<Map<String , Map<String, String > > > setUp() {      

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

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName4", "TestItemCategory_4",
				"TestItemSubCategory_1", 50.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName4", "TestItemCategory_4",
				"TestItemSubCategory_1", 30.0);
		inputList.add(pricingFeed);
		

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName4", "TestItemCategory_5",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName5", "TestItemCategory_5",
				"TestItemSubCategory_1", 25.0);
		inputList.add(pricingFeed);
		

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName5", "TestItemCategory_5",
				"TestItemSubCategory_1", 50.0);
		inputList.add(pricingFeed);
		

		pricingFeed = new PricingFeed("ItemId_5", "TestItemName_5",
				"TestStoreName5", "TestItemCategory_5",
				"TestItemSubCategory_1", 50.0);
		inputList.add(pricingFeed);
		
		
		

		Map result = inputList
				.stream()
				.collect(Collectors.groupingBy(PricingFeed::getStoreName,
										Collectors.groupingBy(	PricingFeed::getItemCategory,
														Collectors.collectingAndThen(Collectors.summarizingDouble(PricingFeed::getPrice),
																dss -> String.valueOf(dss.getSum() + "|" + dss.getCount()  )))));
		
		priceFeedList = new ArrayList<>();
		priceFeedList.add(result);
		
		return priceFeedList;  

	}

}
