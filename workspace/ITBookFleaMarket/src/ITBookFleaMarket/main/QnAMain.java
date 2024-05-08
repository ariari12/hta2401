package ITBookFleaMarket.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import ITBookFleaMarket.dao.MemberDAO;
import ITBookFleaMarket.dao.QnADAO;
import ITBookFleaMarket.vo.QnAVO;

public class QnAMain {
	private Scanner sc;
	private QnADAO qadao;
	private QnAVO qavo;
	private MemberDAO mdao;
	
	public QnAMain() {
		sc = new Scanner(System.in);
		qadao = new QnADAO();
		qavo = QnAVO.getInstance();
		mdao = new MemberDAO();
	}
	
	// 1. 사용자 문의메뉴 - 문의등록 / 내 문의내역 / 전체 문의내역 / 이전으로
	public void userQnAMenu() {
		while (true) {
			System.out.println();
			System.out.println(">> ------------- 문의 화면 -------------");
			System.out.println("   1.문의등록   2.내 문의내역   3.전체 문의내역   4.이전으로");
			System.out.print(">> 선택: ");
			String input = sc.nextLine().trim();
			
			int num = 0;
			try {
				num = Integer.parseInt(input);
				
				if (num < 1 || num > 4) {
					System.out.println(">> 1 / 2 / 3 / 4 중 하나의 숫자를 입력해 주세요.");
					System.out.println();
				}
				
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
				System.out.println();
			}
			
			if (num == 1) { makeQnA(); }
			if (num == 2) { myQnA(); }
			if (num == 3) { allQnA(); }
			if (num == 4) { return; }
		}
	}
	
