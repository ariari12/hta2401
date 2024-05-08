package javaz.basic;

public class SwitchGawiBawiBo {

	public static void main(String[] args) {
		int menu = 1;		// 메뉴 - 현재 미구현
		int you = 1;		// 사람 입력 값
		int com = 1;		// 컴퓨터 입력 값
		String result = ""; // 대결 결과 YOU WIN!/DRAW!/YOU LOSE!
		String youRSP = you == 0 ? "가위" : you == 1 ? "바위" : "보";
		String comRSP = com == 0 ? "가위" : com == 1 ? "바위" : "보";
		
		
		System.out.println("---- JAVA GAME CENTER ----");
		System.out.println("\t1. 가위 바위 보");
		System.out.println("\t2. 야구 게임");
		System.out.println("\t3. 종료");
		System.out.println("------------------------");
		System.out.println(">> 선택 : " + menu);
		
		System.out.println(">> 컴퓨터와 가위 바위 보!");
		System.out.println(">> 가위(0), 바위(1), 보(2) 중 하나를 선택해주세요.");
		System.out.println(">> 선택 : " + you);
		
		// y - c  가위0    바위1    보2
		// 가위0     0     -1      -2
		// 바위1     1      0      -1
		// 보2      2      1       0
		
		if (you < 0 && you > 3) {
			System.out.println(">> 입력 오류!!!");
			System.out.println(">> 가위(0), 바위(1), 보(2) 중 하나를 선택해주세요.");
			return;
		}
		
		// 가위 바위 보 결과 저장
		switch (you - com) {
		case 0:
			result = "DRAW!";
			break;
			
		case -1:
		case 2:
			result = "YOU LOSE!";
			break;
			
		default:
			result = "YOU WIN!";
			break;
		}
		
		System.out.println("\t---------------------------------");
		System.out.println("\t you : " + youRSP + "\tvs.\tcom : " + comRSP);
		System.out.println("\t---------------------------------");
		System.out.println("\t\t    " + result);
	}
	
}
