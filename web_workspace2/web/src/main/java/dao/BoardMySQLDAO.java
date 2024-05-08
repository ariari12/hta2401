package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BoardVO;

public class BoardMySQLDAO {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://mydb.cvei6gkmygd3.ap-northeast-2.rds.amazonaws.com:3306/xe";
//	String user = "scott";
//	String password = "tigerlion2401$";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	StringBuffer sb = new StringBuffer();
	
	public BoardMySQLDAO() {
		conn = DBConnection.getInstance().getConnection();
		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("conn: " + conn);
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	public ArrayList<BoardVO> selectAll(int startNo, int recordPerPage) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		sb.append(" SELECT bno, writer, title, contents, regdate, hits, ip, status ");
		sb.append(" FROM BOARD ");
		sb.append(" ORDER BY bno desc ");
		sb.append(" LIMIT ?, ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, recordPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String regdate = rs.getString("regdate");
				int hits = rs.getInt("hits");
				String ip = rs.getString("ip");
				int status = rs.getInt("status");
				
				BoardVO vo = new BoardVO(bno, writer, title, contents, regdate, hits, ip, status);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