	// 1.1 문의등록
	public void makeQnA() {
		System.out.println();
		System.out.println(">> ------------- 문의등록 -------------");
		String qId = MemberMain.loginId;
		System.out.print("   문의자: ");
		System.out.println(qId);
		System.out.print("   문의 제목: ");
		String qTitle = sc.nextLine().trim();
		if (mdao.invalidRequired(qTitle, "문의 제목")) { return; }
		
//		System.out.print("   문의 내용: ");
//		String qContent = sc.nextLine().trim();
		// bufferedReader / bufferedWriter를 사용하여 여러 줄 입력받기 테스트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("   문의 내용(종료는 \\q): ");
		String qContent = "";
		String input = "";
		while (true) {
			try {
				input = " ".repeat(6) + br.readLine().trim();
				
				if (input.trim().equalsIgnoreCase("\\q")) { break; }
				
				qContent += input + "\n";
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (mdao.invalidRequired(qContent.trim(), "문의 내용")) { return; }
		
		qavo = new QnAVO(qId, qTitle, qContent);
		
		if (qadao.insertQnA(qavo)) {
			System.out.println(">> 문의 등록이 완료되었습니다.");
		} else { 
			System.out.println(">> 문의 등록에 실패했습니다");
		}
	}
	
	// 1.2 내 문의내역 - 상세 보기
	public void myQnA() {
		while (true) {
			List<QnAVO> questions = qadao.selectQnA(MemberMain.loginId);
			
			System.out.println();
			System.out.println(">> 내 문의내역");
			
			showQnAList(questions);
			
			int opt = goQnADetailMenu();
			if (opt == 1) { qnADetail(1); }
			if (opt == 2) { return; }
		}
	}
	
	// 1.2 (공통)문의내역 조회 포맷
	public void showQnAList(List<QnAVO> questions) {
		if (questions.size() < 1) {
			System.out.println(">> 조회된 문의글이 없습니다.");
		}
		
		System.out.println();
		System.out.printf("%5s\t|%8s\t|%8s\t|%5s\t|%5s\n", 
						"문의번호", "문의제목", "등록일자", "문의자" ,"답변상태");
		System.out.println("-".repeat(60));
		
		for (QnAVO question : questions) {
			System.out.printf("   %s\t|%s   \t|" + question.getqDate()
							  + "   \t|%s\t|%s\n", 
							  question.getqNo(), question.getqTitle(),
							  question.getqId() ,question.getaState());
		}
	}
	
	// 1, 2 (공통) 상세보기 - 상세보기 / 이전으로
	public int goQnADetailMenu() {
		int num = 0;
		System.out.println();
		System.out.println("   1.상세보기   2.이전으로");
		System.out.print(">> 선택 : ");
		String input = sc.nextLine().trim();
		
		try {
			num = Integer.parseInt(input);

			if (num < 1 || num > 2) {
				System.out.println(">> 1 / 2 중 하나의 숫자를 입력해 주세요.");
				System.out.println();
			}

		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
			System.out.println();
		}
		
		
		return num;
	}

	// 1, 2.? (공통)상세보기할 문의번호 받기 - 1: 사용자 내문의 상세, 2: 전체문의 상세
	public void qnADetail(int opt) {
		System.out.println();
		System.out.print("   조회할 문의 번호: ");
		String qNoInput = sc.nextLine().trim();
		
		int qNo = 0;
		try {
			qNo = Integer.parseInt(qNoInput);
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
			System.out.println();
		}
		
		if (opt == 1) {
			if ( (qavo = qadao.selectQnA(MemberMain.loginId, qNo)) == null ) {
				System.out.println(">> 존재하지 않는 문의 번호입니다.");
				return;
			}
		}
		
		if (opt == 2) {
			if ( (qavo = qadao.selectQnA(qNo)) == null ) {
				System.out.println(">> 존재하지 않는 문의 번호입니다.");
				return;
			}
		}
		
		qnAInfo(qNo);
		
	}
	
	// 1, 2.? (공통) 문의 상세정보 출력 - 사용자: 문의수정 - 문의삭제
	//						  		관리자: 답변등록 - 답변수정 - 답변삭제 - 문의삭제
	public void qnAInfo(int qNo) {
		boolean isAdmin = mdao.selectMember(MemberMain.loginId).isAdmin();
		
		while (true) {
			qavo = qadao.selectQnA(qNo);
			
			System.out.println();
			System.out.println("=".repeat(50));
			System.out.println("   제목: " + qavo.getqTitle());
			System.out.println("-".repeat(50));
			System.out.println("   내용");
			System.out.println(qavo.getqContent());
			System.out.println(" ".repeat(30) + "문의일자: " 
							   + qavo.getqDate());
			System.out.println("-".repeat(50));
			
			if (qavo.getaDate() != null) {
				System.out.println("   답변");
				System.out.println(qavo.getaContent());
				System.out.println(" ".repeat(30) + "답변일자: " 
						+ qavo.getaDate());
			}
			
			System.out.println("=".repeat(50));
			
			if (isAdmin) {
				manageQnAAdmin(qNo);
				break;
			}
			
			if (qavo.getqId().equals(MemberMain.loginId)) {
				manageQnA(qNo);
				break;
			}
			
			System.out.println(">> 아무 값이나 입력하여 목록 화면으로 돌아갑니다..");
			sc.nextLine().trim();
			return;
			
		}
	}
	
	// 1.2.1 사용자 문의 관리 - 문의수정 / 문의삭제 / 이전으로
	public void manageQnA(int qNo) {
		System.out.println();
		System.out.println("   1.문의수정   2.문의삭제   3.이전으로");
		System.out.print(">> 선택: ");
		String input = sc.nextLine().trim();
		
		int num = 0;
		try {
			num = Integer.parseInt(input);
			
			if (num < 1 || num > 3) {
				System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
				System.out.println();
			}
			
		} catch (NumberFormatException e) {
			System.out.println(">> 숫자를 입력해 주세요.");
			System.out.println();
		}
		
		if (num == 1) { updateQuestion(qNo); }
		if (num == 2) { deleteQuestion(qNo); }
	}
	
	// 1.2.1.1 사용자 문의 수정
	public void updateQuestion(int qNo) {
		if (qadao.hasAnswer(qNo)) {
			System.out.println(">> 답변완료된 문의는 수정할 수 없습니다.");
			return;
		}
		
		qavo = qadao.selectQnA(qNo);
		
		System.out.println();
		System.out.println(">> ------------- 문의 수정 -------------");
		
		System.out.print("   변경할 제목(기존값: " + qavo.getqTitle() + "): ");
		String newQTitle = sc.nextLine().trim();
		if (newQTitle.equals("")) { newQTitle = qavo.getqTitle(); }
		
//		System.out.println("   변경할 내용: ");
//		String newQContent = sc.nextLine().trim();
		
		System.out.println("   기존 내용:");
		System.out.println(qavo.getqContent());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("   변경할 내용(종료는 \\q): ");
		String newQContent = "";
		String input = "";
		while (true) {
			try {
				input = " ".repeat(6) + br.readLine().trim();
				
				if (input.trim().equalsIgnoreCase("\\q")) { break; }
				
				if (input.trim().equals("")) { continue; }
				
				newQContent += input + "\n";
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (newQContent.equals("")) { newQContent = qavo.getqContent(); }
		
		QnAVO updateInfo = new QnAVO(MemberMain.loginId, newQTitle, newQContent);
		
		if (qadao.updateQnA(qNo, updateInfo)) {
			System.out.println(">> 문의글 업데이트가 완료되었습니다.");
		} else {
			System.out.println(">> 문의글 업데이트에 실패했습니다.");
		}
		
		System.out.println();
	}
	
	// 1.2.1.2, 2.? (공통)문의 삭제
	public void deleteQuestion(int qNo) {
		System.out.println();
		System.out.println(">> ------------- 문의 삭제 -------------");
		System.out.print("   정말로 삭제하시겠습니까?(y): ");
		String input = sc.nextLine().trim();
		
		if (input.equalsIgnoreCase("y")) {
			if (qadao.deleteQnA(qNo)) {
				System.out.println(">> 삭제가 완료되었습니다.");
			} else {
				System.out.println(">> 삭제 실패하였습니다.");
			}
		} else {
			System.out.println(">> 삭제를 취소했습니다.");
		}
	}
	
	// 1.3, 2.? (공통)전체 문의내역 - 상세 보기
	public void allQnA() {
		while (true) {
			List<QnAVO> questions = qadao.selectQnA();
			
			System.out.println();
			System.out.println(">> 전체 문의내역");
			
			showQnAList(questions);
			
			int opt = goQnADetailMenu();
			if (opt == 1) { qnADetail(2); }
			if (opt == 2) { return; }
		}
	}
	
	// 2. 관리자 문의메뉴 - 조회 방식 선택 - 전체 문의내역 / 답변 미완료 문의 / 답변 완료 문의 / 이전으로
	public void adminQnAMenu() {
		while(true) {
			System.out.println();
			System.out.println("   조회 방식을 선택해 주세요.");
			System.out.println("   1.전체 문의내역   2. 답변 미완료 문의   3. 답변 완료 문의   4.이전으로");
			System.out.print(">> 선택: ");
			String input = sc.nextLine().trim();
			
			int condition = 0;
			try {
				condition = Integer.parseInt(input);
				
				if (condition < 1 || condition > 4) {
					System.out.println(">> 1 / 2 / 3 / 4 중 하나의 숫자를 입력해 주세요.");
					System.out.println();
				}
				
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
				System.out.println();
			}
			
			if (condition == 1) { allQnA(); }
			
			if (condition == 2 || condition == 3) { conditionQnA(condition); }
			
			if (condition == 4) { return; }
		}
	}
	
	// 2.2 관리자 답변 미완료/완료 문의글 조회 
	public void conditionQnA(int condition) {
		boolean answered = condition == 3;
		
		while (true) {
			List<QnAVO> questions = qadao.selectQnA(answered);
			
			System.out.println();
			
			if (condition == 2) {
				System.out.println(">> 답변 미완료 문의");
			}
			if (condition == 3) {
				System.out.println(">> 답변 완료 문의");
			}
			
			showQnAList(questions);
			
			int opt = goQnADetailMenu();
			if (opt == 1) { qnADetail(2); }
			if (opt == 2) { return; }
		}
	}
	
	// 2.1.1.1 관리자 문의 개별 관리 - (답변등록 / 답변수정) / 답변삭제 / 문의삭제 / 이전으로
	public void manageQnAAdmin(int qNo) {
		boolean isAnswered = qadao.selectQnA(qNo).getaState().equals("답변완료");
		
		System.out.println();
		if (isAnswered) {
			System.out.println("   1.답변수정   2.답변삭제   3.문의삭제   4.이전으로");
		} else {
			System.out.println("   1.답변등록   2.문의삭제   3.이전으로");
		}
		System.out.print(">> 선택: ");
		String input = sc.nextLine().trim();
		
		int num = 0;
		if (isAnswered) {
			try {
				num = Integer.parseInt(input);
				
				if (num < 1 || num > 4) {
					System.out.println(">> 1 / 2 / 3 / 4 중 하나의 숫자를 입력해 주세요.");
					System.out.println();
				}
				
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
				System.out.println();
			}
			
			if (num == 1) { updateAnswer(qNo); }
			if (num == 2) { deleteAnswer(qNo); }
			if (num == 3) { deleteQuestion(qNo); }
			
		} else {
			try {
				num = Integer.parseInt(input);
				
				if (num < 1 || num > 3) {
					System.out.println(">> 1 / 2 / 3 중 하나의 숫자를 입력해 주세요.");
					System.out.println();
				}
				
			} catch (NumberFormatException e) {
				System.out.println(">> 숫자를 입력해 주세요.");
				System.out.println();
			}
		
			if (num == 1) { makeAnswer(qNo); }
			if (num == 2) { deleteQuestion(qNo); }
		}
		
	}
	
	// 2.1.1.1.1 답변 등록
	public void makeAnswer(int qNo) {
		System.out.println();
		System.out.println(">> ------------- 답변등록 -------------");
//		System.out.print("   답변 내용: ");
//		String aContent = sc.nextLine().trim();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("   답변 내용(종료는 \\q): ");
		String aContent = "";
		String input = "";
		while (true) {
			try {
				input = " ".repeat(6) + br.readLine().trim();
				
				if (input.trim().equalsIgnoreCase("\\q")) { break; }
				
				aContent += input + "\n";
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (mdao.invalidRequired(aContent.trim(), "답변")) { return; }
		
		if (qadao.updateQnA(qNo, aContent)) {
			System.out.println(">> 답변 등록이 완료되었습니다.");
		} else { 
			System.out.println(">> 답변 등록에 실패했습니다");
		}
	}
	
	// 2.1.1.1.2 답변 수정
	public void updateAnswer(int qNo) {
		System.out.println();
		System.out.println(">> ------------- 답변수정 -------------");
//		System.out.print("   답변 내용: ");
//		String aContent = sc.nextLine().trim();
		
		System.out.println("   기존 내용:");
		System.out.println(qavo.getaContent());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("   변경할 내용(종료는 \\q): ");
		String aContent = "";
		String input = "";
		while (true) {
			try {
				input = " ".repeat(6) + br.readLine().trim();
				
				if (input.trim().equalsIgnoreCase("\\q")) { break; }
				
				if (input.trim().equals("")) { continue; }
				
				aContent += input + "\n";
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (aContent.equals("")) { aContent = qavo.getaContent(); }
		
		if (qadao.updateQnA(qNo, aContent)) {
			System.out.println(">> 답변 수정이 완료되었습니다.");
		} else { 
			System.out.println(">> 답변 수정에 실패했습니다");
		}
	}
	
	// 2.1.1.1.3 답변 삭제
	public void deleteAnswer(int qNo) {
		System.out.print("   정말로 삭제하시겠습니까?(y): ");
		String input = sc.nextLine().trim();
		
		if (input.equalsIgnoreCase("y")) {
			if (qadao.updateQnA(qNo)) {
				System.out.println(">> 삭제가 완료되었습니다.");
			} else {
				System.out.println(">> 삭제 실패하였습니다.");
			}
		} else {
			System.out.println(">> 삭제를 취소했습니다.");
		}
	}
}
