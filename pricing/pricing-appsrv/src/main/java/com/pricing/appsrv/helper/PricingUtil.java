package com.pricing.appsrv.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.ResultSetIterable;
import org.sql2o.Sql2o;

import com.pricing.common.model.PricingFeed;

public class PricingUtil {

	final int BATCH_SIZE = 1000;
	Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/tarun", "root", "root");

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

}
