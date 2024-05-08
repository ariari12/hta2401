<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysqlConnectionTest.jsp</title>
</head>
<body>
	<%
		// 1. 환경변수
		/* String driver = "com.mysql.jdbc.Driver"; */
		String driver = "com.mysql.cj.jdbc.Driver";
		/* String url = "jdbc:mysql://서버IP:포트번호/dbname"; */
		String url = "jdbc:mysql://xe.cvei6gkmygd3.ap-northeast-2.rds.amazonaws.com:3306/xe";
		String username = "scott";
		String password = "tigerlion2401$";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 2. 드라이버로딩
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn: " + conn);
			out.println("conn: " + conn);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		// 4. sql문장
		StringBuffer sb = new StringBuffer(); 
		sb.append("SELECT * FROM dept");
		
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
			
	%>
</body>
</html>