package memberz.main;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import memberz.dao.MemberDAO;
import memberz.dao.SurveyDAO;
import memberz.vo.SurveyAttendVO;
import memberz.vo.SurveyVO;

public class SurveyMain {
	private Scanner sc;
	private SurveyDAO sdao;
	private SurveyVO svo;
	private MemberDAO mdao;
	
	public SurveyMain() {
		sc = new Scanner(System.in);
		sdao = new SurveyDAO();
		svo = SurveyVO.getInstance();
		mdao = new MemberDAO();
	}
	
	// 관리자 - 설문관리
	public void menu() {
		while(true) {
			System.out.println();
			System.out.println(">> MEMBER only SYSTEM SURVEY 관리자 모드 -----");
			System.out.println("   1.설문목록   2.설문등록   3.관리자메뉴로");
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

			if (num == 1) { selectSurveyOption(); }
			if (num == 2) { createSurvey(); }
			if (num == 3) { break; }
		}
	}
	
	// 관리자 - 설문등록
	public void createSurvey() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 설문등록 -----");
		System.out.print("   설문 제목: ");
		String title = sc.nextLine().trim();
		System.out.print("   설문 항목1: ");
		String num1 = sc.nextLine().trim();
		System.out.print("   설문 항목2: ");
		String num2 = sc.nextLine().trim();
		
		System.out.print("   설문 시작 일자(yyyy-MM-dd): ");
		String startDateStr = sc.nextLine().trim();

		Date startDate = sdao.checkDate(startDateStr);
		if (startDate == null) { return; }
		
		System.out.print("   설문 종료 일자(yyyy-MM-dd): ");
		String endDateStr = sc.nextLine().trim();
		
		Date endDate = sdao.checkDate(endDateStr);
		if (endDate == null) { return; }
		
		
		svo = new SurveyVO(title, num1, num2, startDate, endDate);
		
