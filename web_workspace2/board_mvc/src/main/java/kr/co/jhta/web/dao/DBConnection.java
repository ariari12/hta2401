package kr.co.jhta.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 싱글톤
public class DBConnection {
	private static DBConnection instance = new DBConnection();
	
	private DBConnection() { }
	
	public static DBConnection getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		// JNDI(Java Naming and Directory Interface)
		// 디렉토리 서비스에서 제공하는 데이터 및 객체를 발견하고 참고하기 위한 자바 API
		
		Context initCtx;
		Connection conn = null;
		try {
			initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("myoracle");
			
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
