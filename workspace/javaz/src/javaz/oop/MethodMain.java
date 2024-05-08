package javaz.oop;

public class MethodMain {

	public static void main(String[] args) {
		Method m1 = new Method(); // 1. Method 클래스의 참조 변수 m1 생성		
		
//		m1.type = "원circle"; // 2. m1의 type을 원으로 저장
//		type은 private으로 선언되었기 때문에 외부에서 접근 불가
		
		m1.setType("사각형"); // 2.2 Method 클래스의 주석 4번 메서드를 이용하여 
							   // type을 사각형으로 저장
		System.out.println(m1.getType()); // 2.3 Method 클래스의 주석 5번 메서드를 이용하여 
										  // 반환되는 값을 화면에 출력

		
		m1.area(); // 3. m1의 area 메서드 호출
		
		System.out.println(m1);
		
		System.out.println();
		// 4. Method 클래스의 객체 square 생성
		Method square = new Method();
		// 5. Method 클래스의 참조변수 rect 생성
		Method rect = new Method();
		// 6. Method 클래스의 인스턴스 circle 생성
		Method circle;
		// 6.1. circle을 생성
		circle = new Method();
		
		// 7. square의 type을 정사각형으로 설정
		square.setType("정사각형");
		System.out.print("가로, 세로 5인 정사각형의 넓이: ");
		square.area(5);
		// 8. rect의 type을 직사각형으로 설정
		rect.setType("직사각형");
		System.out.print("가로 5, 세로 3인 직사각형의 넓이: ");
		rect.area(5, 3);
		// 9. circle의 type을 동그라미로 설정
		circle.setType("동그라미");
		System.out.println("반지름이 5.3인 원의 넓이: " + circle.area(5.3));
		
		System.out.println();
		// 10. 생성된 각 객체를 문자열로 출력
		System.out.println(square);
		System.out.println(rect);
		System.out.println(circle);

	}

}
