package javaz.test.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//DAO ; Data Access Object

public class MemberDAO {
	// Database 대신 사용
	private static List<MemberVO> memberList;

	MemberDAO() {
		memberList = new ArrayList<MemberVO>();
	}

//	public void setMemberList(List<MemberVO> memberList) {
//		this.memberList = memberList;
//	}

	// 회원가입
	public boolean insertMember() {
		MemberVO mvo = new MemberVO();
		
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("     아이디 : ");
			String idInput = sc.nextLine();
			if (idInput.equals("")) {
				System.out.println(">> error : 아이디를 입력해주세요.");
			} else if (idInput.contains("admin")) {
				System.out.println(">> error : 'admin'은 아이디에 포함할 수 없습니다.");
			} else if (MemberLogin.getMemberMap().containsKey(idInput)) {
				System.out.println(">> error : 중복된 아이디입니다. 다른 아이디를 입력해주세요.");
			} else {
				mvo.setId(idInput);
				break;
			}
		}

		while (true) {
			System.out.print("     비밀번호 : ");
			String pwInput = sc.nextLine();
			if (pwInput.length() < 12) {
				System.out.println(">> error : 비밀번호는 12자 이상으로 입력해주세요.");
			} else {
				mvo.setPw(pwInput);
				break;
			}
		}

		while (true) {
			System.out.print("     이름 : ");
			String nameInput = sc.nextLine();
			if (nameInput.equals("")) {
				System.out.println(">> error : 이름을 입력해주세요.");
			} else {
				mvo.setName(nameInput);
				break;
			}
		}

		while (true) {
			System.out.print("     이메일 : ");
			String emailInput = sc.nextLine();
			if (emailInput.equals("")) {
				System.out.println(">> error : 이메일을 입력해주세요.");
			} else if (MemberLogin.getMemberMap().containsKey(emailInput)) {
				System.out.println(">> error : 이미 가입된 이메일입니다.");
			} else if (!emailInput.contains("@")) {
				System.out.println(">> error : 이메일을 올바르게 입력해주세요.");
				System.out.println("          (ex. ~~~~~ @ ~~ . ~~)");
			} else {
				mvo.setEmail(emailInput);
				break;
			}
		}

		System.out.print("     (선택)사진 : ");
		String photoInput = sc.nextLine();
		mvo.setPhoto(photoInput);

		while (true) {
			System.out.print("     (선택)성별(F/M) : ");
			String genderInput = sc.nextLine();
			if (!(genderInput.equals("F") || genderInput.equals("M") || genderInput.equals(""))) {
				System.out.println(">> error : F/M(대문자) 또는 공란으로 입력해주세요.");
			} else {
				mvo.setGender(genderInput);
				break;
			}
		}
		
		// 1. MemberVO 객체에 오늘 날짜를 저장한 후 memberList에 mvo를 담기
		mvo.setJoinDate(new Date());
		
		for (MemberVO m : memberList) {
			System.out.println(m.getId());
		}

		if (memberList.add(mvo)) {
			// 2. memberMap 객체에 아이디와 비밀번호 저장
			MemberLogin.getMemberMap().put(mvo.getId(), mvo.getPw());
			
			System.out.println(MemberLogin.getMemberMap());
			
			for (MemberVO m : memberList) {
				System.out.println(m.getId());
			}
			
			return true;
		} else {
			return false;
		}
	}

	// 전체 회원목록
	public List<MemberVO> selectMember() {
		return memberList;
	}

	// 아이디찾기(이메일로 vo 객체 설정)
	public MemberVO selectMemberByEmail(String inputEmail) {
		MemberVO rm = null;
		for (MemberVO member : selectMember()) {
			if (inputEmail.equals(member.getEmail())) {
				rm = member;
			}
		}
		return rm;
	}

	// 비밀번호 찾기(아이디로 vo 객체 설정)
	public MemberVO selectMemberById(String inputId) {
		MemberVO rm = null;
		for (MemberVO member : selectMember()) {
			if (inputId.equals(member.getId())) {
				rm = member;
			}
		}
		return rm;
	}

	// 회원 탈퇴
	public boolean withdrawMember(MemberVO mvo) {
		if (memberList.remove(mvo)) {
			// 2. memberMap 객체에 아이디와 비밀번호 삭제
			MemberLogin.getMemberMap().remove(mvo.getId());
			return true;
		} else {
			return false;
		}
	}
}
