package javaz.util.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// DAO: Data Access Object
public class MemberDAO {
	// Database 대신 사용 - static 뺄 경우 비번 변경 후 로그인 안 됨
	private static List<MemberVO> memberList;
	private MemberVO mvo;
	
	// MembersLogin과 동일하게 값 입력 필요하지 않은지??
	public MemberDAO() {
		memberList = new ArrayList<MemberVO>();
		memberList.add(new MemberVO("admin", "1234", "nadmin", "", "", ""));
		memberList.add(new MemberVO("aaa", "1111", "naaa", "", "", ""));
		memberList.add(new MemberVO("bbb", "2222", "nbbb", "", "", ""));
		memberList.add(new MemberVO("ccc", "3333", "nccc", "", "", ""));
		
		mvo = MemberVO.getInstance();
		
		selectMember("admin").setAdmin(true);
	}
	
	// 회원가입 - 아이디 중복검사
	public boolean isIdDuplicated(String id) {
		for (MemberVO member : memberList) {
			if (member.getId().equals(id)) {
				return true;
			}
		}
		return false;
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
		if (!mvo.getEmail().contains("@")
				|| (!mvo.getEmail().contains(".com") 
				&& !mvo.getEmail().contains(".net") 
				&& !mvo.getEmail().contains(".co.kr")) ) {
			System.out.println(">> 이메일 형식이 올바르지 않습니다.");
			return false;
		}
		
		// 성별 입력 확인
		if (mvo.getGender().equalsIgnoreCase("F")) {
			mvo.setGender("F");
		} else if (mvo.getGender().equalsIgnoreCase("M")) {
			mvo.setGender("M");
		} else if (mvo.getGender().equals("")) {
			
		} else {
			System.out.println(">> 성별은 F 혹은 M을 입력해주세요. (공란 가능)");
			return false;
		}
		
		// 아이디에 admin이 포함될 경우 admin 계정으로 생성 - 대소문자 관계없이 하려면 어떻게?
		if (mvo.getId().contains("admin")) { mvo.setAdmin(true); }
		
		// 1. MemberVO 객체에 오늘 날짜를 저장한 후
		//    memberList에 mvo에 담기
		mvo.setJoinDate(new Date());
		memberList.add(mvo);
		
		// 2. memberMap 객체에 아이디와 비밀번호 저장
		MemberLogin.memberMap.put(mvo.getId(), mvo.getPw());
		
		return true;
	}
	
	// 전체 회원목록
	public List<MemberVO> selectMember() {
		// memberList를 반환
		return memberList;
	}

	// 아이디로 사용자 정보 접근
	public MemberVO selectMember(String id) {
		for (MemberVO member : memberList) {
			if (mvo != null && member.getId().equals(id)) {
				return member;
			}
		}
		return null;
	}
	
	// 이름으로 사용자 아이디 접근
	public String getIdByName(String name) {
		for (MemberVO member : memberList) {
			if (member.getName().equals(name)) {
				return member.getId();
			}
		}
		return "";
	}
	
	// 비밀번호 초기화
	public boolean resetPw(String id) {
		mvo = selectMember(id);
		
		if (mvo != null) {
			mvo.setPw("1");
			return true;
		}
		
		return false;
	}
	
	// 비번 변경 / 로그인 - 비밀번호 조회하여 동일한지 확인
	public boolean pwChk(String id, String pw) {
		mvo = selectMember(id);
		
		if (mvo.getPw().equals(pw)) {
			return true;
		}
		
		return false;
	}
	
	// 비번 변경 - 입력받은 비밀번호로 업데이트
	public void updatePw(String id, String newPW) {
		mvo = selectMember(id);
		
		mvo.setPw(newPW);
		// 로그아웃 시 Map에서 값 삭제하는 경우 없애도 됨
		//	로그인 때마다 List에서 검증하여 Map에 추가하기 때문
		MemberLogin.memberMap.replace(id, newPW); 
	}
	
	// 로그인 체크
	public boolean existChk(String id, String pw) {
		mvo = selectMember(id);
		
		if (pwChk(id, pw)) {
			MemberLogin.memberMap.put(mvo.getId(), mvo.getPw());
			return true;
		}
		
		return false;
	}
	
	// 회원삭제(관리자)
	public boolean removeMember(String id) {
		mvo = selectMember(id);
		
		if (mvo == null) {
			return false;
		}
		
		memberList.remove(mvo);
		MemberLogin.memberMap.remove(id);
		return true;
	}
	
	// 회원탈퇴(일반 사용자) - List와 Map에서 삭제
	public boolean removeMember(String id, String pw) {
		mvo = selectMember(id);
		
		if (mvo != null && !pw.equals(mvo.getPw())) {
			return false;
		}
		
		memberList.remove(mvo);
		MemberLogin.memberMap.remove(id);
		return true;
	}
	
}
