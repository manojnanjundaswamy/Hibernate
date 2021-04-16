package com.hibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/hb-many-to-many?useSSL=false";
		String user = "root";
		String pass = "root";

		try {
			
			System.out.println("Connecting to "+url);
			
			Connection myConn = DriverManager.getConnection(url, user, pass);
			
			System.out.println("Connection successful!.");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