		if (sdao.insertSurvey(svo)) {
			System.out.println(">> 설문 등록이 완료되었습니다.");
		} else {
			System.out.println(">> 설문 등록에 실패했습니다.");
		}
		
	}
	
	// 공통 - 설문조회 옵션 선택(진행 중인 설문/전체설문/완료된 설문)
	public void selectSurveyOption() {
		System.out.println();
		System.out.println("   설문 조회 옵션을 선택해 주세요.");
		System.out.println("   1.진행 중인 설문   2.전체설문   3.완료된 설문");
		String input = sc.nextLine().trim();
		
		int num = 0;
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 3) {
				System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}
		
		selectSurvey(num);
	}
	
	// 공통 - 조건에 맞추어 진행 중/전체/지난 설문조회
	public void selectSurvey(int num) {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy. MM. dd");
		
		System.out.println();
		while (true) {
			if (sdao.selectSurvey().size() < 1) {
				System.out.println(">> 등록된 설문이 없습니다.");
			} else {
				System.out.println();
			}
	
			System.out.printf("%3s |%10s\t|%7s|%7s |%3s|%12s\t|%12s\n", 
					"번호", "주제", "1번 선택 비율", "2번 선택 비율" , "총 응답수",
					"설문 시작 일자", "설문 종료 일자");
			System.out.println("-".repeat(90));
	
			for (SurveyVO survey : sdao.selectSurvey()) {
				// 기간 외인지 확인
				boolean invalidPeroid 
					= sdao.invalidPeroid(survey.getsNo());
				// 진행 중인 설문만 볼 경우 기간 외는 건너뛰기
				if (num == 1 && invalidPeroid) { continue; }
				// 완료된 설문만 볼 경우 기간 내는 건너뛰기
				if (num == 3 && !invalidPeroid) { continue; }
				System.out.printf("  %s  |%-15s\t| %5.1f%%   | %5.1f%%   |   %d   |" 
									+ dateFmt.format(survey.getStartDate()) 
									+ "\t|" + dateFmt.format(survey.getEndDate()) + "\n", 
									survey.getsNo(), survey.getTitle(),
									sdao.num1Rate(survey), sdao.num2Rate(survey),
									survey.getNum1Cnt() + survey.getNum2Cnt());
			}
	
			if (surveyDetailMenu() == 2) { return; }
		}
		
	}
	
	// 공통 - 특정 설문 상세보기
	public int surveyDetailMenu() {
		int num = 0;
		
		System.out.println();
		System.out.println("   1.상세보기   2.이전메뉴로");
		System.out.print(">> 선택 : ");
		String input = sc.nextLine().trim();
		
		// 메뉴 번호 검증
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 2) {
				System.out.println(">> 1 / 2 중 하나의 숫자를 입력해 주세요.");
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}
		
		if (num == 1) { surveyDetail(); }
		
		return num;
	}
	
	// 공통 - 상세 보기할 설문 번호 받기
	public void surveyDetail() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 상세보기 -----");
		
		System.out.print("   선택할 설문 번호: ");
		String input = sc.nextLine().trim();
		
		try {
			int sNo = Integer.parseInt(input);
			if ( (svo = sdao.selectSurvey(sNo)) == null ) {
				System.out.println(">> 해당하는 설문 번호가 존재하지 않습니다.");
				return;
			}
			
			System.out.println("   설문번호: " + svo.getsNo());
			System.out.println("   설문제목: " + svo.getTitle());
			System.out.println("   항목1  : " + svo.getNum1());
			System.out.println("   항목2  : " + svo.getNum2());
			
			if (mdao.selectMember(MemberMain.loginId).getAdmin()) {
				if (surveyAdmin(sNo) == 3) { return; }
			} else {
				if (attendSurvey(sNo) == 2) { return; }
			}
			
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}
		
	}
	
	// 관리자 - 설문관리메뉴
	public int surveyAdmin(int sNo) {
		int num = 0;
		
		System.out.println();
		System.out.println("   1.설문수정   2.설문삭제   3.이전메뉴");
		System.out.print(">> 선택 : ");
		String input = sc.nextLine().trim();

		// 메뉴 번호 검증
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 3) {
				System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}

		if (num == 1) { updateSurvey(sNo); }
		if (num == 2) { deleteSurvey(sNo); }
		
		return num;
	}
	
	// 관리자 - 설문수정
	public void updateSurvey(int sNo) {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 설문수정 -----");
		
		System.out.println("   변경할 제목(기존값: " + svo.getTitle() + "): ");
		System.out.print(">> ");
		String newTitle = sc.nextLine().trim();
		
		System.out.println("   변경할 항목1(기존값: " + svo.getNum1() + "): ");
		System.out.print(">> ");
		String newNum1 = sc.nextLine().trim();
		
		System.out.println("   변경할 항목2(기존값: " + svo.getNum2() + "): ");
		System.out.print(">> ");
		String newNum2 = sc.nextLine().trim();
		
		System.out.println("   변경할 설문 시작 일자(기존값: " + svo.getStartDate() + ")"
				+ "(yyyy-MM-dd): ");
		System.out.print(">> ");
		String newStartDateStr = sc.nextLine().trim();
		
		Date newStartDate = newStartDateStr.equals("") ? 
				null : sdao.checkDate(newStartDateStr);
		
		System.out.println("   변경할 설문 종료 일자(기존값: " + svo.getEndDate() + ")"
				+ "(yyyy-MM-dd): ");
		System.out.print(">> ");
		String newEndDateStr = sc.nextLine().trim();
		
		Date newEndDate = newEndDateStr.equals("") ?
				null : sdao.checkDate(newEndDateStr);
		
		SurveyVO updateInfo = new SurveyVO(newTitle, newNum1, newNum2,
				newStartDate, newEndDate);
		
		if (sdao.updateSurvey(sNo, updateInfo)) {
			System.out.println(">> 설문 정보 업데이트가 완료되었습니다.");
		} else {
			System.out.println(">> 설문 정보 업데이트에 실패했습니다.");
		}
	}
	
	// 관리자 - 설문삭제
	public void deleteSurvey(int sNo) {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 설문삭제 -----");
		
		System.out.print("   정말로 삭제하시겠습니까?(y): ");
		String input = sc.nextLine().trim();
		
		if (input.equalsIgnoreCase("y")) {
			if (sdao.deleteSurvey(sNo)) {
				System.out.println(">> 설문 삭제가 완료되었습니다.");
			} else {
				System.out.println(">> 설문 삭제에 실패했습니다.");
			}
		} else {
			System.out.println(">> 설문 삭제를 취소했습니다.");
		}
			
	}
	
	// 사용자 - 설문응답메뉴
	public void attendMenu() {
		while(true) {
			System.out.println();
			System.out.println(">> MEMBER only SYSTEM SURVEY 사용자 모드 -----");
			System.out.println("   1.설문목록   2.내 설문보기   3.회원메뉴로");
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

			if (num == 1) { selectSurveyOption(); }
			if (num == 2) { mySurvey(); }
			if (num == 3) { break; }
		}
	}
	
	// 사용자 - 설문참여메뉴
	public int attendSurvey(int sNo) {
		int num = 0;
		
		System.out.println();
		System.out.println("   1.설문참여   2.설문응답메뉴로");
		System.out.print(">> 선택 : ");
		String input = sc.nextLine().trim();
		
		// 메뉴 번호 검증
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 2) {
				System.out.println(">> 1 / 2 중 하나의 숫자를 입력해 주세요.");
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}

		if (num == 1) { goSurvey(sNo); }
		
		return num;
	}
	
	// 사용자 - 설문 답변 작성 
	public void goSurvey(int sNo) {
		// 과거에 이미 응답했었다면 바로 종료
		if (sdao.isAttended(sNo)) {
			System.out.println(">> 이미 참여한 설문입니다.");
			System.out.println(">> 이전 메뉴로 돌아갑니다.");
			return;
		}
		
		SurveyVO svo = sdao.selectSurvey(sNo);
		
		System.out.println("   주제: " + svo.getTitle());
		System.out.println("   응답하실 항목 번호를 입력해 주세요.");
		System.out.println("   항목1: " + svo.getNum1());
		System.out.println("   항목2: " + svo.getNum2());
		System.out.print(">> 입력 : ");
		String input = sc.nextLine().trim();
		
		try {
			int num = Integer.parseInt(input);

			if (num < 1 || num > 2) {
				System.out.println(">> 1 / 2 중 하나의 숫자를 입력해 주세요.");
				System.out.println(">> 설문응답 메뉴로 돌아갑니다.");
				return;
			}
			
			SurveyAttendVO avo 
				= new SurveyAttendVO(sNo, MemberMain.loginId, num);
			
			// 응답 저장 시도
			if (sdao.insertAttend(avo)) {
				System.out.println(">> 설문 응답이 완료되었습니다.");
			} else {
				System.out.println(">> 설문 응답에 실패했습니다.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
			System.out.println(">> 설문응답 메뉴로 돌아갑니다.");
		}
		
	}
	
	// 사용자 - 내 설문 보기
	public void mySurvey() {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy. MM. dd");
		
		System.out.println();
		while (true) {
			if (sdao.selectAttend(MemberMain.loginId).size() < 1) {
				System.out.println(">> 응답한 설문이 없습니다.");
			} else {
				System.out.println();
			}
	
			System.out.printf("  %s  |  %s  |  %s  | %s\n", "응답번호", "설문번호", "선택항목", "설문 응답 일자");
			System.out.println("-----------------------------------------------");
	
			for (SurveyAttendVO attend : sdao.selectAttend(MemberMain.loginId)) {
				System.out.printf("    %s    |    %s     |    %s    |" 
									+ dateFmt.format(attend.getAttendDate()) 
									+ "\t\t\n", attend.getaNo(), attend.getsNo(),
									attend.getNum());
			}
			
			if (mySurveyMenu() == 3) { break; }
		}
		
	}
	
	public int mySurveyMenu() {
		int num = 0;
		
		System.out.println();
		System.out.println("   1.응답수정   2.응답삭제   3.관리메뉴로");
		System.out.print(">> 선택 : ");
		String input = sc.nextLine().trim();

		// 메뉴 번호 검증
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 3) {
				System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}

		if (num == 1) { updateAttend(); }
		if (num == 2) { deleteAttend(); }
		
		return num;
	}
	
	// 사용자 - 응답 수정
	public void updateAttend() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 응답수정 -----");
		
		System.out.print("   변경할 응답 번호: ");
		String input = sc.nextLine().trim();
		
		SurveyAttendVO avo = null;
		try {
			int num = Integer.parseInt(input);
			if ( (avo = sdao.selectAttend(num)) == null ) {
				System.out.println(">> 해당하는 번호가 존재하지 않습니다.");
				return;
			}
			
			System.out.println("   변경할 답변(기존값: " + avo.getNum() + "): ");
			System.out.print(">> 입력 : ");
			input = sc.nextLine().trim();
			
			int newNum = Integer.parseInt(input);
			
			if (newNum < 1 || newNum > 2) {
				System.out.println(">> 1 / 2 중 하나의 숫자를 입력헤 주세요.");
			}
			
			if (sdao.updateAttend(newNum, avo)) {
				System.out.println(">> 설문 정보 업데이트가 완료되었습니다.");
			} else {
				System.out.println(">> 설문 정보 업데이트에 실패했습니다.");
			}
			
			
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
		}
	}
	
	// 사용자 - 응답 삭제
	public void deleteAttend() {
		System.out.println();
		System.out.println(">> MEMBER only SYSTEM 응답삭제 -----");
		
		System.out.print("   삭제할 응답 번호: ");
		String input = sc.nextLine().trim();
		
		try {
			int ano = Integer.parseInt(input);
			if ( (sdao.selectAttend(ano)) == null ) {
				System.out.println(">> 해당하는 번호가 존재하지 않습니다.");
				return;
			}
			
			System.out.print("   정말로 삭제하시겠습니까?(y): ");
			input = sc.nextLine().trim();
			
			if (input.equalsIgnoreCase("y")) {
				if (sdao.deleteAttend(ano)) {
					System.out.println(">> 응답 삭제가 완료되었습니다.");
				} else {
					System.out.println(">> 응답 삭제에 실패했습니다.");
				}
			} else {
				System.out.println(">> 설문 삭제를 취소했습니다.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
			return;
		}
	}

}
