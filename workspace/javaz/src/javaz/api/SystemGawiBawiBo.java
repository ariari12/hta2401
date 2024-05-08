package javaz.api;

import java.io.IOException;

public class SystemGawiBawiBo {

	public static void main(String[] args) {
		new SystemGawiBawiBo().menu();

	}

	public void menu() {
		while (true) {
			System.out.println("---- JAVA GAME CENTER -----");
			System.out.printf("%17s\n", "1. 가위 바위 보");
			System.out.printf("%15s\n", "2. 야구 게임");
			System.out.printf("%12s\n", "3. 종료");
			System.out.println("---------------------------");
			System.out.print(">> 선택 : ");

			int input = '0';
			try {
				input = System.in.read();
				// 값 입력 받을 경우 \r과 \n이 더 들어오므로 버리도록 작업 진행
				System.in.read();
				System.in.read();
				// 아래와 같이 한 줄로 처리할 수도 있음
//				System.in.skip(2);
				if (input < '1' || input > '3') {
					System.out.println("1 ~ 3 사이의 숫자를 입력해주세요.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}

			SystemGawiBawiBo sgbb = new SystemGawiBawiBo();

			try {
				if (input == '1') {
					sgbb.gawiBawiBo(); break;  // 가위바위보 게임 종료 시 메뉴도 종료
				}
				if (input == '2') {
					sgbb.baseball(); break;	   // 가위바위보 게임 종료 시 메뉴도 종료
				}
				if (input == '3') {
					System.out.println("게임을 종료합니다..");
					System.in.close();
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void gawiBawiBo() {
		boolean flag = true;
		int you = '0';
		
		while (flag) {
			System.out.println(">> 컴퓨터와 가위 바위 보!");
			System.out.println("가위(0), 바위(1), 보(2) 중 하나를 선택해주세요.");
			System.out.print(">> 선택 : ");

			int com = (int) (Math.random() * 3);
			String result = "";
			String youResult = "";
			String comResult = "";

			try {
				you = System.in.read();
				System.in.read();
				System.in.read();

				if (you < '0' || you > '2') {
					System.out.println("0 ~ 2 사이의 숫자를 입력해주세요.");
				} else {
					int checkResult = (you - '0') - com;

					// 승패 판별
					if (checkResult == 0) {
						result = "DRAW!!";
					}
					if (checkResult == -2 || checkResult == 1) {
						result = "YOU WIN!";
					}
					if (checkResult == 2 || checkResult == -1) {
						result = "YOU LOSE!";
					}

					// 사용자의 가위/바위/보 결과 문자열 저장
					if (you == '0') { youResult = "가위"; }
					if (you == '1') { youResult = "바위"; }
					if (you == '2') { youResult = "보"; }

					// 컴퓨터의 가위/바위/보 결과 문자열 저장
					if (com == 0) { comResult = "가위"; }
					if (com == 1) { comResult = "바위"; }
					if (com == 2) { comResult = "보"; }

					System.out.println("\t----------------------------------");
					System.out.println("\t you : " + youResult + "\tvs.\tcom : " + comResult);
					System.out.println("\t----------------------------------");
					System.out.println("\t\t   " + result);
					System.out.println();

					while (true) {
						System.out.println("게임을 계속하시겠습니까? (y/n)");
						System.out.print(">> 입력 : ");

						int checkContinue = System.in.read();
						System.in.read();
						System.in.read();

						if (checkContinue == 'y') {
							System.out.println("게임을 진행합니다..\n\n");
							break;
						}
						if (checkContinue == 'n') {
							System.out.println("게임을 종료합니다..\n\n");
							flag = false;
							break;
						}
						if (checkContinue != 'y' && checkContinue != 'n') {
							System.out.println("y 혹은 n을 입력해주세요.");
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}

	}

	public void baseball() {
		int[] youNums = new int[3];
		int[] comNums = new int[3];
		boolean gameFlag = true; // 게임 속행 여부 확인

		while (gameFlag) { // 최초 1회 시에만 작업, 이후 새 게임 시 새로 출력/설정되는 부분
			boolean sameGame = true; // 기존 게임 속행 여부 확인
			System.out.println("컴퓨터와 야구 게임!");

			// 컴퓨터 랜덤 숫자 생성(1~9) - 중복 제외
			for (int i = 0; i < comNums.length; i++) {
				int nansu = (int) (Math.random() * 9) + 1;
				for (int j = 0; j <= i; j++) {
					if (nansu == comNums[j] && i != j) {
						comNums[i] = 0; // 불필요한 작업??
						i--;
						break;
					}
					comNums[i] = nansu;
				}
			}

			while (sameGame) {
				// (2) 숫자를 하나씩 받을 경우 - 아래 주석 해제
				for (int i = 0; i < youNums.length; i++) {
					while (true) {
						try {
							System.out.println("숫자를 입력해주세요.");
							System.out.print(">> " + (i + 1) + "번째 숫자 입력 : ");
							// 아스키 숫자값을 int 숫자로 변환 ('1' -> 1)
							youNums[i] = System.in.read() - '0';
							System.in.skip(2);

							// 범위 외 숫자 검증
							if (youNums[i] < 1 || youNums[i] > 9) {
								System.out.println("1 ~ 9 사이의 숫자를 입력해주세요.");
								// 해당 인덱스 다시 지정하여 값 입력 받기
								i--;
								System.out.println(i);
								break;
							}
							// 중복 체크 - 중복일 경우 해당 인덱스 다시 지정하여 값 입력 받기
							for (int j = 0; j <= i; j++) {
								if (youNums[i] == youNums[j] && i != j) {
									System.out.println("중복되지 않은 값을 입력해주세요.");
									i--;
									break;
								}
							}
							break;
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NumberFormatException e) {
							System.out.println("숫자를 입력해주세요.");
						}
					}
				}

				// 낫싱 / 볼 / 스트라이크 계산
				int ball = 0;
				int strike = 0;

				// 같은 위치(인덱스)에 같은 값 있는지 확인
				for (int i = 0; i < comNums.length; i++) {
					if (comNums[i] == youNums[i]) { strike++; }
					// 위치 관계 없이 같은 값 있는지 확인
					for (int j = 0; j < youNums.length; j++) {
						if (comNums[i] == youNums[j]) { ball++; }
					}
				}

				// 볼에서 스트라이크와 중복값 제거
				ball -= strike;

				// 결과 출력
				System.out.println("----- 야구 게임 결과 -----");
				System.out.print("입력한 값: ");
				for (int num : youNums) {
					System.out.print(num);
				}
				System.out.println();
				
				// 확인용!!
				System.out.print("컴퓨터 값: ");
				for (int num : comNums) {
					System.out.print(num);
				}
				System.out.println();
				
				if (ball == 0 && strike == 0) {
					System.out.println("낫싱");
				} else {
					if (ball > 0) {
						System.out.println(ball + "볼 " + strike + "스트라이크");
					} else {
						System.out.println(strike + "스트라이크");
					}
				}

				while (true) {
					// 재실행 확인(3스트라이크 이후)
					if (strike == 3) {
						System.out.println("축하합니다!");
						System.out.println("게임을 새로 시작하시겠습니까? (y/n)");
					} else {
						System.out.println("게임을 계속하시겠습니까? (y/n)");
					}
					
					System.out.print(">> 입력 : ");

					int checkContinue;
					try {
						checkContinue = System.in.read();
						System.in.skip(2);
						System.out.println((char) checkContinue);	// 확인용

						if (checkContinue == 'y') {
							System.out.println("게임을 진행합니다.\n\n");
							// 3스트라이크일 경우, 새 게임 시작
							if (strike == 3) { sameGame = false; }
							break;
						}
						if (checkContinue == 'n') {
							System.out.println("야구게임을 종료합니다..\n\n");
							gameFlag = false;
							sameGame = false;
							break;
						}
						if (checkContinue != 'y' && checkContinue != 'n') {
							System.out.println("y 혹은 n을 입력해주세요.\n");
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
