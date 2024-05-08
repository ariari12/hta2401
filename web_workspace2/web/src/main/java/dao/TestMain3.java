package dao;

import java.sql.Connection;

public class TestMain3 {

	public static void main(String[] args) {
		DBConnection conn1 = DBConnection.getInstance();
		DBConnection conn2 = DBConnection.getInstance();
		
		System.out.println("conn1: " + conn1);
		System.out.println("conn2: " + conn2);
		
		System.out.println("-----------------------------------------------");
		
		 Connection conn = conn1.getConnection();
		
	}

}
