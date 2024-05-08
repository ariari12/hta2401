package ITBookFleaMarket.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private static Connection con;

	private DBConn() {

	}

	public static Connection getConnection() {
		if (con == null) {
			try {
				String driver = "oracle.jdbc.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String username = "dev";
				String password = "1111";

				Class.forName(driver);
//				System.out.println("드라이버 로딩 ok");

				con = DriverManager.getConnection(url, username, password);
//				System.out.println("con ok");
			} catch (ClassNotFoundException e) {
				System.err.println(">> 드라이버를 찾을 수 없습니다.");
			} catch (SQLException e) {
				System.err.println(">> DB 연결 실패 또는 SQLExeption 예외 발생");
				e.printStackTrace();
			}
		}

		return con;
	}

	public static void close() {
		try {
			if (con != null) {
				con.close();
			}
//			System.out.println("con closed!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
//			System.out.println("pstmt closed!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
//			System.out.println("stmt closed!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt, Statement stmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
//			System.out.println("pstmt closed!");
			
			if (stmt != null) {
				stmt.close();
			}
//			System.out.println("stmt closed!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
//				System.out.println("stmt closed!");

			if (pstmt != null) {
				pstmt.close();
			}
//				System.out.println("pstmt closed!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
