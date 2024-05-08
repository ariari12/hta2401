package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.FileInfoVO;

public class FileInfoDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public FileInfoDAO() {
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	public ArrayList<FileInfoVO> selectAll() {
		ArrayList<FileInfoVO> list = new ArrayList<FileInfoVO>();
		sb.setLength(0);
		sb.append(" SELECT fno, filename, type, no FROM fileinfo ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int fno = rs.getInt("fno");
				String filename = rs.getString("filename");
				String type = rs.getString("type");
				int no = rs.getInt("no");
				
				FileInfoVO vo = new FileInfoVO(fno, filename, type, no);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return list;
	}
	
	public void addFileOne(String filePath, String type, int no) {
		sb.setLength(0);
		sb.append(" INSERT INTO fileinfo ");
		sb.append(" VALUES(FILEINFO_FNO_SEQ.NEXTVAL, ?, ?, ?) ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, filePath);
			pstmt.setString(2, type);
			pstmt.setInt(3, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
