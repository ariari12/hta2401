package ITBookFleaMarket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ITBookFleaMarket.common.DBConn;
import ITBookFleaMarket.vo.QnAVO;

public class QnADAO {
	private String query;
	private PreparedStatement pstmt;
	private boolean result;
	private ResultSet rs;
	
	// 사용자 문의 등록
	public boolean insertQnA(QnAVO qavo) {
		query = " INSERT INTO t_qna(qNo, qId, questionTitle, questionContent, questionDate, answerState) "
				+ "VALUES (SEQ_QNO.NEXTVAL, ?, ?, ?, SYSDATE, '처리중')";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, qavo.getqId());
			pstmt.setString(2, qavo.getqTitle());
			pstmt.setString(3, qavo.getqContent());
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 전체 문의 목록 조회
	public List<QnAVO> selectQnA() {
		QnAVO qavo = null;
		List<QnAVO> questions = new ArrayList<QnAVO>();
		
		query = " SELECT * FROM t_qna ORDER BY qNo DESC ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				qavo = new QnAVO(rs.getString("qId"), rs.getString("questionTitle"),
								rs.getString("questionContent"));
				qavo.setqNo(rs.getInt("qNo"));
				qavo.setqDate(rs.getDate("questionDate"));
				qavo.setaState(rs.getString("answerState"));
				qavo.setaContent(rs.getString("answerContent"));
				qavo.setaDate(rs.getDate("answerDate"));
				
				questions.add(qavo);
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return questions;
	}
	
	// 특정 사용자 문의 목록 조회
	public List<QnAVO> selectQnA(String id) {
		QnAVO qavo = null;
		List<QnAVO> questions = new ArrayList<QnAVO>();
		
		query = " SELECT * FROM t_qna WHERE qId = ? ORDER BY qNo DESC ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				qavo = new QnAVO(rs.getString("qId"), rs.getString("questionTitle"),
								rs.getString("questionContent"));
				qavo.setqNo(rs.getInt("qNo"));
				qavo.setqDate(rs.getDate("questionDate"));
				qavo.setaState(rs.getString("answerState"));
				qavo.setaContent(rs.getString("answerContent"));
				qavo.setaDate(rs.getDate("answerDate"));
				
				questions.add(qavo);
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return questions;
	}

	// 전체에서 문의번호로 문의글 조회
	public QnAVO selectQnA(int qNo) {
		QnAVO qavo = null;
		query = " SELECT * FROM t_qna WHERE qNo = ? ORDER BY qNo DESC ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, qNo);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				qavo = new QnAVO(rs.getString("qId"), rs.getString("questionTitle"),
							rs.getString("questionContent"));
				qavo.setqNo(rs.getInt("qNo"));
				qavo.setqDate(rs.getDate("questionDate"));
				qavo.setaState(rs.getString("answerState"));
				qavo.setaContent(rs.getString("answerContent"));
				qavo.setaDate(rs.getDate("answerDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qavo;
	}

	// 전체에서 답변완료/미완료 조건으로 문의글 조회
	public List<QnAVO> selectQnA(boolean answered) {
		QnAVO qavo = null;
		List<QnAVO> questions = new ArrayList<QnAVO>();
		String condition = answered ? "=" : "!=";
		
		query = " SELECT * FROM t_qna WHERE answerState " + condition + " '답변완료'"
				+ " ORDER BY qNo DESC "; 
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				qavo = new QnAVO(rs.getString("qId"), rs.getString("questionTitle"),
						rs.getString("questionContent"));
				qavo.setqNo(rs.getInt("qNo"));
				qavo.setqDate(rs.getDate("questionDate"));
				qavo.setaState(rs.getString("answerState"));
				qavo.setaContent(rs.getString("answerContent"));
				qavo.setaDate(rs.getDate("answerDate"));
		
				questions.add(qavo);
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return questions;
	}
	
	// 특정 문의자 문의 목록에서 문의번호 조회
	public QnAVO selectQnA(String id, int qNo) {
		QnAVO qavo = null;
		List<QnAVO> questions = selectQnA(id);
		
		for (QnAVO question : questions) {
			if (question.getqNo() == qNo) {
				qavo = question;
			}
		}
		
		return qavo;
	}

	// 문의 수정(사용자)
	public boolean updateQnA(int qNo, QnAVO updateInfo) {
		query = " UPDATE t_qna SET questionTitle = ?, questionContent = ?, questionDate = SYSDATE "
				+ "WHERE qNo = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, updateInfo.getqTitle());
			pstmt.setString(2, updateInfo.getqContent());
			pstmt.setInt(3, qNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 문의가 답변 완료되었는지 검증
	public boolean hasAnswer(int qNo) {
		return selectQnA(qNo).getaState().equals("답변완료");
	}

	// 문의 삭제(공통)
	public boolean deleteQnA(int qNo) {
		query = " DELETE t_qna WHERE qNo = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, qNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 문의 답변(관리자)
	public boolean updateQnA(int qNo, String aContent) {
		query = " UPDATE t_qna SET answerState = '답변완료', answerContent = ?, answerDate = SYSDATE "
				+ "WHERE qNo = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, aContent);
			pstmt.setInt(2, qNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 문의 답변 삭제(관리자)
	public boolean updateQnA(int qNo) {
		query = " UPDATE t_qna SET answerContent = NULL, answerDate = NULL, answerState = '처리중' "
				+ "WHERE qNo = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, qNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
