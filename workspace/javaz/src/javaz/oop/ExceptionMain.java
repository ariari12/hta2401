package javaz.oop;

import javaz.api.SamchoException;

public class ExceptionMain {

	public static void main(String[] args) {
		// 1. 직접 예외 처리
		try { // 예외 발생 가능 영역
			System.out.println("1. 예외 발생 전");
			System.out.println(args[2]);	// 2. 예외 발생
			System.out.println("3. 예외 발생 후");
		} catch(NullPointerException e) {
			System.out.println("4. 관련 없는 예외 객체 i");
		} catch(NumberFormatException | ArithmeticException e) {
			    // 여러 예외를 처리하고 싶은 경우
			System.out.println("5. 관련 없는 예외 객체 ii");
//		} catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("6. 예외 잡기 성공!");
		} catch(Exception e) {
			System.out.println("7. 최상위 예외 객체");
			e.printStackTrace();
			System.out.println("예외 메시지: " + e.getMessage());
		} finally {
			System.out.println("8. 예외 발생 여부 관계없이 실행");
		}
		System.out.println("-- END main() --");
		
		// 원금 10_000원을 매개변수로 전달하여 Cal 클래스의 eeja 메서드 호출
		// 예외 발생 시 직접 처리하여
		// 발생한 예외 메시지를 화면에 표시 -- eeja() 안에서 try / catch 진행
		Cal c = new Cal();
		c.eeja(10_000);
		
		// 고객이름은 Lee 원금은 20_000원에 대한 이자를 계산하는 eeja 메서드 호출
		Cal.eeja(20_000, "Lee");
		try {
			Cal.eeja(30_000, null);
		} catch(NullPointerException e) {
			System.out.println("NullPointerException 발생!!");
		}
		
		// 임의의 정수를 매개변수로 전달하여 countdown 메서드 호출
		ExceptionMain em = new ExceptionMain();
		try {
			em.countdown(2);
		} catch (InterruptedException e) {
			System.out.println("예외발생!!");
		} catch (SamchoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 카운트 다운 횟수를 매개변수로 넘겨받아
	// 1초마다 카운트 다운되는 값을 화면에 표시하는 인스턴스 메서드 countdown
	// 예외 상황이 발생하는 경우 메서드를 호출한 쪽으로 예외 처리 위임
	public void countdown(int num) throws InterruptedException, SamchoException {
		// 3. 만약 카운트 다운 횟수가 3 미만인 경우
		//    "카운트 다운은 3초 이상으로 지정해주세요."를 메시지로 사용자정의 예외 던지기
		//    - 발생한 예외는 호출한 쪽으로 위임 처리
		if (num < 3) {
			throw new SamchoException("카운트 다운은 3초 이상으로 지정해주세요.");
		}
		
		System.out.println("-- 카운트 다운 --");
		for (int i = num; i >= 0; i--) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		System.out.println();
	}
}

class Cal {
	public static final double RATE = 0.05;
	
	// 고객이름과 원금을 매개변수로 받아서 이자를 계산한 후
	// 화면에 표시하는 공유 메서드 eeja
	// 예외 상황이 발생하는 경우 메서드를 호출한 쪽으로 예외 처리 위임
	   // throws 통해 던진 후 main() 에서 try / catch 진행
	public static void eeja(int wongum, String name) throws NullPointerException {
		System.out.println("고객명: " + name);
		System.out.println("고객명: " + name.toUpperCase());
		System.out.println("원금: " + wongum);
		System.out.println("이자: " + wongum * RATE);
	}
	
	public void eeja(int wongum) {
		try {
		System.out.println(wongum / 0);
		} catch (ArithmeticException e) {
			System.out.println("예외 발생!");
			System.out.println("예외 메시지: " + e.getMessage());
		}
	}
}
