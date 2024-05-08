package day3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCEx1 {
	public static void main(String[] args) {
		// 1. 환경변수
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 드라이버로딩
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn: " + conn);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4. sql문장
		StringBuffer sb = new StringBuffer(); 
		sb.append("SELECT * FROM DEPT");
		
		try {
			// 5. sql문장 객체
			pstmt = conn.prepareStatement(sb.toString());
			// 6. 실행 (ResultSet)
			rs = pstmt.executeQuery();
			
			// 7. 레코드별 로직 처리
//			for(int i = 0; i < 4; i++) {
			while(rs.next()) { 
//				rs.next(); // 전달받은 리절트셋의 첫번째 레코드로 이동
				int deptno = rs.getInt("deptno"); // 컬럼명이 deptno
//				int deptno = rs.getInt(1); // 1번째 컬럼
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				
				System.out.println(deptno + " : " + dname + " : " + loc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 8. 자원반납
			try {
				if (rs != null) { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
