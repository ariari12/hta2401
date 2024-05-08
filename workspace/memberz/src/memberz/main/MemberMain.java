package memberz.main;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import memberz.dao.MemberDAO;
import memberz.vo.MemberVO;

public class MemberMain {
	public static String loginId;
	private Scanner sc;
	private MemberVO mvo;
	private MemberDAO mdao;
	private SurveyMain survey;

	// 0. 멤버필드들을 초기화하는 생성자
	public MemberMain() {
		sc = new Scanner(System.in);
		mvo = MemberVO.getInstance();
		mdao = new MemberDAO();
		survey = new SurveyMain();
	}

	// 1. 메인메뉴
	private void menu() {
		while (true) {
			System.out.println(">> MEMBER only SYSTEM MAIN ----------------------");
			System.out.println("   1.가입   2.로그인   3.ID찾기   4.PW찾기   5.시스템 종료");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine().trim();

			// 메뉴 번호 검증
			int num = 0;
			try {
				num = Integer.parseInt(input);

				if (num < 1 || num > 5) {
					System.out.println(">> 1 / 2 / 3 / 4 / 5 중 하나의 숫자를 입력해 주세요.");
					System.out.println();
				}

			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
				System.out.println();
			}

			if (num == 1) { join(); }
			if (num == 2) { login(); }
			if (num == 3) { findId(); }
			if (num == 4) { findPw(); }
			if (num == 5) {
				System.out.println(">> 시스템을 종료합니다.");
				sc.close();
				System.exit(0);
			}
		}
	}

	// 2. 로그인 -- DAO에서 진행
	public void login() {
		while (true) {
			System.out.println();
			System.out.println(">> MEMBER only SYSTEM 로그인 ---------");
			System.out.print("   아이디 : ");
			String id = sc.nextLine().trim();
			System.out.print("   비밀번호 : ");
			String pw = sc.nextLine().trim();

			if (mdao.pwChk(id, pw)) {
				mvo = mdao.selectMember(id);
//				System.out.println(">> " + mvo.getName() + "님 환영합니다.");

				loginId = id;

				if (mvo.getAdmin()) {
					adminMenu();
					break;
				} else {
					memberMenu();
					break;
				}

			} else {
				System.out.println(">> 아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println();
				break;
			}
		}
	}

	// 3. 관리자 메뉴
	public void adminMenu() {
		while (true) {
			System.out.println();
			System.out.println(">> MEMBER only SYSTEM 관리자 모드 --------------");
			System.out.println("   1.회원목록   2.회원정보조회   3.설문관리   4.로그아웃");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine().trim();

			// 메뉴 번호 검증
			int num = 0;
			try {
				num = Integer.parseInt(input);

				if (num < 1 || num > 4) {
					System.out.println(">> 1 / 2 / 3 / 4 중 하나의 숫자를 입력해 주세요.");
				}

			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
			}

			if (num == 1) { memberList(); }
			if (num == 2) { memberInfoAdmin(); }
			if (num == 3) { survey.menu(); }
			if (num == 4) {
				logout();
				break;
			}
		}

	}

	// 3.1 전체 회원 목록 보기
	public void memberList() {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy. MM. dd a hh:mm:ss");
		// 테스트용 계정을 생성하지 않았을 경우 - null인지 확인하는 조건이 추가되어야 함
//			if (mdao.selectMember() == null || mdao.selectMember().size() < 1) {
		// 테스트용 계정만 있을 경우
		if (mdao.selectMember().size() < 1) {
			System.out.println(">> 등록된 회원이 없습니다.");
		} else {
			System.out.println();
		}

		System.out.printf("%5s\t|%4s\t|%8s\t|%14s\n", "아이디", "이름", "이메일", "가입일자");
		System.out.println("-----------------------------------------------------------");

		for (MemberVO member : mdao.selectMember()) {
			System.out.printf("%s\t|%s\t|%s\t|" 
					+ dateFmt.format(member.getJoinDate()) + "\n", 
					member.getId(), member.getName(), member.getEmail());
		}
	}

	// 3.2 회원 삭제
	public void deleteMember(String id) {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 회원 삭제 -----");
		System.out.print("   정말로 " + id + " 계정을 삭제하시겠습니까?(y): ");
		String input = sc.nextLine().trim();

		if (input.equalsIgnoreCase("y")) {
			boolean isDeleted = mdao.deleteMember(id);

			if (isDeleted) {
				System.out.println(">> " + id + " 계정이 삭제되었습니다.");
				return;
			}

			System.out.println(">> 존재하지 않는 회원입니다.");
		} else {
			System.out.println(">> 회원삭제를 취소합니다.");
			System.out.println(">> 메뉴로 돌아갑니다.");
		}
	}
	
	// 3.3 특정 회원 정보 보기
	public void memberInfoAdmin() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 특정 회원 조회 -----");
		System.out.print("   조회할 회원 아이디: ");
		String id = sc.nextLine().trim();
		
		
		if ( (mvo = mdao.selectMember(id)) == null) {
			System.out.println(">> " + id + " 아이디로 등록된 회원정보가 없습니다.");
			return;
		}

		System.out.println("   아이디\t : " + mvo.getId());
		System.out.println("   이름\t : " + mvo.getName());
		System.out.println("   이메일\t : " + mvo.getEmail());
		System.out.println("   사진\t : " + mvo.getPhoto());
		System.out.println("   성별(F/M) : " + mvo.getGender());
		
		updateInfoMenu(id);
	}

