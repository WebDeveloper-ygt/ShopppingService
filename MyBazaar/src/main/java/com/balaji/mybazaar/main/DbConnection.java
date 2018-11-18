package com.balaji.mybazaar.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
   
	public static final String URL = "jdbc:mysql://localhost:3306/bazaar";
	public static final String user = "root";
	public static final String password = "admin";
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	
	public Connection getDbConnection() throws SQLException {
		Connection conn = null ;
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		try {
			conn = DriverManager.getConnection(URL, user, password);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn.getSchema());
		return conn;
		
	}
}
