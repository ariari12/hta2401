package memberz.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import memberz.common.DBConn;
import memberz.main.MemberMain;
import memberz.vo.SurveyAttendVO;
import memberz.vo.SurveyVO;

public class SurveyDAO {
	private PreparedStatement pstmt;
	private String query;
	private boolean result;
	private ResultSet rs;
	
	// 관리자 - 설문조사 생성
	public boolean insertSurvey(SurveyVO svo) {
		if (invalidDate(svo.getStartDate(), svo.getEndDate()) ) {
			System.out.println(">> 종료 일자가 시작 일자보다 이릅니다.");
			return false;
		}
		
//		List<SurveyVO> surveys = selectSurvey();
		query = " INSERT INTO t_survey "
				+ "VALUES(t_survey_seq.NEXTVAL, ?, ?, ?, ?, ?, 0, 0) ";
//		query = " INSERT INTO t_survey VALUES(?, ?, ?, ?, ?, ?, 0, 0) ";
//		query = " INSERT INTO t_survey VALUES(?, ?, ?, ?, "
//				+ "TO_DATE(?, 'YY-MM-DD'), TO_DATE(?, 'YY-MM-DD'), ?, ? ";
		// TO_DATE에 값 입력하기 위해서는 기존 startDate, endDate 자료형이 String이어야 함
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
//			pstmt.setLong(1, surveys.size() + 1);
			pstmt.setString(1, svo.getTitle().equals("") ?
							" " : svo.getTitle());
			pstmt.setString(2, svo.getNum1().equals("") ?
							" " : svo.getNum1());
			pstmt.setString(3, svo.getNum2().equals("") ?
							" " : svo.getNum2());
			pstmt.setDate(4, svo.getStartDate() == null ?
					 	  Date.valueOf("1900-01-01") : 
					 		  svo.getStartDate());
			pstmt.setDate(5, svo.getEndDate() == null ?
						  new Date(System.currentTimeMillis()) : 
							  svo.getEndDate());
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관리자 - 입력받은 날짜 형식 검증
	public Date checkDate(String dateStr) {
		Date date = null;
		
		try { 
			date = Date.valueOf(dateStr);
			
		} catch (IllegalArgumentException e) {
			System.out.println(">> 연 / 월 / 일에 유효한 숫자를 입력해 주세요.");
		}
		
		return date;
	}
	
	// 관리자 - 개시 일자 종료 일자 범위 확인
	public boolean invalidDate(Date startDate, Date endDate) {
		if (startDate.toLocalDate().isAfter(endDate.toLocalDate())) {
			return true;
		}
		return false;
	}
	
	// 공통 - 전체 설문 목록 반환
	public List<SurveyVO> selectSurvey() {
		SurveyVO svo = null;
		List<SurveyVO> surveys = new ArrayList<SurveyVO>();
		
		query = " SELECT * FROM t_survey ORDER BY sno ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				svo = new SurveyVO(rs.getString("title"), rs.getString("num1"),
								   rs.getString("num2"), rs.getDate("startdate"),
								   rs.getDate("enddate"));
				svo.setsNo(rs.getInt("sno"));
				svo.setNum1Cnt(rs.getInt("num1cnt"));
				svo.setNum2Cnt(rs.getInt("num2cnt"));
				surveys.add(svo);
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return surveys;
	}
	
