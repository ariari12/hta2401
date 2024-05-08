package javaz.basic;

import javaz.oop.Car; // 특정 클래스를 지정하여 임포트
import javaz.oop.*;   // 특정 패키지 전체를 임포트

public class MuInCar extends Car {
// 							 javaz.oop.Car {
							 // import 구문을 사용하지 않은 경우 패키지를 포함한 전체 클래스명을 명시
	
	// 기본 생성자에서 MuInCar의 각 필드의 값을 다음처럼 지정 
		// color는 gold, maker는 테슬라, door는 4개, key는 true
	public MuInCar() {
		color = "gold"; // public이므로 접근 제한 X
		maker = "테슬라"; // 패키지가 다르지만 Car를 상속받았으므로 가능
		// door = 4;    // 패키지가 다르므로 접근 불가
//		key = true;		// private이므로 외부 클래스에서 사용 불가
		setKey(true);   
	}
	
	public static void main(String[] args) {
		// 현재 클래스의 인스턴스 mc 생성
		MuInCar mc = new MuInCar();
		
		// 현재 클래스의 인스턴스를 참조변수 없이 생성
		System.out.println(mc);

	}
}
