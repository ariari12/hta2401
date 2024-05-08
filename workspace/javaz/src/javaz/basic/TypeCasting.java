package javaz.basic;

// 형변환
// 피연산자의 타입이 같아야 연산 가능
// - 다른 타입의 경우, 연산 전에 피연산자 타입을 일치시킴
// - 자료형끼리의 연산에 주의 필요

// 자동형변환
// - JVM 내부에서 자동 수행
// - 4byte 이하 정수는 int형으로 변환
//   char|byte|short > int > long > float > double
// - 자료형의 표현 범위가 넓은 쪽에 맞춰 변환된 후 연산 수행
//   ex) int / double > double / double > double
//       sum /   3.0  >   sum  / 3.0    > 82.33333

// 강제 형변환
// - 범위가 큰 자료형을 작은 자료형으로 변환
// - 값 손실 발생 가능 ex)소수점 이하 버려짐
// - (변환하려는 자료형)변수명|리터럴
//   ex) int / double > int /     int     >   int
//       sum /   3.0  > sum / (int)3.0    > 82.33333

public class TypeCasting {

	public static void main(String[] args) {
		byte one = 1;
		byte two = 2;
		byte sam = 1 + 2; // 데이터 연산 가능
//			 sam = one + two;   // 타입 연산 불가
//			 sam = (byte)one + (byte)two; // 각 변수 형변환 후 연산 시 int가 됨
		sam = (byte) (one + two);

		int oneTwo = one + two; // 자동현변환

		int oneMil = 1000000;
		int twoMil = 2000000;
		int intTril = oneMil * twoMil;
		    intTril = 1000000 * 2000000;

		long longTril = (long)oneMil * (long)twoMil; // O
			 longTril = (long)(oneMil * twoMil);	 // X, int 계산 후 형변환 
		     longTril = (long)oneMil * twoMil;		 // O

		System.out.println(intTril);
		System.out.println(longTril);
		
		System.out.println("------------");
		// 변수 ch를 선언하면서 임의의 알파벳 문자 하나로 초기화
		char ch = 'K';
		
		// 해당 문자의 아스키코드를 다음 형태로 출력: 문자 "~"의 아스키코드는 ~~		
		System.out.println("문자 \"" + ch + "\"의 아스키코드는 " + (int)ch);
		
		// 변수 code를 선언하면서 33~90 사이의 정수 중 하나로 초기화
		int code = 84;
		
		// 해당 아스키코드의 문자를 다음 형태로 출력: 아스키코드 "~~"의 문자는 ~		
		System.out.println("아스키코드 \"" + code + "\"의 문자는 " + (char)code);
		
	} // END main()

} // END class()
