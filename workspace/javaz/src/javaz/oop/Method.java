package javaz.oop;

public class Method {
	static final double PI = 3.14; // 1. 3.14를 상수 PI에 저장
	private String type; // 2. 문자열 변수 type을 선언
							// 단, 클래스 이부에서는 접근 할 수 없도록 처리

	void area() { // 3. "--넓이 구하기--"를 출력하는 area 메서드 작성
					// 매개변수와 반환 타입은 없음
		System.out.println("--넓이 구하기--");
	}

	// 정사각형의 넓이를 계산하여 출력, 매개변수 : 한 변의 길이
	void area(int length) { // overloading O
		System.out.println(length * length);
	}
	
//	int area(int length) { // overloading X
// 반환타입이 다른 것은 오버로딩 X
// 매개변수의 타입, 개수, 순서가 다른 것은 오버로딩 O
//		System.out.println(length * length);
//		return 0;
//	}
	
	// 직사각형의 넓이를 계산하여 출력
	// 매개변수: 가로, 세로의 길이
	void area(int width, int height) {
		System.out.println(width * height);
	}
	
	// 반지름을 매개변수로 넘겨받아 계산한 후 반환
	double area(double radius) {
		return radius * radius * PI;
	}
	
	// Method 클래스의 객체의 type과 PI값을 문자열로 반환
	public String toString() {
		return "Type: " + type + ", PI: " + PI;
	}

	// 4 type 필드의 값을 설정하는 메서드 작성
	public void setType(String type) {
		this.type = type;
	}

	// 5. type 멤버 변수의 값을 반환하는 메서드 작성
	public String getType() {
		return type;
	}

}
