package javaz.util;

// Lambda expression 람다식
// - 인터페이스를 구현하는 익명 클래스의 객체 생성 부분을 수식화한 것
// - 구현할 것인 추상 메서드 하나일 때 간단히 표현 가능
//   - 메서드 이름 생략 가능
// - 함수 지향 언어에 가까움
//   - 자바의 메서드는 정식 개체 X -> 독립적으로 정의 불가능
//   - 함수는 스스로 존재 O -> 나중에 실행할 목적으로 전달 가능
//   - 함수를 변수에 저장 또는 매개변수로 전달 가능

// - 함수를 객체로 간주 -> 메서드를 객체로 취급
// - 어떤 인터페이스를 구현할 것인지는 대입되는 인터페이스에 따라 달라짐
//   - 함수적 인터페이스(functional interface)의 익명구현 객체로
//     람다식을 사용
// - 인터페이스이름 객체변수 = (매개변수목록) -> { 실행문 목록 }
//   - 매개변수목록에 변수 이름만 사용 가능
//   - 매개변수가 1개이면 괄호 생략 가능
//   - 매개변수가 없으면 괄호만 표시
//   - 실행문이 1개이면 중괄호 생략 가능
//   - 실행문이 return문 뿐인 경우 return문과 중괄호 동시 생락 가능

// 1. Thread를 상속받는 ThreadA 클래스 정의
class ThreadA extends Thread {

	@Override
	public void run() {
		System.out.println("This is ThreadA");
	} 
	
}

// 2. Runnable을 구현하는 RunnableB 클래스 정의
class RunnableB implements Runnable {

	@Override
	public void run() {
		System.out.println("This is RunnableB");
	}
	
}

@FunctionalInterface // 추상 메서드 하나만 갖는 인터페이스
interface InterfaceA {
	public void method(); // 매개변수 X, 반환값 X
}

// 정수형 매개변수 하나를 받는 추상 메서드 method를
// 함수적 인터페이스 InterfaceB에 선언
@FunctionalInterface
interface InterfaceB {
	public void method(int x); // 매개변수 O, 반환값 X
}

@FunctionalInterface // 추상 메서드 하나만 갖는 인터페이스
interface InterfaceC {
	public double method(); // 매개변수 X, 반환값 O
}

@FunctionalInterface
interface InterfaceD {
	public int method(int x, int y);
}

public class LambdaMain {
	// 정수형 매개변수 2개를 받아서 더한 후 반환하는 공유 메서드 sum 정의
	public static int sum(int x, int y) {
		return x + y;
	}
	
	public static void main(String[] args) {
		InterfaceD d = (arg1, arg2) -> {
			int result = arg1 + arg2;
			return result;
		};
		System.out.println("5 + 5 = " + d.method(5, 5));
		
		d = (arg1, arg2) -> arg1 + arg2;
		System.out.println("6 + 7 = " + d.method(6, 7));
		
		d = (arg1, arg2) -> sum(7, 8);
		
		//////////////////////////////////////////
		System.out.println();
		InterfaceC c = () -> {
			double pi = Math.PI;
			return pi;
		};
		System.out.println("return 1: " + c.method());
		
		c = () -> {
			return Math.PI;
		};
		System.out.println("return 2: " + c.method());
		
		c = () -> Math.PI;  // return문 하나만인 경우 모두 생략 가능
		
		System.out.println("return 2: " + c.method());
		
		//////////////////////////////////////////
		System.out.println();
		InterfaceB b = (ax) -> {
			int result = ax * 5;
			System.out.println(ax + " * 5 = " + result);
		};
		b.method(2);
		
		b = ax -> { // 매개변수가 하나인 경우 ( ) 생략 가능
			int result = ax + 5;
			System.out.println(ax + " + 5 = " + result);
		};
		b.method(3);
		
		//////////////////////////////////////////
		System.out.println();
		// 추상 class implements를 생략하고 정의 가능
		// class A implements InterfaceA~~
		InterfaceA a = () -> {
			String str = "Hi! lambda1";
			System.out.println(str);
		};  // 실행문이 여러 줄이면 { }를 명시
		a.method();
		
		a = () -> {
			System.out.println("Hi! lambda2");
		}; // 실행문이 한 줄이면 { }를 명시해도 되고
		a.method();
		
		a = () ->	// { } 생략도 가능
		System.out.println("Hi! lambda3");
		a.method();
		
		//////////////////////////////////////////
		System.out.println();
		new ThreadA().start();
		
		new Thread(new RunnableB()).start();
		
		// 3. 참조변수 t3에 Thread를 익명의 객체로 구현
		Thread t3 = new Thread() {

			@Override
			public void run() {
				System.out.println("This is Thread t3");
			}
			
		};
		
		t3.start();
		
		// 4. 참조변수가 없는 익명의 객체로 Runnable을 구현
		new Thread(
			new Runnable() {
	
				@Override
				public void run() {
					System.out.println("This is Runnable anony");
				}
				
			}
		).start();
		
		new Thread(
//				() -> { System.out.println("This is Runnable Lambda"); }
				() -> System.out.println("This is Runnable Lambda")
		).start();
		
	}

}
