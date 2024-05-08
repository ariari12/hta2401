package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;

public class MemberDAO {
	
	// 1. 환경변수
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public MemberDAO() {
		try {
			// 2. 드라이버로딩
			Class.forName(driver);
			
			// 3. Connection
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn: " + conn);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public MemberVO searchUser(String id, String pw) {
		// 4 ~ 7
		sb.setLength(0);
		sb.append(" SELECT no,id, pw, name, gender, motive FROM member WHERE id = ? AND pw = ? ");
		MemberVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				int no = rs.getInt("no");
				String gender = rs.getString("gender");
				String motive = rs.getString("motive");
				
				vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setNo(no);
				vo.setGender(gender);
				vo.setMotive(motive);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	// addMember
	public void addMember(MemberVO vo) {
		// sb 길이를 0으로 만들어서 초기화
		sb.setLength(0);
		// 4. SQL문 작성
		sb.append(" INSERT INTO member VALUES(MEMBER_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?) ");

		// 5. 문장 객체
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getMotive());
			
			// 6. 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO getOne(String id) {
		MemberVO vo = null;
		sb.setLength(0);
//		sb.append(" SELECT no, id, pw, name, gender, motive, zipcode, addrs ");
		sb.append(" SELECT id, pw, name ");
		sb.append(" FROM member ");
		sb.append(" WHERE id = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
//				int no = rs.getInt("no");
				String name = rs.getString("name");
//				String pw = rs.getString("pw");
//				String gender = rs.getString("gender");
//				String motive = rs.getString("motive");
//				String zipcode = rs.getString("zipcode");
//				String addrs = rs.getString("addrs");
				
				vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public ArrayList<MemberVO> selectAll() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		sb.setLength(0);
		sb.append(" SELECT no, id, pw, name, gender, motive, zipcode, addrs ");
		sb.append(" FROM member ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id"); 
				String pw = rs.getString("pw"); 
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String motive = rs.getString("motive");
				String zipcode = rs.getString("zipcode");
				String addrs = rs.getString("addrs");
				
				MemberVO vo = new MemberVO(no, id, pw, name, gender, motive, zipcode, addrs);
				list.add(vo);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
