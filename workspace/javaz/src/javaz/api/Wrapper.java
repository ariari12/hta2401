package javaz.api;

// Wrapper 클래스
// - 기본형을 참조형으로 표현하는 클래스들
//   - 기본 데이터 형을 값이 아닌 객체로 사용 가능
// - 메서드의 매개변수로 기본형이 아닌 참조형을 전달하거나,
//   클래스가 제공하는 상수 또는 메서드 사용 가능
// - Boolean, Character
// - Byte, Short, Integer, Long, Float, Double
//   - 추상 클래스 Number의 하위 클래스

// Boxing  : 기본형 -> 참조형
// Unboxing: 참조형 -> 기본형
public class Wrapper {
	public static void main(String[] args) throws ArithmeticException {
		double d = 3.14;
//		d.~~~ // X
		Double dd = 3.14;          // auto boxing
		dd = Double.valueOf(3.14); // boxing
		d = dd.doubleValue();	   // unboxing
		
		System.out.println(d);
		System.out.println(dd);
		
		Integer i = 123;
		int ii = Integer.valueOf(123);
		
		// 2. String > int ----------------------
//		ii = "456";
		try {
			ii = Integer.valueOf("456");
			ii = Integer.parseInt("567");
			ii = Integer.parseInt("오륙칠");
		} catch (NumberFormatException e) {
			System.out.println("한글은 숫자로 파싱 불가");
		}
		
		// 3. int > String
		String s1 = String.valueOf(100);   // 100;
		String s2 = 200 + "";			   // 200;
		String s3 = Integer.toString(300); // 300;
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
		// int 범위의 가장 큰 값
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
//		System.err.println(5/0);
		
		System.out.println(5 / 0.0);
		System.out.println(Double.isInfinite(5 / 0.0));
		System.out.println();
		
		System.out.println(5 % 0.0);
		System.out.println(Double.isNaN(5 % 0.0));
		
		///////////////////////////////////
		System.out.println();
		// 명령행 매개변수로 단수를 입력받아
		// 구구단을 출력하는 메서드 호출
		new Wrapper().gugudan(args[0]);
//		new Wrapper().gugudan(args);

	}
	
	// 단수를 입력받아 지정된 구구단을 출력하는 gugudan 메서드
	public void gugudan(String arg) {
		try {
			int dan = Integer.parseInt(arg);
		
			if (dan >= 2 && dan <= 9) { 
				System.out.println("----- 구구단 -----");
				System.out.println(dan + "단을 출력합니다.");
				
				System.out.print(dan + " * 1 = " + dan + "\t");
				
				for (int j = 2; j <= 6; j += 4) {
					for (int i = j; i < j + 4; i++) {
						System.out.print(dan + " * " + i + " = " + dan * i + "\t");
					}
					System.out.println();
				}
			}
			
			else {
				System.out.println("구구단은 2 ~ 9 사이의 숫자를 입력해 주세요.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("구구단은 숫자로 입력해주세요.");
		}
	}
	
	
	// 단수를 입력받아 지정된 구구단을 출력하는 gugudan 메서드
	public void gugudan(String[] dans) {
		int[] danNums = new int[dans.length];
		// 숫자로 변환 - try/catch 사용하여 숫자 아닐 경우 메시지
		try {
			for (int i = 0; i < danNums.length; i++) {
				danNums[i] = Integer.parseInt(dans[i]);
			}
		
			// 2 ~ 9 사이가 아닌 경우 - 안내 메시지 출력
			// 전달 받은 배열 갯수만큼 반복하여 구구단 출력
			for (int dan : danNums) {
				if (dan < 2 || dan > 9) { 
					System.out.println("입력값: " + dan);
					System.out.println("2 ~ 9 사이의 숫자를 입력해 주세요.");
					break;
					}
				System.out.println("----- 구구단 -----");
				System.out.println(dan + "단을 출력합니다.");
				for (int i = 1; i <= 9; i++) {
					System.out.println(dan + " * " + i + " = " + dan * i + " ");
				}
				System.out.println();
			}
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해 주세요.");
			System.out.println("종료합니다..");
		}
	}

}
