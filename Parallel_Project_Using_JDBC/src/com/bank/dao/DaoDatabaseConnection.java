package com.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DaoDatabaseConnection {
//	public static Connection getConnection() throws Exception {
//		
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection conn = 
//				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","satish","sats");
//		
//		return conn;
//	}
	
	public EntityManager getConnection() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("kum");
	
		EntityManager em=emf.createEntityManager();
		return em;
	}
}