	// 4. 회원 메뉴
	public void memberMenu() {
		while (true) {
			if (mdao.selectMember(loginId) == null) {
				System.out.println(">> 회원 메뉴 불러오기 실패!");
				System.out.println(">> 존재하지 않는 회원입니다.");
				System.out.println(">> 메인 메뉴로 돌아갑니다.");
				System.out.println();
				break;
			}

			System.out.println();
			System.out.println(">> MEMBER only SYSTEM 사용자 모드 ------------");
			System.out.println("   1.내 정보   2.비밀번호 변경   3.설문   4.로그아웃");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine().trim();

			// 메뉴 번호 검증
			int num = 0;
			try {
				num = Integer.parseInt(input);

				if (num < 1 || num > 4) {
					System.out.println(">> 1 / 2 / 3 / 4 중 하나의 숫자를 입력해 주세요.");
				}

			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
			}

			if (num == 1) { memberInfo(); }
			if (num == 2) { changePw(); }
			if (num == 3) { survey.attendMenu(); }
			if (num == 4) {
				logout();
				break;
			}
		}
	}

	// 4.1 내 정보 보기
	public void memberInfo() {
		mvo = mdao.selectMember(loginId);

		System.out.println("   아이디\t : " + mvo.getId());
		System.out.println("   이름\t : " + mvo.getName());
		System.out.println("   이메일\t : " + mvo.getEmail());
		System.out.println("   사진\t : " + mvo.getPhoto());
		System.out.println("   성별(F/M) : " + mvo.getGender());
		
		updateInfoMenu(loginId);
	}
	
	// 3.3, 4.1 +) 정보 수정 / 삭제(탈퇴) / 종료 메뉴 선택
	public void updateInfoMenu(String id) {
		while (true) {
			System.out.println();
			System.out.println("   1.수정   2.삭제(탈퇴)   3.종료");
			System.out.print(">> 선택 : ");
			String input = sc.nextLine().trim();
	
			// 메뉴 번호 검증
			int num = 0;
			try {
				num = Integer.parseInt(input);
	
				if (num < 1 || num > 3) {
					System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
				}
	
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
			}
	
			if (num == 1) {
				updateInfo(id);
			}
			if (num == 2) {
				boolean isAdmin = mdao.selectMember(loginId).getAdmin();
				if (isAdmin) { deleteMember(id); } 
				else { 
					withdraw();
					return;
				}
			}
			if (num == 3) {
				break;
			}
		}
	}
	
	// 3.3, 4.1 +) 정보 수정
	public void updateInfo(String id) {
		boolean isAdmin = mdao.selectMember(loginId).getAdmin();
		MemberVO mvo = mdao.selectMember(id);
		String newId = ""; 
		String newPw = ""; 
		String newName = "";
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 정보  -----");
		
		if (isAdmin) {
			System.out.print("   변경할 아이디(기존값: " + id + "): ");
			newId = sc.nextLine().trim();
			System.out.print("   변경할 비밀번호(기존값: " + mvo.getPw() + "): ");
			newPw = sc.nextLine().trim();
			System.out.print("   변경할 이름(기존값: " + mvo.getName() + "): ");
			newName = sc.nextLine().trim();
		}
		
		System.out.print("   변경할 이메일(기존값: " + mvo.getEmail() + "): ");
		String newEmail = sc.nextLine().trim();
		System.out.print("   변경할 사진(기존값: " + mvo.getPhoto() + "): ");
		String newPhoto = sc.nextLine().trim();
		System.out.print("   변경할 성별(기존값: " + mvo.getGender() + "): ");
		String newGender = sc.nextLine().trim();
		
		MemberVO updateInfo 
			= new MemberVO(newId, newPw, newName, newEmail, newPhoto, newGender);
		
		if (mdao.updateMember(id, updateInfo)) {
			System.out.println(">> 회원 정보 업데이트가 완료되었습니다.");
		} else {
			System.out.println(">> 회원 정보 업데이트에 실패했습니다.");
		}
		
	}
	
