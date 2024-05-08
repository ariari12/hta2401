package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public boolean insertMember(MemberVO vo) {
		sb.setLength(0);
		sb.append(" INSERT INTO member(no, id, pw, name, gender, zipcode, addrs) ");
		sb.append(" VALUES(MEMBER_NO_SEQ.nextval, ?, ?, ?, ?, ?, ?) ");
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getZipcode());
			pstmt.setString(6, vo.getAddrs());

			result = 1 == pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void insertMember1(MemberVO vo) {
		sb.setLength(0);
		sb.append(" INSERT INTO member(no, id, pw, name, gender) ");
		sb.append(" VALUES(MEMBER_NO_SEQ.nextval, ?, ?, ?, ?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
