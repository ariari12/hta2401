package javaz.basic;

import java.util.Iterator;

// for 반복문
// - 반복 횟수가 명확한 경우에 주로 사용
// - 초기화: 가장 먼저 1회만 수행
//		   반복 제어 변수의 선언 또는 초기화
// - 조건식 : 반복 계속 여부 검사 - false이면 반복 종료
// - 증감식 : 반복 제어 변수의 값을 증감 처리한 후 조건식으로 이동

// for( 초기화 ; 조건식 ; 증감식 ) {
//		수행문1;
//		수행문2;
//		...
//		수행문n;
// }

// for ( 초기화 ; 조건식 ; 증감식 )  수행문1;

// 중첩 for문
// for ( 초기화 ; 조건식 ; 증감식 ) {      // outer loop
//		for ( 초기화 ; 조건식 ; 증감식 ) { //inner loop
//			  ...
// 		} // END inner loop
// } // END outer loop

public class For {

	public static void main(String[] args) {
		System.out.println(0);
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		System.out.println(4);
		System.out.println();

		for (int i = 0; i < 5; i++) { // i < 5 대신 i <= 4를 써도 동일한 의미
			// System.out.println(i);
		}

//		for ( ; ; ) { } 				// 무한루프
//		for (int i = 0; ; )	{ }		    // 무한루프
//		for (int i = 0; i < 10; ) { }	// 무한루프
//		for (int i = 0; ; i++) { }		// 무한루프

		System.out.println();
		System.out.println("2 ~ 10 사이의 2의 배수");
		System.out.println("2 4 6 8 10");

		for (int i = 2; i <= 10; i += 2) {
			System.out.print(i + " ");
		}

		System.out.println();
		System.out.println();
		System.out.println("1 ~ 10 사이의 3의 배수");
		System.out.println("3 6 9 ");

		for (int i = 1; i <= 10; i++) {
			if (i % 3 == 0) {
				System.out.print(i + " ");
			}
		}

		System.out.println();
		System.out.println();
		System.out.println("1 ~ 10 사이의 모든 정수의 합");
		System.out.println("55");

//		int sum = 0; // 외부에 정의해야 for문 밖에 값 전달 가능함

		for (int i = 1, sum = 0; i <= 10; i++) { // 여러개 초기화 가능
			sum += i; // 다만, for문 밖에서 sum 사용 불가
			System.out.println(i + "까지의 합: " + sum);
		}

		System.out.println("---------------");
//		System.out.println("1 ~ 10 사이의 모든 정수의 합: " + sum); // 오류 발생

		System.out.println();
		System.out.println("구구단 - 2단");
		System.out.println("2 * 1 = 2");
		System.out.println("2 * 2 = 4");
		System.out.println("2 * 3 = 6");
		System.out.println("2 * 4 = 8");
		System.out.println("   ...   ");
		System.out.println("2 * 9 = 18");

		System.out.println();

		System.out.println("구구단 - 2단");
		for (int i = 1; i <= 9; i++) {
			System.out.println("2 * " + i + " = " + 2 * i);
		}

		System.out.println();
		System.out.println("중첩 for문");
		System.out.println("1: *");
		System.out.println("2: **");
		System.out.println("3: ***");
		System.out.println("4: ****");
		System.out.println("5: *****");

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + " : ");
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + " : ");
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i = 5; i >= 1; i--) {
			System.out.print(i + " : ");
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + " : ");
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("가로 5 * 세로 5 별표 출력");
		for (int i = 1; i <= 5; i++) {
			System.out.print(i + " : ");
			for (int j = 1; j <= 5; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("구구단");
		for(int i = 2; i <= 9; i++) {
			System.out.println("[" + i + "]단");
			for (int j = 1; j <= 9; j++) {
				System.out.print(i + " * " + j + " = " + i * j + "\t");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("구구단 2단 ~ 9단 세로 방향 출력");
		for (int i = 2; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				System.out.println(i + " * " + j + " = " + i * j);
			}			
			System.out.println();
		}
		
		System.out.println();
		System.out.println("구구단 2단 ~ 9단 가로 방향 출력");
		// 바깥의 줄수를 가장 바깥에서 세어
		for (int i = 1; i <= 18; i++) {
			for (int k = 1; k <= 9; k++) {
				if (i >= 10) {
					System.out.println();					
					for (int j = 6; j <= 9; j++) {
						System.out.print(j + " * " + k + " = " + j * k);
						System.out.print("\t");
					}
				}
				
				if (i <= 9) {
					System.out.println();
					for (int j = 2; j <= 5; j++) {
						System.out.print(j + " * " + k + " = " + j * k);
						System.out.print("\t");
						//System.out.print(i + " " + j + " " + k);
					}
				}				
				i++;
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("test2");
		//바깥을 크게 2개로 나누어 계산
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= 9; j++) {
				if (i == 1) {
					for (int k = 2; k <= 5; k++) {
						System.out.printf("%d * %d = %2d\t", k, j, k * j);
						}
				}
				if (i == 2) {
					for (int k = 6; k <= 9; k++) {
						System.out.printf("%d * %d = %2d\t", k, j, k * j);
						}
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("가로 방향 두 블럭을 반복하는 for문");
		// 가로 방향 2단을 반복하는 for문
		for (int row = 2; row <= 6; row += 4) {
			// 각 단의 1 ~ 9를 반복하는 for문
			for (int i = 1; i <= 9 ; i++) {
				// 2단 ~ 5단 / 6단 ~ 9단을 반복하는 for문
				for (int dan = row; dan <= row + 3; dan++) {
					System.out.printf("%d * %d = %2d\t", dan, i, dan * i);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