	// sno로 설문 조회
	public SurveyVO selectSurvey(int sNo) {
		SurveyVO svo = null;
		query = " SELECT * FROM t_survey WHERE sno = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, sNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				svo = new SurveyVO(rs.getString("title"), rs.getString("num1"), 
									rs.getString("num2"), rs.getDate("startdate"),
									rs.getDate("enddate"));
				svo.setsNo(rs.getInt("sno"));
				svo.setNum1Cnt(rs.getInt("num1cnt"));
				svo.setNum2Cnt(rs.getInt("num2cnt"));
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return svo;
	}
	
	// survey목록 - 1항목 선택 비율 계산
	public double num1Rate(SurveyVO svo) {
		if (svo.getNum1Cnt() == 0) {
			return 0;
		}
		
		return (float) svo.getNum1Cnt() 
				/ (svo.getNum1Cnt() + svo.getNum2Cnt()) * 100.0;
	}
	
	// survey목록 - 2항목 선택 비율 계산
	public double num2Rate(SurveyVO svo) {
		if (svo.getNum2Cnt() == 0) {
			return 0;
		}
		
		return (float) svo.getNum2Cnt() 
				/ (svo.getNum1Cnt() + svo.getNum2Cnt()) * 100.0;
	}
	
	// 관리자 - 설문 수정
	public boolean updateSurvey(int sNo, SurveyVO updateInfo) {
		SurveyVO nowSvo = selectSurvey(sNo);
		
		query = " UPDATE t_survey SET title = ?, num1 = ?, num2 = ?, " 
				+ "startdate = ?, enddate = ? WHERE sno = ?";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, updateInfo.getTitle().equals("") ? 
							nowSvo.getTitle() : updateInfo.getTitle());
			pstmt.setString(2, updateInfo.getNum1().equals("") ? 
							nowSvo.getNum1() : updateInfo.getNum1());
			pstmt.setString(3, updateInfo.getNum2().equals("") ?
							nowSvo.getNum2() : updateInfo.getNum2());
			pstmt.setDate(4, updateInfo.getStartDate() == null ?
						  nowSvo.getStartDate() : updateInfo.getStartDate());
			pstmt.setDate(5, updateInfo.getEndDate() == null ?
						  nowSvo.getEndDate() : updateInfo.getEndDate());
			pstmt.setInt(6, sNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 관리자 - 설문 삭제
	public boolean deleteSurvey(int sNo) {
		query = " DELETE t_survey WHERE sno = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, sNo);
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 사용자 - 설문 응답 저장 - 트랜잭션 처리(두 테이블에서 각각 insert/update가 발생하므로)
	public boolean insertAttend(SurveyAttendVO avo) {
//		List<SurveyAttendVO> attends = selectAttend();
		
		query = " INSERT INTO t_survey_attend "
				+ "VALUES(t_survey_attend_seq.NEXTVAL, ?, ?, ?, SYSDATE) ";
//		query = " INSERT INTO t_survey_attend VALUES(?, ?, ?, ?, SYSDATE) ";
		
		try {
//			DBConn.getConnection().setAutoCommit(false);
			pstmt = DBConn.getConnection().prepareStatement(query);
//			pstmt.setInt(1, attends.size() + 1);
			pstmt.setInt(1, avo.getsNo());
			pstmt.setString(2, avo.getId());
			pstmt.setInt(3, avo.getNum());
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 설문 참여 테이블에 데이터 추가에 성공한 경우
		// 설문 테이블에 해당 항목의 응답자 수를 1 증가
		if (result) { result = updateNumCnt(avo, "+"); }
		
		// 업데이트까지 성공 시 commit
//		if (result) { 
//			try {
//				DBConn.getConnection().commit();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} 
//		}
		
		return result;
	}
	
	// survey - 사용자 응답 항목에 따라 연산 기호 받아 num?cnt 값 갱신
	public boolean updateNumCnt(SurveyAttendVO avo, String symbol) {
		String property = "";
		
		if (avo.getNum() == 1) { property = "num1cnt"; }
		if (avo.getNum() == 2) { property = "num2cnt"; }
		
		query = " UPDATE t_survey SET " + property + " = " 
				+ property + " " + symbol + " 1 WHERE sno = ?";
		
		try {
//			DBConn.getConnection().setAutoCommit(false);
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, avo.getsNo());
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally에 autoCommit을 false로 설정
//			try {
//				DBConn.getConnection().setAutoCommit(true);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		
		return result;
	}
	
	// 사용자 - 모든 응답 내역 조회
	public List<SurveyAttendVO> selectAttend() {
		SurveyAttendVO avo = null;
		List<SurveyAttendVO> attends = new ArrayList<SurveyAttendVO>();
		
		query = " SELECT * FROM t_survey_attend ORDER BY ano ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				avo = new SurveyAttendVO(rs.getInt("sno"), rs.getString("id"),
										 rs.getInt("num"));
				avo.setaNo(rs.getInt("ano"));
				avo.setAttendDate(rs.getDate("attenddate"));
				attends.add(avo);
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attends;
	}
	
	// 사용자 - 아이디로 해당 아이디가 응답한 설문 목록 조회
	public List<SurveyAttendVO> selectAttend(String id) {
		SurveyAttendVO avo = null;
		List<SurveyAttendVO> attends = new ArrayList<SurveyAttendVO>();
		
		query = " SELECT * FROM t_survey_attend WHERE id = ? ORDER BY ano ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				avo = new SurveyAttendVO(rs.getInt("sno"), rs.getString("id"),
										 rs.getInt("num"));
				avo.setaNo(rs.getInt("ano"));
				avo.setAttendDate(rs.getDate("attenddate"));
				attends.add(avo);
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attends;
	}

	// 사용자 - sno의 설문 가능 기간인지 확인
	public boolean invalidPeroid(int sNo) {
		SurveyVO svo = selectSurvey(sNo);
		LocalDate startDate = svo.getStartDate().toLocalDate();
		LocalDate endDate = svo.getEndDate().toLocalDate();
		LocalDate nowDate = LocalDate.now();
		
		if (nowDate.isBefore(startDate) || nowDate.isAfter(endDate)) {
			return true;
		}
		
		return false;
	}
	
	// 사용자 - 특정 아이디가 특정 설문 번호 응답했는지 체크
	public boolean isAttended(int sNo) {
		query = " SELECT * FROM t_survey_attend WHERE id = ? AND sno = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, MemberMain.loginId);
			pstmt.setInt(2, sNo);
			
			rs = pstmt.executeQuery();
			
			result = rs.next();
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 사용자 - 아이디와 응답 번호로 응답 내역 조회
	public SurveyAttendVO selectAttend(int aNo) {
		SurveyAttendVO avo = null;
		query = " SELECT * FROM t_survey_attend WHERE id = ? AND ano = ? ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, MemberMain.loginId);
			pstmt.setInt(2, aNo);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				avo = new SurveyAttendVO(rs.getInt("sno"), rs.getString("id"),
										 rs.getInt("num"));
				avo.setaNo(rs.getInt("ano"));
				avo.setAttendDate(rs.getDate("attenddate"));
			}
			
			DBConn.close(pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avo;
	}

	// 사용자 - 아이디와 응답 선택 항목으로 응답 수정
	public boolean updateAttend(int num, SurveyAttendVO avo) {
		// 새로 입력한 값이 기존 값과 동일한 경우 바로 종료
		if (num == avo.getNum()) { return true; }
		
		// 기존 답변의 num?cnt를 -1
		result = updateNumCnt(avo, "-");
		
		if (result) {
			query = " UPDATE t_survey_attend SET num = ?, attenddate = ? "
					+ "WHERE id = ? AND sno = ? ";
			
			try {
				pstmt = DBConn.getConnection().prepareStatement(query);
				pstmt.setInt(1, num);
				pstmt.setDate(2, new Date(System.currentTimeMillis()));
				pstmt.setString(3, MemberMain.loginId);
				pstmt.setInt(4, avo.getsNo());
				
				result = pstmt.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			avo = selectAttend(avo.getaNo());
			
			if (result) {
				result = updateNumCnt(avo, "+");
			}
		}
		return result;
	}
	
	// 사용자 - 아이디와 응답 번호로 응답 삭제
	public boolean deleteAttend(int aNo) {
		updateNumCnt(selectAttend(aNo), "-");
		
		query = " DELETE t_survey_attend WHERE id = ? AND ano = ?";
		
		if (result) {
			try {
				pstmt = DBConn.getConnection().prepareStatement(query);
				pstmt.setString(1, MemberMain.loginId);
				pstmt.setInt(2, aNo);
				
				result = pstmt.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
