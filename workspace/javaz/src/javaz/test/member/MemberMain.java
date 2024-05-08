package javaz.test.member;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MemberMain {

	private Scanner sc;
	private MemberVO mvo;
	private MemberDAO mdao;
	private MemberLogin ml;

	// 0. 멤버필드들을 초기화하는 생성자
	MemberMain() {
		sc = new Scanner(System.in);
		mvo = new MemberVO("", "", "", "", "", "");
		mdao = new MemberDAO();
		ml = new MemberLogin();
	}

	// 1. 메뉴화면
	public void menu() {

		System.out.println();
		System.out.println(">> MEMBER only SYSTEM MAIN --------");
		System.out.println("     1.가입   2.로그인   3.아이디/비밀번호 찾기   4.시스템 종료");
		System.out.print(">> 선택 : ");
		String menu = sc.nextLine();
		switch (menu) {
		case "1":
			join();
			break;
		case "2":
			login();
			break;
		case "3":
			findIdPw();
			break;
		case "4":
			System.out.println(">> 시스템을 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("     ERROR: 1, 2, 3 중 하나를 입력해주세요...");
			menu();
		}

	}

	// 2. 로그인화면
	public void login() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 로그인 --------");
		System.out.print("     아이디 : ");
		String idInput = sc.nextLine();
		System.out.print("     비밀번호 : ");
		String pwInput = sc.nextLine();

		if (!ml.loginChk(idInput, pwInput)) {
			System.out.println(">> 아이디 또는 비밀번호가 일치하지 않습니다.");
			while (true) {
				System.out.println("     1.재로그인     2.아이디/비밀번호 찾기     3.MAIN으로");
				System.out.print(">> 선택 : ");
				String menu = sc.nextLine();
				switch (menu) {
				case "1":
					login();
					break;
				case "2":
					findIdPw();
					break;
				case "3":
					menu();
					break;
				default:
					System.out.println("     ERROR: 1, 2 중 하나를 입력해주세요...");
				}
			}
		} else if (idInput.equals("admin")) {
			adminMenu();
		} else
			mvo = mdao.selectMemberById(idInput);
			memberMenu();
	}

	// 3. 관리자 메뉴
	public void adminMenu() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 관리자 모드 --------");
		System.out.println("     1.회원 목록     2.회원 강퇴     3.로그아웃");
		System.out.print(">> 선택 : ");
		String menu = sc.nextLine();

		switch (menu) {
		case "1":
			memberList();
			break;
		case "2":
			kick();
			break;
		case "3":
			logout();
			break;
		default:
			System.out.println("     ERROR: 1, 2, 3 중 하나를 입력해주세요...");
			adminMenu();
		}
	}

	// 3.1 전체 회원 목록 보기
	public void memberList() {
		System.out.println();
		System.out.println("  아이디  |  이름  |  이메일  |  가입일자  ");
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss");
		for (MemberVO member : mdao.selectMember()) {
			System.out.println(member.getId() + "|" + member.getName() + "|" + member.getEmail() + "|"
					+ dateFmt.format(member.getJoinDate()));
		}

		System.out.println();
		System.out.println(">> 뒤로 가려면 아무 키나 입력하세요.");
		sc.nextLine();
		adminMenu();
	}

	// 3.2 회원 강퇴
	public void kick() {
		System.out.println();
		System.out.println(">> 강퇴시킬 회원의 ID를 입력해주세요.");
		while (true) {
			System.out.print("     ID : ");
			String idInput = sc.nextLine();
			if (mdao.selectMemberById(idInput) == null && idInput.equals("")) {
				System.out.println(">> error : 아이디을 입력해주세요.");
			} else if (mdao.selectMemberById(idInput) == null) {
				System.out.println(">> 가입되지 않은 아이디입니다.");
				System.out.println("   관리자 메뉴로 돌아갑니다.");
				adminMenu();
				break;
			} else {
				System.out.println(">> 정말 '" + idInput + "' 회원을 강퇴시키겠습니까?");
				while (true) {
					System.out.println(">>     1.예    2.아니오");
					System.out.print(">> 선택 : ");
					String yn = sc.nextLine();
					switch (yn) {
					case "1":
						if (mdao.withdrawMember(mdao.selectMemberById(idInput))) {
							System.out.println(">> 회원 강퇴 완료하였습니다.");
						} else {
							System.out.println(">> 회원 강퇴에 실패하였습니다.");
						}
						break;
					case "2":
						System.out.println(">> 회원 강퇴를 취소하였습니다.");
						break;
					default:
						System.out.println("     ERROR: 1, 2 중 하나를 입력해주세요...");
					}
					System.out.println(">> 관리자 MAIN으로 이동합니다.");
					adminMenu();
					break;
				}
				break;
			}
		}
	}

	// 4. 회원 전용 메뉴
	public void memberMenu() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 사용자 모드 --------");
		System.out.println("     1.내 정보     2.비밀번호 변경     3.로그아웃     4.회원 탈퇴");
		System.out.print(">> 선택 : ");
		String menu = sc.nextLine();

		switch (menu) {
		case "1":
			memberInfo();
			break;
		case "2":
			changePw();
			break;
		case "3":
			logout();
			break;
		case "4":
			Withdrawal();
			break;
		default:
			System.out.println("     ERROR: 1, 2, 3 중 하나를 입력해주세요...");
			memberMenu();
		}

	}

	// 4.1 내 정보 보기
	public void memberInfo() {
		System.out.println();

		System.out.println("     아이디 : " + mvo.getId());
		System.out.println("     이름 : " + mvo.getName());
		System.out.println("     이메일 : " + mvo.getEmail());
		System.out.println("     사진 : " + mvo.getPhoto());
		System.out.println("     성별(F/M) : " + mvo.getGender());
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss");
		System.out.println("     가입일자 : " + dateFmt.format(mvo.getJoinDate()));

		System.out.println();
		System.out.println(">> 뒤로 가려면 아무 키나 입력하세요.");
		sc.nextLine();
		memberMenu();
	}

	// 4.2 비밀번호 변경
	public void changePw() {
		System.out.println();
		System.out.println(">> 현재 비밀번호를 입력해주세요.");
		System.out.print(">> 현재 비밀번호 : ");
		String curPw = sc.nextLine();
		if (!curPw.equals(mvo.getPw())) {
			System.out.println(">> 비밀번호가 일치하지 않습니다.");
			while (true) {
				System.out.println();
				System.out.println("     1.재시도     2.뒤로");
				System.out.print(">> 선택 : ");
				String menu = sc.nextLine();
				switch (menu) {
				case "1":
					changePw();
					break;
				case "2":
					memberMenu();
					break;
				default:
					System.out.println("     ERROR: 1, 2 중 하나를 입력해주세요...");
				}
			}
		} else {
			System.out.println();
			System.out.println(">> 새로운 비밀번호를 입력해주세요.");
			System.out.print(">> 새 비밀번호 : ");
			String newPw = sc.nextLine();
			while (true) {
				if (newPw.length() < 12) {
					System.out.println(">> error : 비밀번호는 12자 이상으로 입력해주세요.");
				} else {
					mvo.setPw(newPw);
					MemberLogin.getMemberMap().put(mvo.getId(), newPw);
					System.out.println(">> 비밀번호를 변경하였습니다.");
					System.out.println("   다시 로그인해주세요.");
					break;
				}
			}
			menu();
		}
	}

	// 4.3 회원 탈퇴
	public void Withdrawal() {
		System.out.println();
		System.out.println(">> 탈퇴를 위해 현재 비밀번호를 입력해주세요.");
		System.out.print(">> 현재 비밀번호 : ");
		String curPw = sc.nextLine();
		if (!curPw.equals(mvo.getPw())) {
			System.out.println(">> 비밀번호가 일치하지 않습니다.");
			while (true) {
				System.out.println();
				System.out.println("     1.재시도     2.뒤로");
				System.out.print(">> 선택 : ");
				String menu = sc.nextLine();
				switch (menu) {
				case "1":
					changePw();
					break;
				case "2":
					memberMenu();
					break;
				default:
					System.out.println("     ERROR: 1, 2 중 하나를 입력해주세요...");
				}
			}
		} else {
			System.out.println();
			System.out.println(">> 정말 회원을 탈퇴하시겠습니까?");
			while (true) {
				System.out.println(">>     1.예    2.아니오");
				System.out.print(">> 선택 : ");
				String yn = sc.nextLine();
				switch (yn) {
				case "1":
					if (mdao.withdrawMember(mvo)) {
						System.out.println(">> 회원 탈퇴 완료되었습니다.");
						System.out.println("   MAIN으로 돌아갑니다...");
						menu();
					} else {
						System.out.println(">> 회원 가입에 실패하였습니다.");
						System.out.println(">> MAIN으로 이동합니다...");
					}
					break;
				case "2":
					System.out.println(">> 회원 MAIN으로 돌아갑니다.");
					memberMenu();
					break;
				default:
					System.out.println("     ERROR: 1, 2 중 하나를 입력해주세요...");
				}
			}
		}
	}

	// 5. 회원가입
	public void join() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 회원 가입 --------");

		if (mdao.insertMember()) {
			System.out.println(">> 회원 가입이 완료되었습니다.");
			System.out.println(">> 로그인 후 이용해 주세요.");
		} else {
			System.out.println(">> 회원 가입에 실패하였습니다.");
			System.out.println(">> MAIN으로 이동합니다...");
		}
		menu();
	}

	// 6. 로그아웃
	public void logout() {
		System.out.println();
		System.out.println("로그아웃합니다...");
		menu();
	}

	// 7. 아이디/비밀번호 찾기
	public void findIdPw() {
		while (true) {
			System.out.println("     1.아이디 찾기     2.비밀번호 찾기     3.뒤로 가기");
			String idpw = sc.nextLine();
			switch (idpw) {
			case "1":
				findId();
				menu();
				break;
			case "2":
				findPw();
				menu();
				break;
			case "3":
				menu();
				break;
			default:
				System.out.println("     ERROR: 1, 2 3 중 하나를 입력해주세요...");
			}
		}
	}

	// 7.1 아이디 찾기
	public void findId() {
		System.out.println();
		System.out.println(">> 이메일을 입력해주세요.");
		while (true) {
			System.out.print("     이메일 : ");
			String emailInput = sc.nextLine();
			if (mdao.selectMemberByEmail(emailInput) == null && emailInput.equals("")) {
				System.out.println(">> error : 이메일을 입력해주세요.");
			} else if (mdao.selectMemberByEmail(emailInput) == null && !emailInput.contains("@")) {
				System.out.println(">> error : 이메일을 올바르게 입력해주세요.");
				System.out.println("          (ex. ~~~~~ @ ~~ . ~~)");
			} else if (mdao.selectMemberByEmail(emailInput) == null) {
				System.out.println(">> 가입되지 않은 이메일입니다.");
				break;
			} else {
				System.out.println(">> 귀하의 아이디는 '" + mdao.selectMemberByEmail(emailInput).getId() + "'입니다.");
				break;
			}
		}
	}

	// 7.2 비밀번호 찾기
	public void findPw() {
		System.out.println();
		System.out.println(">> 아이디를 입력해주세요.");
		while (true) {
			System.out.print("     아이디 : ");
			String idInput = sc.nextLine();
			if (mdao.selectMemberById(idInput) == null && idInput.equals("")) {
				System.out.println(">> error : 아이디를 입력해주세요.");
			} else if (mdao.selectMemberById(idInput) == null) {
				System.out.println(">> 가입되지 않은 아이디입니다.");
				break;
			} else {
				System.out.println(">> 귀하의 비밀번호는 '" + mdao.selectMemberById(idInput).getPw() + "'입니다.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		new MemberMain().menu();
	}
}
