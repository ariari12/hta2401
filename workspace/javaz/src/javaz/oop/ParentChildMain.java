package javaz.oop;

class Parent { // 1. 기본생성자를 작성하여 "1. Parent()"를 출력
	Parent() {
		System.out.println("1. Parent()");
	}
	
	// 매개변수, 반환타입, 접근 제한이 없는 method 메서드 작성
	// "Parent's method"를 화면에 출력
	public void method() {
		System.out.println("Parent's method");
	}
}


final class Child extends Parent { // 2. Parent 클래스를 상속받도록 처리
	Child() { // 3. 기본생성자를 작성하여 "2. Child()"를 출력
//		super();	 //    부모 클래스의 기본 생성자는 자동으로 호출되지만
				     //    명시적으로 호출하는 경우에 사용
		System.out.println("2. Child()");
	}
	
	// 부모 클래스의 인스턴스 메서드를 오버라이딩하여
	// "Child's method"를 화면에 출력
	@Override
	public void method() {
		System.out.println("Child's method");
	}
}


//5. Child 클래스를 상속받는 GrandChild 클래스 작성
class GrandChild /* extends Child */ {  }
				 // final로 선언된 클래스 상속 불가


public class ParentChildMain {
	public static void main(String[] args) {
		Parent p = new Parent();

		Child c = new Child(); // 4. Child 클래스의 인스턴스 c 생성		
		
		// p 객체의 인스턴스 메서드 호출
		p.method();
		
		// c 객체의 인스턴스 메서드 호출
		c.method();
		
		System.out.println();
		System.out.println("- up casting -");
		p = c;
		p.method();
		
		System.out.println();
		System.out.println("- down casting -");
		c = (Child) new Parent();
		c.method();
		
//		GrandChild gc = new GrandChild(); // 6. GrandChild 클래스의 객체 gc 생성
		
	}
}
