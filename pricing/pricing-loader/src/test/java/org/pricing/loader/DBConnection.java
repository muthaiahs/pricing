package org.pricing.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {
		Properties props = new Properties();		
		Connection con = null;
		
		File input = new File("pricing-loader.properties");  
		
		if(input == null || !input.exists() ) {   
			

			System.err.println(" input is null        "+input  );    
			System.exit(-1);
		}
		
		try(InputStream fis = new FileInputStream(input);  ) {			
			props.load(fis);

			// load the Driver Class
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));

			// create the connection now
			con = DriverManager.getConnection(props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();			
		}
		
		return con;
	}

	public static void close(Statement stmt) {

		try {

			if (stmt != null && !stmt.isClosed()) {

				stmt.close();
			}

		} catch (SQLException e) {

		}

	}

	public static void close(Connection connection) {

		try {

			if (connection != null && !connection.isClosed()) {

				connection.close();
			}

		} catch (SQLException e) {
			;
		}

	}

}
