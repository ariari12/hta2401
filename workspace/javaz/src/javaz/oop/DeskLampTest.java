package javaz.oop;

public class DeskLampTest {

	public static void main(String[] args) {
		try {
			DeskLamp myLamp = new DeskLamp(); // 1. DeskLamp 클래스의 객체 myLamp 생성
			myLamp.powerOn(); // 2. myLamp 켜기

			System.out.println(myLamp);

			myLamp.setBrightness(3); // 4. myLamp의 밝기를 3으로 설정
			System.out.println("램프의 밝기: " 
					+ myLamp.getBrightness()); // 5. myLamp의 현재 밝기를 화면에 출력

			myLamp.powerOff(); // 3. myLamp 끄기

			// 예외처리 확인
//			myLamp.setBrightness(10);
//			System.out.println(myLamp);
			
			////////////////////////////////////
			// 6. DeskLamp 클래스의 인스턴스 redLamp 생성
			DeskLamp redLamp = new DeskLamp();
			
			// 7. redLamp의 전원 켜기
			redLamp.powerOn();
			
			// 8. redLamp의 밝기를 2로 설정
			redLamp.setBrightness(2);
			
			// toString() 메서드를 오버라이딩 하지 않은 채로 사용할 경우 주소값(해시코드)이 나타남
			// System.out.println(myLamp);  // javaz.oop.DeskLamp@762efe5d
			// System.out.println(redLamp); // javaz.oop.DeskLamp@64cee07
			
			// 9. redLamp의 밝기와 전원 상태 출력
			System.out.println(redLamp);
			
			// 10. myLamp의 밝기와 전원 상태 출력
			System.out.println(myLamp.toString());
			
		} catch (IllegalArgumentException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}
