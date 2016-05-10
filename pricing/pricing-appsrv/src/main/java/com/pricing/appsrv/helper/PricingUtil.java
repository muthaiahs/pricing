package com.pricing.appsrv.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.ResultSetIterable;
import org.sql2o.Sql2o;

import com.pricing.common.model.PricingFeed;

public class PricingUtil {

	Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/tarun", "root", "root");

	private static final int BATCH_SIZE = 3000;

	public List<PricingFeed> loadAllPricingFeed() throws SQLException {

		String sql = "SELECT ITEM_ID, ITEM_TITLE, STORE , ITEM_CATEGORY , ITEM_SUB_CATEGORY , PRICE  "
				+ "FROM tarun.price_feed";

		List<PricingFeed> result = new ArrayList<>();

		List<PricingFeed> batch = new ArrayList<PricingFeed>(BATCH_SIZE);

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
						// here is where you flush your batch to file

						result.addAll(batch);
						System.out.println(" result  :         "
								+ result.size());

						batch.clear();
					}
					batch.add(priceFeed);
				}
				result.addAll(batch);
			}
		}
		
		return result;  

	}
	
	
	public void insertABunchOfRows(List<PricingFeed> priceFeed ){
				
		Long count = 0L;
		
		final String PRICE_FEED_INSERT_BATCH = " insert into price_feed (ITEM_ID , ITEM_TITLE , STORE , ITEM_CATEGORY , ITEM_SUB_CATEGORY , PRICE  ) "
				+ " values (:itemId,:itemName,:storeName,:itemCategory,:itemSubCategory,:price ) ";
	    

	    try (Connection con = sql2o.beginTransaction()) {
	        Query query = con.createQuery(PRICE_FEED_INSERT_BATCH);

	        for (PricingFeed pf : priceFeed) {
	            query.addParameter("itemId",  pf.getItemId())
	            	.addParameter("itemName",  pf.getItemTitle())
	            	.addParameter("storeName", pf.getStoreName())
	            	.addParameter("itemCategory",  pf.getItemCategory())
	            	.addParameter("itemSubCategory",  pf.getItemSubCategory())
	            	.addParameter("price",  pf.getPrice())	            	
                    .addToBatch(); 	            

				count++;

				if (count % BATCH_SIZE == 0) {
					query.executeBatch();
	//				ps.clearBatch();
				}
				
	        }

	        query.executeBatch(); // executes entire batch
	        con.commit();         // remember to call commit(), else sql2o will automatically rollback.
	    }
	}
	
	

}
