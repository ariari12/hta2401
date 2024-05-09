package kr.co.jhta.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.jhta.web.vo.BoardVO;



public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuffer sb = new StringBuffer();
	
	public BoardDAO() {
		conn = DBConnection.getInstance().getConnection();		
	}
	
	// 전체 게시물 건수
	public int getTotalCount() {
		int cnt = 0;
		sb.setLength(0);
		sb.append(" SELECT COUNT(*) cnt FROM board ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return cnt;
	}	
	
	// 전체 조회
	public ArrayList<BoardVO> selectAll() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		sb.setLength(0);
		sb.append(" SELECT bno, writer, title, contents, to_char(regdate, 'yyyy-mm-dd') regdate, hits, ip, status ");
		sb.append(" FROM board ");
		sb.append(" ORDER BY bno DESC ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
		
		close();
		return list;
	}
	
	// 전체 조회
	public ArrayList<BoardVO> selectAll(int startNo, int endNo) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		sb.setLength(0);
		sb.append(" SELECT bno, writer, title, contents, regdate, hits, ip, status ");
		sb.append(" FROM ( SELECT ROWNUM rn, bno, writer, title, contents, regdate, hits, ip, status ");
		sb.append("        FROM ( SELECT bno, writer, title, contents, regdate, hits, ip, status ");
		sb.append("               FROM board ");
		sb.append("               ORDER BY bno desc ) ");
		sb.append("        WHERE ROWNUM <= ? ) ");
		sb.append(" WHERE rn >= ? ");
		
		// sb.append(" SELECT bno, writer, title, contents, to_char(regdate, 'yyyy-mm-dd') regdate, hits, ip, status ");
		// sb.append(" FROM board ");
		// sb.append(" ORDER BY bno DESC ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, endNo);
			pstmt.setInt(2, startNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
		
		close();
		return list;
	}
	
	// 1건 조회
	public BoardVO getOne(int bno) {
		BoardVO vo = null;
		sb.setLength(0);
		sb.append(" SELECT bno, writer, title, contents, to_char(regdate, 'yyyy-mm-dd') regdate, hits, ip, status ");
		sb.append(" FROM board ");
		sb.append(" WHERE bno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String regdate = rs.getString("regdate");
				int hits = rs.getInt("hits");
				String ip = rs.getString("ip");
				int status = rs.getInt("status");
				
				vo = new BoardVO(bno, writer, title, contents, regdate, hits, ip, status);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return vo;
	}
	
	// 1건 추가
	public void addOne(BoardVO vo) {
		sb.setLength(0);
		sb.append(" INSERT INTO board ");
		sb.append(" VALUES(BOARD_BNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?, 1)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getIp());
			
			pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 1건 변경
	public void updateOne(BoardVO vo) {
		// 제목, 내용, 작성자만 변경
		sb.setLength(0);
		sb.append(" UPDATE board ");
		sb.append(" SET writer = ?, title = ?, contents = ? ");
		sb.append(" WHERE bno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());;
			pstmt.setString(3, vo.getContents());
			pstmt.setInt(4, vo.getBno());
			
			pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 1건 삭제
	public void deleteOne(int bno) {
		// 게시물번호로 게시물 삭제
		sb.setLength(0);
		sb.append(" DELETE FROM board ");
		sb.append(" WHERE bno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 조회수 증가
	public void raiseHits(int bno) {
		sb.setLength(0);
		sb.append(" UPDATE board ");
		sb.append(" SET hits = hits + 1 ");
		sb.append(" WHERE bno = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			close();
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
}
