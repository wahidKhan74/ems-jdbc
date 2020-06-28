package com.simplilearn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabseConnection {
	
	static final String USERNAME ="sam";
	static final String PASSWORD ="sam121";	
	static final String DB_URL ="jdbc:mysql://localhost:3306/emsdb";	
	static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
	
	public static Connection initConnection() {
		Connection  conn = null;
		try {
			System.out.println("Create Db Connection !");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return conn;
	}
}
