package javaz.oop;

// 1. 매개변수, 반환타입, 접근제한이 없는 draw 메서드를 갖는 Shape 클래스 정의
class Shape {
	public void draw() { 
		System.out.println("형태를 그립니다.");
	}
	
} // END Shape class

// 2. Shape 클래스를 상속받는 Circle 클래스 정의
class Circle extends Shape {
	// 2.1 부모 메서드를 재정의하여 "원을 그립니다."를 출력
	@Override
	public void draw() {
		System.out.println("원을 그립니다.");
	}
	
	// 원의 넓이를 구하는 공식을 출력하는 area 메서드 작성
	public void area() {
		System.out.println("원의 넓이를 구하는 공식: [반지릅] * [반지름] * [원주율(3.14)]");
	}
	
} // END Circle class

// 3. Shape 클래스를 상속받는 Rectangle 클래스 정의
class Rectangle extends Shape {
	// 3.1 부모 메서드를 재정의하여 "사각형을 그립니다."를 출력
	@Override
	public void draw() {
		System.out.println("사각형을 그립니다.");
	}
	
	// 사각형의 넓이를 구하는 공식을 출력하는 area 메서드
	public void area() {
		System.out.println("사각형의 넓이를 구하는 공식: [가로] * [세로]");
	}
	
} // END Rectangle class

public class ShapePolyMain {

	public static void main(String[] args) {
		// 4. Shape 클래스의 인스턴스 shape 생성
		Shape shape = new Shape();
		shape.draw();
		
		
		// 5. shape을 Circle 클래스 타입의 객체로 변환
		//	  5.1 shape의 인스턴스 메서드 호출
		shape = new Circle();
		shape.draw();
//		shape.area()	// 상속시켜 준 메서드가 아니므로 사용 불가(부모 클래스에 해당 메서드가 정의되어 있지 않음)
		
		// 6. shape을 Rectangle 클래스 타입의 객체로 변환
		//	  6.1 shape의 인스턴스 메서드 호출
		shape = new Rectangle();
		System.out.println(shape instanceof Shape);
		System.out.println(shape instanceof Object);
		System.out.println(shape instanceof Circle);
		System.out.println(shape instanceof Rectangle);
		shape.draw();

	}

}
