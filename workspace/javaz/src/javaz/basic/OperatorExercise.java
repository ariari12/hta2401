package javaz.basic;

public class OperatorExercise {

	public static void main(String[] args) {
		// 피자 주문하기------------
		// - 미디어피자 : 반지름 20cm, 10,000원
		// - 라지피자  : 반지름 40cm, 20,000원
		// 예산은 20,000원
		// 가성비가 높은 주문은?
		// 1. 미디엄피자 2판
		// 2. 라지피자 1판
		int mediumRadius = 20;		
		int largeRadius = 40;
		final double PI = 3.14;
		
		double mediumArea = mediumRadius * mediumRadius * PI;
		double largeArea = largeRadius * largeRadius * PI;
		
		String rs =  mediumArea * 2 > largeArea ? 
				"1. 미디엄피자 2판" : "2. 라지피자 1판";
		
		System.out.println("가성비가 높은 주문은 " + rs);
		
		// 동전 출금하기------------
		// 출금액은 7,777원
		// 10원 미만은 10원으로 반올림하여
		// 다음과 같이 출력
		// 오백원: ~~개 ~~~~원
		//  백원: ~~개 ~~~~원
		// 오십원: ~~개 ~~~~원
		//  십원: ~~개 ~~~~원
		int outputMount = 7_777;
		int fivehundredWon = outputMount / 500;
		// 출금액 변수 추가 연산 없이 동전 갯수 연산		
		int hundredWon = outputMount % 500 / 100;
		int fiftyWon = outputMount % 500 % 100 / 50;
		int tenWon = outputMount % 500 % 100 % 50 % 10 >= 5 ?
				outputMount % 500 % 100 % 50 / 10 + 1 : outputMount % 500 % 100 % 50 / 10;
		// 출금액 변수 추가 연산하여 동전 갯수 연산
		outputMount %= 500;
		hundredWon = outputMount / 100;
		outputMount %= 100;
		fiftyWon = outputMount / 50;
		outputMount %= 50;
		tenWon = outputMount / 10;
		System.out.println(tenWon);
		outputMount %= 10;
		//tenWon = outputMount >= 5 ? ++tenWon : tenWon;
		tenWon += outputMount >= 5 ? 1 : 0;
		
//		System.out.println("오백원: " + fivehundredWon + "개 " + fivehundredWon * 500 + "원");
//		System.out.println("백원: " + hundredWon + "개 " + hundredWon * 100 + "원");
//		System.out.println("오십원: " + fiftyWon + "개 " + fiftyWon * 50 + "원");
//		System.out.println("십원: " + tenWon + "개 " + tenWon * 10 + "원");
//		System.out.println(outputMount); 
		
		System.out.printf("오백원: %2d개 %,5d원\n", fivehundredWon, fivehundredWon * 500);
		System.out.printf("백원: %2d개 %,5d원\n", hundredWon, hundredWon * 100);
		System.out.printf("오십원: %2d개 %,5d원\n", fiftyWon, fiftyWon * 50);
		System.out.printf("십원: %2d개 %,5d원\n", tenWon, tenWon * 10);
		System.out.printf("%5d원", outputMount);
		

	}

}
