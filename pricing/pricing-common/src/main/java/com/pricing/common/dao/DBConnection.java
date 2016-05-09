package com.pricing.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {
		Properties props = new Properties();		
		Connection con = null;
		
		try {  			
//			props.load(fis);

			// load the Driver Class
			Class.forName("com.mysql.jdbc.Driver");  

			// create the connection now
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tarun?rewriteBatchedStatements=true",
					"root",
					"root");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();	
			System.exit(-1);
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
