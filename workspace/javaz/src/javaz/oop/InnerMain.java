package javaz.oop;

class Outer {
	class Member {
		public void method() {
			System.out.println("Member inner method()");
		}
	}

	static class Static {
		public static void method() {
			System.out.println("Static inner method()");
		}
	}

	public void method() {
		class Local {
			public void method() {
				System.out.println("Local inner method()");
			}
		}
		new Local().method();
	}
}

// 1. 접근 제한이 없고 매개변수와 변환값이 없는 추상 메서드 method를 갖는
//    추상 클래스 Abstract 정의
abstract class Abstract {
	public abstract void methodA();
}

// 2. 접근 제한이 없고 매개변수와 변환값이 없는 추상 메서드 method를 갖는
//   인터페이스 Interface 정의
interface Interface {
	public abstract void methodB();
}

// 3. 1번의 클래스를 상속받고, 2번의 인터페이스를 구현하는 AnonyClass 정의
class AnonyClass extends Abstract implements Interface {
	
	@Override
	public void methodA() {
		System.out.println("methodA()");
	}

	@Override
	public void methodB() {
		System.out.println("methodB()");
	}
}

public class InnerMain {
	public static void main(String[] args) {
//		Abstract a = new Abstract();	// 추상 클래스는 객체 생성 불가
//		Interface i = new Interface();  // 인터페이스도 객체 생성 불가
		
		Abstract a = new AnonyClass();  // 자식 타입으로는 가능
		Interface i = new AnonyClass(); // 자식 타입으로는 가능
		
		// 4. 3번 클래스의 객체를 생성하여 1번 클래스의 메서드를 호출
		AnonyClass ac = new AnonyClass();
		ac.methodA();
		
		// 5. 4번의 인스턴스를 이용하여 2번 클래스의 메서드 호출
		ac.methodB();
		
		/////////////////////////////////////////
		Abstract aa = new Abstract() {
			@Override
			public void methodA() {
				System.out.println("추상 클래스를 구현한 익명의 이너 클래스");
			}
		};
		
		aa.methodA();
		
		new Interface() {	// 참조변수 X
			@Override
			public void methodB() {
				System.out.println("인터페이스를 구현한 익명의 이너 클래스");
			}
		}.methodB();
		
		Interface ii = new Interface() {	// 참조변수 O
			@Override
			public void methodB() {
				System.out.println("인터페이스를 구현한 익명의 이너 클래스 ii");
			}
		};
		
		ii.methodB();
		
		
		// 람다식으로 구현
		Interface iii = () -> {		// 람다식
			System.out.println("인터페이스를 람다식으로 구현");
		};
		
		iii.methodB();
		/////////////////////////////////////////
		
		Outer o = new Outer();
//		Member m = new Member();
		Outer.Member m = o.new Member();
		m.method();

		Outer.Static.method();
		
		o.method();
	}

}
