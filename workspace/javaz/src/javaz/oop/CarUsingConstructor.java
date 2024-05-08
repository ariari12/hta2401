package javaz.oop;

public class CarUsingConstructor {

	public static void main(String[] args) {
		// 1. Car 클래스의 기본생성자를 이용하여 myCar 객체를 생성
		Car myCar = new Car();
		
		//1.2 myCar 객체의 door의 수를 3으로 지정
		myCar.door = 3;
		
		// 2. Car 클래스의 door 개수를 매개변수로 받아서 초기화하는 생성자를 이용하여 door4car 인스턴스 생성
		Car door4car = new Car(4);
		
		// 3. myCar 참조변수와 door4car를 문자열로 출력
		//    - 필요한 메서드를 Car 클래스에 정의하여 처리
		//    - 출력 형태
		//    [color: ~~, maker: ~~, door: ~~개]
		System.out.println(myCar);
		System.out.println(door4car);
		
		// 4. color는 orange, maker는 BMW, door는 2개, key는 false를 매개변수 값으로 전달하여
		//    Car 클래스의 인스턴스 bCar 생성
		Car bCar = new Car("orange", "BMW", 2, true);
		
		// 5. Car 클래스의 객체를 문자열로 출력하는 메서드를 수정하여 key의 상태도 함께 출력하도록 처리
		//    (key가 true이면 "key : OK", false이면 "key : Not OK"로 표시)
		System.out.println(bCar);

	}

}
