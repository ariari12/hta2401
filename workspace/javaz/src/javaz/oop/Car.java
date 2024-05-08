package javaz.oop;

public class Car {
	// 상태 / field / 속성 / property
	public    String color; // 접근제한 X
	protected String maker; // 동일패키지 또는 하위 패키지
			  int door;     // 동일 패키지
	private   boolean key;  // 현재 클래스 내에서만 사용 가능
	
	// 기본생성자
	public Car() {
		// 생성자를 하나도 정의하지 않은 경우 컴파일러에 의해 자동으로 추가됨
		// 그렇지 않은 경우, 필요에 따라 직접 정의 필요
		System.out.println("-- 자동차가 생성되었습니다. --");		
	}
	
	// door의 개수를 매개변수로 받아서 초기화하는 생성자
	public Car(int door) {
		this(); // 기본생성자 호출 - O
				// 반드시 생성자의 첫번째 줄에 명시
		this.door = door;
//		this(); // 기본생성자 호출 - X
	}
	
	// 멤버 변수의 값들을 매개변수로 받아서 초기화하는 생성자
	// 단, door는 door의 개수를 매개변수로 받는 생성자를 이용
	public Car(String color, String maker, int door, boolean key) {
		this(door);
		this.color = color;
		this.maker = maker;		
		this.key = key;		
	}

	// 동작 / method / 기능 / 멤버변수
	public void start() {
		// 키가 true가 아니면 "키를 꽂아주세요."를 출력
		if (!key) {
			System.out.println("키를 꽂아주세요.");
		} else { // 그렇지 않으면 "[color] 자동차가 출발합니다."를 출력
			System.out.println(color + " 자동차가 출발합니다.");
		}
	}

	public final void stop() { // 오버라이딩 불가능 메서드
		System.out.println("자동차가 멈춥니다.");
	}
	
	// key 필드의 값을 설정하는 메서드
	public void setKey(boolean key) {
		this.key = key;
	}
	
	// key 필드의 값을 반환하는 메서드
	public boolean getKey() {
		return key;
	}
	
	public String toString() {
		return "[color: " + color + ", maker: " + maker + ", door: " + door + "개, key: "
				+ (key ? "OK" : "Not OK") + "]";
	}

}
