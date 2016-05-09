package com.pricing.appsrv.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.ResultSetIterable;
import org.sql2o.Sql2o;

import com.pricing.common.model.PricingFeed;

public class PricingUtil {

	Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/tarun", "root", "root");

	public void loadAllPricingFeed() throws SQLException {

		String sql = "SELECT id, description, duedate " + "FROM tasks";

		final int BATCH_SIZE = 1000;

		List<PricingFeed> batch = new ArrayList<PricingFeed>(BATCH_SIZE);

		try (Connection con = sql2o.open()) {
			try (ResultSetIterable<PricingFeed> tasks = con.createQuery(sql)
					.addColumnMapping("DUE_DATE", "dueDate")
					.executeAndFetchLazy(PricingFeed.class)) {
				for (PricingFeed task : tasks) {
					if (batch.size() == BATCH_SIZE) {
						// here is where you flush your batch to file

						batch.clear();
					}
					batch.add(task);
				}
			}
		}
	}

}
