package javaz.oop;

public class DeskLamp {
	// field / 속성(=property) / 멤버변수
	private boolean power;  // 켜짐: true,     기본값 꺼짐: false
	private int brightness; // 밝기: 1 | 2| 3, 기본값 false

	// method / 기능 / 동작
	public void powerOn() {
		power = true;
		brightness = 1;
		System.out.println("램프가 켜졌습니다.");
	}
	
	public void powerOff() {
		power = false;
		brightness = 0;
		System.out.println("램프가 꺼졌습니다.");		
	}
	
	public void setBrightness(int brightness) {
		if (brightness < 0 || brightness > 3) {
			throw new IllegalArgumentException("0 ~ 3 사이의 숫자를 입력해 주세요.");
		}
		
		if (power == false) {
			throw new IllegalArgumentException("전원이 꺼져있는 상태입니다. 먼저 전원을 켜주세요.");
		}
		
		this.brightness = brightness;
		System.out.println("밝기를 " + this.brightness + "(으)로 설정했습니다.");		
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	// 객체를 문자열로 반환하는 메서드
	public String toString() {
//		return "===== 현재 상태 =====\n전원: " + (power == true ? "켜짐" : "꺼짐") 
//				+ ", 밝기: " + "■".repeat(brightness) + "□".repeat(3 - brightness) 
//				+ "\n==================="; 
		// 전원이 켜진 경우 on, 꺼진 경우 off
		return "램프 전원 : " + (power ? "ON" : "OFF") + ", 밝기 : " + brightness;
							// power == true ? ~~로 해도 동일
	}
}
