package com.pricing.loader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.pricing.loader.model.PricingFeed;

public class PricingFeedDAOImpl implements PricingFeedDAO {

	private static final String PRICE_FEED_INSERT_BATCH = " insert into price_feed (ITEM_ID , ITEM_TITLE , STORE , ITEM_CATEGORY , ITEM_SUB_CATEGORY , PRICE  ) "
			+ " values (?,?,?,?,?,? ) ";

	private static final int BATCH_SIZE = 30000;

	public void loadPricingFeed(List<PricingFeed> priceFeed) throws Exception {

		Long count = 0L;
		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = DBConnection.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(PRICE_FEED_INSERT_BATCH);

			long start = System.currentTimeMillis();
			for (PricingFeed pf : priceFeed) {
				ps.setString(1, pf.getItemId());
				ps.setString(2, pf.getItemTitle());
				ps.setString(3, pf.getStoreName());
				ps.setString(4, pf.getItemCategory());
				ps.setString(5, pf.getItemSubCategory());
				ps.setDouble(6, pf.getPrice());

				ps.addBatch();

				count++;

				if (count % BATCH_SIZE == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}

			}

			int[] result = ps.executeBatch();
			con.commit();

			System.out.println(" result   :        " + result);

			System.out.println("Time Taken="
					+ (System.currentTimeMillis() - start));

		} catch (Exception e) {

			try {
				
				if(con != null   )				
					con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			throw e;

		} finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
	}

}
