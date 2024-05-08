package javaz.basic;

//import javaz.oop.Shape;

// 연산자
// - 특정 연산을 나타내는 기호
// - 우선 순위가 높은 연산자가 먼저 계산되고
// - 동일 우선 순위에서는 왼쪽에서 오른쪽으로 연산 진행

// 증감 연산자  ++, --		     // 1순위
// 산술 연산자  +, -, *, /, %   // 2순위
// 시프트 연산자 >>, <<, >>>     // 3순위
// 비교 연산자  >, <, >=, <=,	 // 4순위
//			 ==, !=
// 비트 연산자	&, |, ^, ~(1순위) // 5순위
// 논리 연산자 &&, ||, !(1순위)  // 6순위
// 조건 연산자 __?__:__         // 7순위
// 삼항 연산자 __?__:__			 
// 대입 연산자 =, *=, /+, %=	// 8순위
//		   +=, -=
public class Operator {

	public static void main(String[] args) {
		// 부호 연산자
		// + 피연산자에 1 곱하기
		// - 피연산자에 -1 곱하기
		int buho = 3;	System.out.println(buho);
		buho = -buho;	System.out.println(buho);
		buho = +buho;	System.out.println(buho);
		buho = -buho;	System.out.println(buho);
		
		System.out.println();
		// 산술 연산자
		// % 나머지 구하기, 짝수/홀수/배수 구하기
		int divide = 7 + 6 - 5 * 4 / 3;
		int modulo = 7 + 6 - 5 * 4 % 3;
		
		System.out.println(divide);
		System.out.println(modulo);
//		System.out.println(divide / 0);   // 0으로 나누기 X(예외처리)
		System.out.println(divide / 0.0); // 0.0으로 나누기 X(Infinity)
		System.out.println(divide % 0.0); // 0.0 나머지 X(NaN)
		
		// 변수 time에 3695를 저장하고
		int time = 3695;
		// 3695초를 ~~시간 ~~분 ~~초로 계산하여
		// hour min sec에 저장한 후 화면에 다음과 같이 출력
		int hour = time / 60 / 60;
		int min = (time - hour * 60 * 60) / 60;
		    min = time % (60 * 60) / 60;
		int sec = time - (hour * 60 * 60) - (min * 60);
		    sec = time % 60;
		
		// 3695초는 ~~시간 ~~분 ~~초
		System.out.println("3695초는 " + hour + "시간 " + min + "분 " + sec + "초");
		
		System.out.println();
		// 증감 연산자------------
		// 연산자의 위치에 따라 증감 시점이 달라짐
		// ++	변수의 값을 1 증가
		// --   변수의 값을 1 감소
		int year = 2024;
		System.out.println("last year: " + --year); // 먼저 1 감소
		System.out.println("last year: " + ++year); // 먼저 1 증가
		System.out.println("last year: " + year--); // 나중에 1 감소
		System.out.println("back to year: " + year++); // 나중에 1 증가
		System.out.println("now: " + year);
		
		System.out.println();
		// 대입 연산자------------
		// 변수의 값 또는 수식의 연산 결과를 변수에 저장
		// 다른 연산자와 결합하여 사용 가능
		// 우선 순위가 가장 낮음
		int i = 1;
		System.out.println(i);
		i = i + 1;
		System.out.println(i);
		i += 1;
		System.out.println(i);
		i++;
		System.out.println(i);
		
		System.out.println();
		// 비교 연산자------------
		// 비교 결과 true 또는 false 반환
		// >, <, >=, <=   boolean을 제외한 기본형 변수 값 비고
		//       ==, !=   기본형은 값 비교, 참조형은 주소 비교
		//                모든 자료형에 사용 가능 단, 문자열 비교 X
		// instanceof     특정 객체의 인스턴스 여부 비교
		String question = "질문) 1부터 10 사이의 모든 정수를 더한 값은?";
		int answer = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10;
		int input = 50;
		boolean result = answer == input;
		System.out.println(question);
		System.out.println("입력) " + input);
		System.out.println("정답) " + answer);
		System.out.println("판정) " + result);
		
		
		
		System.out.println();
		System.out.println("- instanceof 연산자 -");
		
		// 현재 클래스의 인스턴스 o 생성
		Operator o = new Operator();
		// 객체 o가 Operator 클래스의 인스턴스이면 true를 출력 아니면 false를 출력
		System.out.println(o instanceof Operator);
		// 참조변수 o가 Object 클래스의 인스턴스이면 true를 출력 아니면 false를 출력
		System.out.println(o instanceof Object);
		// 참조변수 o가 MuInCar 클래스의 인스턴스이면 true를 출력 아니면 false를 출력
//		System.out.println(o instanceof MuInCar);
		
		
		// Shape 클래스의 객체 s 생성
//		Shape s = new Shape();	// X
//		패키지가 다르므로 클래스 이름만 명시 X
//		패키지를 포함한 전체 클래스 이름을 명시하거나
//		import 키워드를 이용하여 사용 O
//		javaz.oop.Shape s = new Shape();
//		단, 접근제어자에 의해 사용 가능 여부 결정
		
		// Circle 클래스의 객체 c 생성
		// Rectangle 클래스의 인스턴스 r 생성
		
		
		
		System.out.println();
		// 논리 연산자------------
		// 비교 결과 true 또는 false 반환
		// 먼저 조건을 만족하면 나머지 검사 X
		// &&가 ||보다 우선순위가 높음
		// &&  AND 연산 - 모든 조건이 true일 때 true
		// ||  OR  연산 - 한 조건이라도 true이면 true
		// !   NOT 연산 - true이면 false, false이면 true
		input = 333;
		
		// input이 3의 배수이면 true 아니면 false 출력
		result = input % 3 == 0;
		System.out.println(result);
		
		// input이 3의 배수가 아니면 true 아니면 false
		result = input % 3 != 0;
		System.out.println(result);
		
		// input이 100 미만의 3의 배수면 true 아니면 false
		result = input < 100 && input % 3 == 0;
		System.out.println(result);
		
		// input이 100 이상이거나 3의 배수이면 true 아니면 false
		result = input >= 100 || input % 3 == 0;
		System.out.println(result);
		
		System.out.println();
		// 대문자, 소문자, 알파벳 여부 구분------------
		char ch = 'x';
		boolean lower, upper, alpha;
		
//		Pattern pattern = Pattern.compile("[A-Z]");
//		System.out.println(pattern.matcher(ch + "").matches());
		
		lower = (int)ch >= 97 && (int)ch <= 122;
		lower = ch >= 'a' && ch <= 'z';
		upper = (int)ch >= 65 && (int)ch <= 90;
		upper = ch >= 'A' && ch <= 'Z';
		alpha = lower || upper;
		
		System.out.println("입력문자: " + ch);
		System.out.println("대문자: " + upper);
		System.out.println("소문자: " + lower);
		System.out.println("알파벳: " + alpha);	
		
		System.out.println();
		// 비트 연산자------------
		// 이진 비트 연산 수행
		// float, double을 제외한 기본형에 사용
		// 정수값의 특정 위치에 있는 비트 마스크 추출 가능
		// 특정 센서값 검사 등
		// 1: true, 0: false
		// ~: NOT
		// &: AND
		// |: OR
		// ^: XOR
		int x = 0b0011; // 3
		int y = 0b0100; // 4
		
		System.out.println(x ^ y);
		System.out.println(x | y);
		System.out.println(x & y);
		System.out.println(~x);
		System.out.println(~y);		
		
		System.out.println();
		// 시프트 연산자------------
		// 비트 이동 연산자
		// 곱셉 또는 나눗셈 연산자보다 빠름
		// << : 왼쪽으로 n비트 이동
		// >> : 오른쪽으로 n비트 이동, 부호비트 유지 O
		// >>>: 오른쪽으로 n비트 이동, 부호비트 유지 X 
		int z = 0b0100; // 4
		System.out.println(4 << 1);
		System.out.println(4 >> 1);
		System.out.println(4 >>> 1);
		System.out.println(-4 >>> 1); // 10...100 > 01...010
		System.out.println(-4 >> 1); // 1...100 > 1...010
		
		System.out.println();
		// 조건(삼항) 연산자------------
		// 3개의 피연산자 필요
		// (조건식) ? 식1 : 식2;
		x = -3;
		y = -4;
		
		String rs = x >= 0 ? "양수" : "음수";
		System.out.println(rs);
		
		int max = 0;
		    min = 0;
		
		max = x > y ? x : y;
		min = x < y ? x : y;
		System.out.println("max: " + max);
		System.out.println("min: " + min);
		
		System.out.println();
		// 짝수 홀수 구분------------
		x = 3;
		ch = x % 2 == 0 ? '짝' : '홀';
		
		System.out.println(x + "는 " + ch + "수입니다.");
		
		// 윤년 계산------------
		// - 특정 연도를 4로 나누어서 나머지가 없고 100으로 나누어 나머지가 있으면 윤년
		// 단, 400으로 나누어 나머지가 없으면 윤년
		// 2000, 2004, 2008, ..., 2024, ...
		year = 2008;
		rs = year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 
			"윤년" : "평년";
				
		System.out.println(year + "년은 " + rs);
				
	} // END main()

} // END class
