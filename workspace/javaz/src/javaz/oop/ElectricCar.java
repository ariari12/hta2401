package javaz.oop;

public class ElectricCar extends Car {
	private int battery;
//	private Tire tire1, tire2, tire3, tire4;
	private Tire[] tires;
	
	public ElectricCar() {  }
	 
	public void setBattery(int battery) {
		this.battery = battery;
	}
	
	// 클래스에 Tire 인스턴스 생성하여 생성
//	public ElectricCar(int battery, Tire tire1, Tire tire2, Tire tire3, Tire tire4) {
//		this.battery = battery;
//		this.tire1 = tire1;
//		this.tire2 = tire2;
//		this.tire3 = tire3;
//		this.tire4 = tire4;
//		
//		tire1.setTireInfo();
//		tire2.setTireInfo();
//		tire3.setTireInfo();
//		tire4.setTireInfo();
//	}

	// Tire var args로 받아서 생성: Tire 배열과 동일하게 작동하나, 
	// Tire 배열은 매개변수를 반드시 Tire 배열로만 받아야 함
	// Tire var args는 Tire 배열로 받거나 여러 개의 Tire 필드를 받아서 작업할 수 있음  
	// 위와 동일함
	                               // Tire[] tires(Tire 배열 선언)과 동일 
	public ElectricCar(int battery, Tire ... tires) {
		this.battery = battery;
		this.tires = tires;
		System.out.printf("배터리 용량: %,d\n", battery);
		for (Tire tire : tires) {
			tire.setTireInfo();
		}
	}
	
	public int getBattery() {
		return battery;
	}
	
	// 부모 클래스의 start() 오버라이딩
	@Override
	public void start() {
		// 부모 클래스의 start() 호출
		super.start(); // 부모 클래스의 start() 호출
		if (battery < 1) {  // 배터리가 1미만인 경우
			System.out.println("배터리를 충전해주세요");
			stop();  // 자동차 정지시키기
		}
	}
	
	// 부모 클래스의 final로 선언된 stop() 오버라이딩 X
//	public    void stop() { // O 접근 제한자 동일
//	protected void stop() { // X 부모보다 좁은 범위로
//			  void stop() { // X 접근을 제한할 수
//	private   void stop() { // X 없음
		
//	}
}
