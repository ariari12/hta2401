package memberz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import memberz.common.DBConn;
import memberz.vo.MemberVO;

public class MemberDAO {
	private String query;
	private PreparedStatement pstmt;
	private boolean result;
	private ResultSet rs;
	
	// MembersLogin과 동일하게 값 입력 필요하지 않은지??
	public MemberDAO() {  }

	// 회원가입 - 아이디 중복검사
	public boolean isIdDuplicated(String id) {
		query = " SELECT id FROM t_member WHERE id = '" + id + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			result = pstmt.executeUpdate() == 1;
			DBConn.close(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 회원가입
	public boolean insertMember(MemberVO mvo) {
		// 필수항목 입력 검증
		if (mvo.getId().equals("")) {
			System.out.println(">> 아이디는 필수항목입니다.");
			return false;
		} else if (mvo.getPw().equals("")) {
			System.out.println(">> 비밀번호는 필수항목입니다.");
			return false;
		} else if (mvo.getName().equals("")) {
			System.out.println(">> 이름은 필수항목입니다.");
			return false;
		} else if (mvo.getEmail().equals("")) {
			System.out.println(">> 이메일은 필수항목입니다.");
			return false;
		}

		// 이메일 검증
		if (invalidEmail(mvo)) { return false; }

		// 성별 입력 확인
		if (invalidGender(mvo)) { return false; }

		// 아이디에 admin이 포함될 경우 admin 계정으로 생성 - 대소문자 관계없이 하려면 어떻게?
		if (mvo.getId().contains("admin")) {
			mvo.setAdmin(true);
		}
		
		query = " INSERT INTO t_member VALUES(?, ?, ?, ?, ?, SYSDATE, ?) ";
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
//			pstmt.setString(1, mvo.getId().equals("") ? " " : mvo.getId());
//			pstmt.setString(2, mvo.getPw().equals("") ? " " : mvo.getPw());
//			pstmt.setString(3, mvo.getName().equals("") ? " " : mvo.getName());
//			pstmt.setString(4, mvo.getEmail().equals("") ? " " : mvo.getEmail());
//			pstmt.setString(5, mvo.getPhoto().equals("") ? " " : mvo.getPhoto());
//			pstmt.setString(6, mvo.getGender().equals("") ? " " : mvo.getGender());
			pstmt.setString(1, mvo.getPw().equals("") ? " " : mvo.getPw());
			pstmt.setString(2, mvo.getName().equals("") ? " " : mvo.getName());
			pstmt.setString(3, mvo.getEmail().equals("") ? " " : mvo.getEmail());
			pstmt.setString(4, mvo.getPhoto().equals("") ? " " : mvo.getPhoto());
			pstmt.setString(5, mvo.getGender().equals("") ? " " : mvo.getGender());
			pstmt.setString(6, mvo.getId().equals("") ? " " : mvo.getId());
			
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// 이메일 검증
	public boolean invalidEmail(MemberVO mvo) {
		if (!mvo.getEmail().contains("@") || 
				(!mvo.getEmail().contains(".com") 
				  && !mvo.getEmail().contains(".net")
				  && !mvo.getEmail().contains(".co.kr"))) {
			System.out.println(">> 이메일 형식이 올바르지 않습니다.");
			return true;
		}
		
		return false;
	}
	
	// 성별 입력값 검증
	public boolean invalidGender(MemberVO mvo) {
		if (mvo.getGender().equalsIgnoreCase("F")) {
			mvo.setGender("F");
		} else if (mvo.getGender().equalsIgnoreCase("M")) {
			mvo.setGender("M");
		} else if (mvo.getGender().equals("")) {

		} else {
			System.out.println(">> 성별은 F 혹은 M을 입력해주세요. (공란 가능)");
			return true;
		}
		
		return false;
	}

	// 전체 회원목록
	public List<MemberVO> selectMember() {
		MemberVO mvo = null;
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		query = " SELECT * FROM t_member ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mvo = new MemberVO(rs.getString("id"), rs.getString("pw"),
						rs.getString("name"), rs.getString("email"),
						rs.getString("photo"), rs.getString("gender"));
				mvo.setJoinDate(rs.getDate("joindate"));
				mvo.setAdmin(mvo.getId().equals("admin") ? true : false);
				memberList.add(mvo);
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// memberList를 반환
		return memberList;
	}

	// 아이디로 사용자 정보 접근
	public MemberVO selectMember(String id) {
		MemberVO mvo = null;
		query = " SELECT * FROM t_member WHERE id = '" + id + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mvo = new MemberVO(rs.getString("id"), rs.getString("pw"),
						rs.getString("name"), rs.getString("email"),
						rs.getString("photo"), rs.getString("gender"));
				mvo.setJoinDate(rs.getDate("joindate"));
				mvo.setAdmin(mvo.getId().equals("admin") ? true : false);
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mvo;
	}

	// 비번 변경 / 로그인 - 비밀번호 조회하여 동일한지 확인
	public boolean pwChk(String id, String pw) {
		query = " SELECT * FROM t_member WHERE id = '" + id 
				+ "' AND pw = '" + pw + "' ";

		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			result = rs.next();
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 비번 변경 - 입력받은 비밀번호로 업데이트
	public void updatePw(String id, String newPW) {
		query = " UPDATE t_member SET pw = '" + newPW 
				+ "' WHERE id = '" + id + "' ";

		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.executeUpdate();
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 체크 (사용 x)
	public boolean loginChk(String id, String pw) {
		
		if (pwChk(id, pw)) {
			
			return true;
		}

		return false;
	}
	
	// 회원정보 수정
	public boolean updateMember(String currentId, MemberVO updateInfo) {
		MemberVO nowMvo = selectMember(currentId);
		
		if (!updateInfo.getEmail().equals("") && invalidEmail(updateInfo)) { 
			return false; 
			}
		if (!updateInfo.getGender().equals("") && invalidGender(updateInfo)) { 
			return false; 
			}
		
		query = " UPDATE t_member SET id = ?, pw = ?, name = ?, email = ?,"
				+ " photo = ?, gender = ? WHERE id = ? ";
		
		try {
			// 필수인 경우에만 빈 값 체크하여 기존값 유지
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, updateInfo.getId().equals("") ? 
							currentId : updateInfo.getId());
			pstmt.setString(2, updateInfo.getPw().equals("") ? 
							nowMvo.getPw() : updateInfo.getPw());
			pstmt.setString(3, updateInfo.getName().equals("") ? 
							nowMvo.getName() : updateInfo.getName());
			pstmt.setString(4, updateInfo.getEmail().equals("") ? 
							nowMvo.getEmail() : updateInfo.getEmail());
			pstmt.setString(5, updateInfo.getPhoto().equals("") ? 
							" " : updateInfo.getPhoto());
			pstmt.setString(6, updateInfo.getGender().equals("") ? 
							" " : updateInfo.getGender());
			pstmt.setString(7, currentId);
			result = pstmt.executeUpdate() == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원삭제(관리자)
	public boolean deleteMember(String id) {
		query = " DELETE t_member WHERE id = '" + id + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 회원탈퇴(일반 사용자)
	public boolean deleteMember(String id, String pw) {
		query = " DELETE t_member WHERE id = '" + id 
				+ "' AND pw = '" + pw + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// ID 찾기
	public String selectId(String name, String email) {
		String id = "";
		query = " SELECT id FROM t_member WHERE name = '" + name 
				+ "' AND email = '" + email + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString("id");
			}
			
			DBConn.close(pstmt, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	// PW 찾기
	public boolean selectPw(String id, String name, String email) {
		query = " UPDATE t_member SET pw = '1' WHERE id = '" + id 
				+ "' AND name = '" + name 
				+ "' AND email = '" + email + "' ";
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			result = pstmt.executeUpdate() == 1;
			
			DBConn.close(pstmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 로그아웃
	public void logout(String id) {
		System.out.println(">> 로그아웃이 완료되었습니다.");
	}

}
