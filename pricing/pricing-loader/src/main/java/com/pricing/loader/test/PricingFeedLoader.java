package com.pricing.loader.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.pricing.loader.dao.PricingFeedDAO;
import com.pricing.loader.dao.PricingFeedDAOImpl;
import com.pricing.loader.helper.PricingFeedMapper;
import com.pricing.loader.model.PricingFeed;

public class PricingFeedLoader {

	public static void main(String[] args) {

		System.out.println(" Entered into PricingFeedLoader()  :       ");

		if (!checkArgs(args)) {

			System.err.println(" Invalid PricingFeedLoader -  args :    \n   "
					+ Arrays.toString(args ) + "   \n");  
			System.exit(-1);

		}

		loadFeedFile(args[0], args[1]);

		System.out.println(" Exit into PricingFeedLoader()  :       ");

	}

	private static void loadFeedFile(String path, String separator) {

		try (InputStream is = new FileInputStream(new File(path));
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));) {

			List<PricingFeed> pricingFeedList = br.lines()
					.skip(1)
					.map(new PricingFeedMapper()).collect(Collectors.toList());

			PricingFeedDAO pricingFeedDAO = new PricingFeedDAOImpl();

			pricingFeedDAO.loadPricingFeed(pricingFeedList);

		} catch (Exception e) {

			System.err
					.println(" Exception occured in PricingFeedLoader   :      \n   "
							+ e.getMessage() + "   \n");
			
			e.printStackTrace();  
			
			System.exit(-1);
		}

	}

	private static boolean checkArgsCount(String[] args) {

		if (args == null || args.length == 2) {

			return true;
		}

		return false;
	}

	private static boolean checkArgs(String[] args) {

		if (!checkArgsCount(args)) {

			System.err
					.println("Usage: PricingFeedLoader <feed-file-path> <separator> \n"
							+ " where   <feed-file-path> is the feed-file to use   \n"
							+ "  <separator> is the separator eg : | or ,   \n\n");
			return false;

		}

		String path = null;
		String sep = null;

		if (args != null) {

			path = args[0];
			sep = args[1];

			if (path == null || path.isEmpty()) {

				System.err.println(" PricingFeedLoader -   path is null   ");
				return false;

			}

			File feedFile = new File(path);

			if (feedFile == null || !feedFile.exists() || !feedFile.isFile()) {

				System.err
						.println(" PricingFeedLoader -  feedFilePath does not exists    "
								+ path);
				return false;

			}

			if (sep == null || sep.isEmpty()) {

				System.err.println(" PricingFeedLoader -   sep is null   ");
				return false;
			}

		}

		return true;
	}

}
