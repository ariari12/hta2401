package javaz.basic;

// variable 변수
// - 가변적인 하나의 값을 저장하는 메모리 공간
//  - 변수의 이름: 메모리 주소에 붙인 이름
// - 가장 마지막에 저장된 값을 기억
// - 중괄호 블럭 { } 내부에 선언하여 사용
//  - 선언: 변수의 종류에 따라 각기 다른 크기의 메모리 공간 확보
// - 선언된 블럭 내에서만 유효
// - 선언 후 초기화 또는 선언과 동시에 초기화하거나 선언 시 자동으로 초기화 되어 사용

// 변수의 종류
// - 인스턴스 변수: 특정 객체가 소유
// - 클래스 변수: 모든 객체가 공유
//
// - 지역 변수: 특정 { } 블럭 내에 선언
// - 매개 변수: 메서드 호출 시 전달되는 값을 저장
//           parameter
//
// - 기본자료형: primitive type 8가지
//			  정수형: byte, short, int, long
//			  실수형: float, double
//			  문자형: char
//			  논리형: boolean
// - 참조자료형: 참조하는 객체의 주소 ex)배열, 클래스, 인터페이스, 열거형

// 변수 선언
// - 데이터타입 변수이름;

// 변수 초기화
// - 선언된 변수에 가장 처음 값을 저장하는 것
// - 특정 값으로 직접↓ 저장하거나 자동으로 초기화 됨
// - 선언된 변수 이름 = 리터럴|변수|연산|특정 메서드의 반환값;

// 변수의 선언 및 초기화
// - 데이터타입 변수이름 = 리터럴|변수|연산|특정 메서드의 반환값;

// var 키워드
// - 자바10부터 사용 가능
// - 저장되는 데이터에 따라 타입이 자동 결정됨

// constant 상수
// - 변하지 않는 하나의 값을 저장하는 메모리 공간
// - 가장 처음에 저장된 값만 기억
// - 선언과 동시에 초기화하거나 생성자에서 초기화
// - final 키워드 및 static 키워드와 함께 사용

public class Variable {
	public static void main(String[] args) {
		// 3.14를 저장하는 변수 pi
		double pi = 3.14;
		
		// 3.14를 저장하는 상수 PI_II
		final double PI_II = 3.14;		
		
		
//		var guess; // 값 미지정 불가
		var guess = "some data";
//		guess = 123; // 문자열 변수에 정수 저장 불가
		
		// 논리형 및 문자형 변수 선언
		boolean tf;
		char c;

		// 선언된 변수를 초기화
		tf = true;
		tf = false;
		
//		c = A; // 단일 따옴표로 묶어주어야 함
		c = 'A';
		c = 66;
		c = '\u0043';
		
		c = 0b01000100; // 2진수
		c = 0104;		// 8진수
		c = 0x44;		// 16진수

		// 선언된 변수 사용		
		System.out.println(c);
		c = 'n';
		System.out.println(c);
		c = '\n';
		System.out.println(c);
//		c = '''; // 구문 오류 발생
		c = '\'';
		System.out.println(c);
//		c = '\'; // 구문 오류 발생
		c = '\\';
		System.out.println(c);
		System.out.println(tf);
		
		//////////////////////
		// 정수형 변수 선언 및 초기화
		byte 	b = 127; 				  // 1바이트
		short 	s = -32768; 			  // 2바이트
		int 	i = 2100000000; 		  // 4바이트
//		long 	l = 9223372036854775807;  // 8바이트 
		// int 범위를 넘어가는 숫자를 저장하려는 경우 뒤에 L 혹은 l 붙임
		long 	l = 9223372036854775807L;  // 8바이트
				l = 9223372036854775807l;
				
		// 실수형 변수 선언 및 초기화
//		float f = 0.12345678901234567890;	// 4바이트
		// float의 범위보다 소숫점이 긴 값을 저장할 경우 F 혹은 f 붙임
		float f = 0.12345678901234567890F;	// 4바이트
			  f = 0.12345678901234567890f;
		double d = 0.12345678901234567890;	// 8바이트
		// 소수 아래 9번째까지 출력(반올림)
		System.out.println(f);
		// 소수 아래 17번째까지 출력(반올림)
		System.out.println(d);
		
		// 동일한 데이터 타입의 선언
		int dec, bin, oct, hex;
		dec = 10;		System.out.println(dec);
		bin = 0b1010;	System.out.println(bin);
		oct = 0012;		System.out.println(oct);
		hex = 0x0A;		System.out.println(hex);
		
		// 동일한 데이터 타입의 선언 및 초기화
		int aa= 1, bb = 2, cc = 3;
		
		////////////////////////////////
		// String 클래스
		// - 문자열 관련 처리
		// - 두 자 이상의 문자열 저장
		//   - "로 묶어서 저장
		// - +를 이용하여 문자로 결합 가능
		// - printf()를 이용하여 형식 문자열 출력
		//   문자열: %s
		//   정수: %d
		//   실수: %f
		//   논리값: %b
		
		System.out.println("--------------");
		// 문자열 Lee를 name 변수에 저장
		String name = "Lee";
		int kor = 90;
		int eng = 80, math = 77;
		int sum = kor + eng + math;
		double avg = sum / 3.0;
		
		System.out.println("이름: " + name);
		System.out.println("합계: " + sum);
		// 아래와 같이 출력할 경우, 문자 사이의 결합으로 인식되어 '908070' 출력됨
		System.out.println("합계: " + kor + eng + math);
		// 아래와 같이 int끼리 괄호 연산할 경우, 숫자 합산으로 인식되어 '240' 출력됨
		System.out.println("합계: " + (kor + eng + math));		
		
		System.out.println("평균: " + avg);
		System.out.println("평균: " + sum / 3);
		System.out.println("평균: " + sum / 3.0);
		System.out.printf("평균: %f\n", sum / 3.0);
		System.out.printf("평균: %.2f", avg);
		System.out.println();
		// 정수,소수 부분 합해서 9자리 출력: 결과가 9자리 미만일 경우 왼쪽에 공란 추가
		System.out.printf("평균: %9.2f", avg);
		System.out.println();
		System.out.println("평균: " + String.format("%.2f", avg));
		System.out.printf("sum * 100 : %d\n", sum * 100);
		System.out.printf("sum * 100 : %,d\n", sum * 100);
		System.out.print("print()t\t");
		System.out.print("print()");
	}

}
