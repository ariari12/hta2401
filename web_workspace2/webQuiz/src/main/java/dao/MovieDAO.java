package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MovieVO;

public class MovieDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public MovieDAO() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 영화 이름으로 조회
	public MovieVO selectOne(String name) {
		MovieVO vo = null;
		sb.setLength(0);
		sb.append(" SELECT no, name, src FROM movie ");
		sb.append(" WHERE name LIKE ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("no");
				String mname = rs.getString("name");
				String src = rs.getString("src");

				vo = new MovieVO(no, mname, src);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return vo;
	}

	// 추가
	public void addOne(MovieVO vo) {
		sb.setLength(0);
		sb.append(" INSERT INTO movie ");
		sb.append(" VALUES(MOVIE_NO_SEQ.NEXTVAL, ?, ?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getSrc());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void close() {
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
}
