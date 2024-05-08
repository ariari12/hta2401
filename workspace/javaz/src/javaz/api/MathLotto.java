package javaz.api;

public class MathLotto {
	private int[] lottoWin;
	private int bonusNum;
	
	// 기본생성자 ------------------------------
	// 1106회차 로또 당첨 번호로 lottoWin 배열을 초기화하고
	// 보너스 번호를 bonusNum에 저장
	public MathLotto() {
		lottoWin = new int[] { 1, 3, 4, 29, 42, 45 };
		bonusNum = 36;
		
		// 랜덤 테스트 - 중복 값, 오름차순 정렬 여부
		showResult();
		
		
	}
	
	// 로또 번호 랜덤 생성 - makeLottoNum 메서드 ----
	public int[] makeLottoNum() {
		// 실행 시 마다
		// 1 ~ 45 사이의 중복되지 않는 난수 6개를 추출하여
		// 오름차순 정렬하여 임의의 배열에 저장한 후 반환
		int[] myLottoNum = new int[6];
		
		// 중복 체크하여 번호 부여
		for (int i = 0; i < myLottoNum.length; i++) {
			int nansu = (int) (Math.random() * 45) + 1;
			System.out.println("난수: " + nansu);
			while (myLottoNum[i] == 0) {
				for (int j = 0; j <= i; j++) {
					System.out.println("인덱스 확인 i: " + i + ", j: " + j);
					System.out.println("인덱스 확인 i: " + i + ", 값: " + myLottoNum[i]);
					System.out.println("중복인가? " + (nansu == myLottoNum[j]) + "\n");
					if (nansu == myLottoNum[j] && i != j) {
						myLottoNum[i] = 0;
						i--; 
						break; 
						}
					myLottoNum[i] = nansu;
				}
			}
		}
		
		// 오름차순 정렬
		for (int i = 0; i < myLottoNum.length; i++) {
			for (int j = i + 1; j < myLottoNum.length; j++) {
				int temp = 0;
				if (myLottoNum[i] > myLottoNum[j]) {
					temp = myLottoNum[i];
					myLottoNum[i] = myLottoNum[j];
					myLottoNum[j] = temp;
				}
			}
		}
		
		return myLottoNum;
	}
	
	// 당첨 여부 확인 및 결과 출력 메서드
	// 이번 주 당첨번호	: ~~ ~~ ~~ ~~ ~~ ~~ + ~~
	// 나의 당첨번호	: ~~ ~~ ~~ ~~ ~~ ~~  1등 ... 5등 | 다음 기회에
	private void showResult() {
		int[] myLottoNum = makeLottoNum();
		
//		// 1등
//		myLottoNum = new int[] { 1, 3, 4, 29, 42, 45 };
//		// 2등
//		myLottoNum = new int[] { 1, 3, 4, 29, 36, 42};
//		// 3등
//		myLottoNum = new int[] { 1, 3, 4, 29, 42, 43 };
//		// 4등
//		myLottoNum = new int[] { 1, 3, 4, 29, 30, 31 };
//		// 5등
//		myLottoNum = new int[] { 1, 3, 4, 30, 31, 32 };
		
		// 당첨 개수 확인 ------------------------------
		int count = 0;
		boolean haveBonus = false;
		for (int num : myLottoNum) {
			for (int winNum : lottoWin) {
				if (num == winNum) { count++; }
				if (num == bonusNum) { haveBonus = true; }
			}
		}
		
		// 당첨 여부 및 등수 확인 -----------------------
		String result = "";
		switch (count) {
		case 6: result = "1등"; break;
		case 5: result = "3등"; 
			for (int myNum : myLottoNum) { 
					if (myNum == bonusNum) { result = "2등"; }
			} break;	
		case 4: result = "4등"; break;
		case 3: result = "5등"; break;
		default: result = "다음 기회에";
		}
		
		
		System.out.println("- 1106회차 로또 당첨 결과 - ");
		System.out.print("이번 주 당첨번호\t: ");
		for (int winNum : lottoWin) {
			System.out.printf("%2d ", winNum);
		}
		System.out.printf("+ %2d\n", bonusNum);
		
		System.out.print("나의 로또 번호\t: " );
		
		
		for (int num : myLottoNum) {
			System.out.printf("%2d ", num);
//			// 당첨 번호 / 보너스 번호 소지 확인 (위에서 분리해서 작성함)
//			for (int winNum : lottoWin) {
//				if (num == winNum) { count++; }
//				if (num == bonusNum) { haveBonus = true; }
//			}
		}
		
		System.out.print("\t   ");
		System.out.println(result);
		
		// if문 사용하여 출력
//		if (count == 6) { System.out.println("1등"); }
//		else if (count == 5 && haveBonus) { System.out.println("2등"); }
//		else if (count == 5) { System.out.println("3등"); }
//		else if (count == 4) { System.out.println("4등"); }
//		else if (count == 3) { System.out.println("5등"); }
//		else if (count < 3) { System.out.println("다음 기회에"); }
		
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		new MathLotto();
	}
}