	// 4.2 비밀번호 변경하기
	public void changePw() {
		System.out.println(">> 비밀번호를 변경합니다.");
		System.out.print("   기존 비밀번호 : ");
		String currentPW = sc.nextLine().trim();

		if (mdao.pwChk(loginId, currentPW)) {
			System.out.print("   신규 비밀번호 : ");
			String newPW = sc.nextLine().trim();

			if (newPW.equals("")) {
				System.out.println(">> 값은 비어있으면 안 됩니다.");
				return;
			}

			if (currentPW.equals(newPW)) {
				System.out.println(">> 신규 비밀번호가 기존 비밀번호와 동일합니다.");
				return;
			}

			mdao.updatePw(loginId, newPW);
			System.out.println(">> 비밀번호가 변경되었습니다.");
			return;
		}

		System.out.println(">> 변경 실패! 기존 비밀번호가 틀립니다.");
	}
	
	// 5. 회원 가입
	public void join() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 회원 가입 ----");
		System.out.print("   아이디\t : ");
		String id = sc.nextLine().trim();
		// 아이디 중복체크
		if (mdao.isIdDuplicated(id)) {
			System.out.println(">> 중복된 아이디입니다.");
			System.out.println(">> 메인 메뉴로 돌아갑니다.");
			System.out.println();
			return;
		}

		System.out.print("   비밀번호 : ");
		String pw = sc.nextLine().trim();
		System.out.print("   이름\t : ");
		String name = sc.nextLine().trim();
		System.out.print("   이메일\t : ");
		String email = sc.nextLine().trim();
		System.out.print("   사진\t : ");
		String photo = sc.nextLine().trim();
		System.out.print("   성별(F/M) : ");
		String gender = sc.nextLine().trim();

		boolean isInsert = mdao.insertMember(new MemberVO(id, pw, name, email, photo, gender));

		if (isInsert) {
			System.out.println(">> 회원 가입이 완료되었습니다.");
			System.out.println(">> 로그인 후 이용해 주세요.");
		} else {
			System.out.println(">> 회원 가입에 실패하였습니다.");
			System.out.println(">> 잠시 후 다시 이용해주세요.");
		}

		System.out.println();
	}

	// 6. 로그아웃
	public void logout() {
		mdao.logout(loginId);
		System.out.println();
		return;
	}

	// 7. 아이디 찾기
	public void findId() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 아이디 찾기 ----");
		System.out.print("   이름: ");
		String name = sc.nextLine().trim();
		System.out.print("   이메일: ");
		String email = sc.nextLine().trim();
		String id = mdao.selectId(name, email);

		if (id.equals("")) {
			System.out.println(">> 해당 정보로 등록된 회원정보가 없습니다.");
		} else {
			System.out.println(">> 아이디: " + id);
		}

	}

	// 8. 비밀번호 찾기
	public void findPw() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 비밀번호 찾기 ----");
		System.out.print("   아이디: ");
		String id = sc.nextLine().trim();
		System.out.print("   이름: ");
		String name = sc.nextLine().trim();
		System.out.print("   이메일: ");
		String email = sc.nextLine().trim();

		boolean isReset = mdao.selectPw(id, name, email);

		if (isReset) {
			System.out.println(">> " + id + " 계정의 임시 비밀번호를 발급하였습니다.");
			System.out.println(">> 로그인 후 비밀번호를 변경해주세요.");
			System.out.println(">> 임시 비밀번호: " + "1");
		} else {
			System.out.println(">> 해당 정보로 등록된 회원정보가 없습니다.");
		}

	}

	// 9. 회원 탈퇴
	public void withdraw() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 회원탈퇴 -----");
		System.out.print("   정말로 탈퇴하시겠습니까?(y): ");
		String input = sc.nextLine().trim();

		if (input.equalsIgnoreCase("y")) {
			System.out.print("   탈퇴 처리를 위해 비밀번호를 입력해주세요: ");
			String inputPw = sc.nextLine().trim();
			boolean isDeleted = mdao.deleteMember(loginId, inputPw);

			if (isDeleted) {
				System.out.println(">> 탈퇴가 완료되었습니다.");
				System.out.println();
				return;
			}

			System.out.println(">> 비밀번호가 일치하지 않습니다.");
		} else {
			System.out.println(">> 회원 탈퇴를 취소했습니다.");
		}

	}

	public static void main(String[] args) {
		new MemberMain().menu();
	}

}
