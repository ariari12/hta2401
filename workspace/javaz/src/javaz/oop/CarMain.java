package javaz.oop;

public class CarMain {

	public static void main(String[] args) {
		Car yellowCar; // Car 클래스의 참조 변수 선언
		yellowCar= new Car(); // 선언된 변수 생성
		
		yellowCar.color = "노랑";
		yellowCar.maker = "기아";
		yellowCar.door = 4;
//		yellowCar.key = false;
		// key 필드가 private으로 선언되었기 때문에 아래처럼 사용 X
		yellowCar.setKey(false); // 이렇게는 가능
		
		//////////////////////
		// Car 클래스의 객체 redCar 생성	-> 인스턴스화 (redCar은 Car의 인스턴스)
		// 인스터스 = 객체 = 오브젝트
		Car redCar = new Car();
		
		redCar.color = "빨강";    
		redCar.maker = "현대";
		redCar.door = 2;
//		redCar.key = true;
		// key 필드가 private으로 선언되었기 때문에 아래처럼 사용 X
		redCar.setKey(true); // 이렇게는 가능
		
		//////////////////////
		System.out.println("--yellowCar--");
		System.out.println(yellowCar.color + " 자동차가 생성되었습니다.");
		System.out.println("제조사: " + yellowCar.maker);
		System.out.println("문의 개수: " + yellowCar.door);
		// System.out.println("키 상태: " + yellowCar.key);
		// key 필드가 private으로 선언되었기 때문에 위처럼 사용 X
										   // 아래처럼은 가능
		System.out.println("키 상태: " + yellowCar.getKey());
		
		// 1. key 필드의 값을 true로 변경
		yellowCar.setKey(true);
		// 2. key 필드의 값을 반환하는 메서드를 호출하여 반환되는 값을 keyStatus 변수에 저장하고 
		boolean keyStatus = yellowCar.getKey();
		// 3. 키 상태를 화면에 출력
		System.out.println("변경 후 키 상태: " + keyStatus);
		
		yellowCar.start(); // Car 클래스의 start() 호출		
		
		//////////////////////
		System.out.println("--redCar--");
		System.out.println(redCar.color + " 자동차가 생성되었습니다.");
		System.out.println("제조사: " + redCar.maker);
		System.out.println("문의 개수: " + redCar.door);
		// System.out.println("키 상태: " + redCar.key);
		// key 필드가 private으로 선언되었기 때문에 위처럼 사용 X
		   								 // 아래처럼은 가능
		System.out.println("키 상태: " + redCar.getKey());
		redCar.start(); // Car 클래스의 start() 호출
		
		System.out.println();
		System.out.println("-- 전기차 만들기 --");
		// ElectricCar 클래스의 객체 ec 생성
		ElectricCar ec = new ElectricCar();
		
		// 생성된 객체를 문자열로 출력
		System.out.println(ec);
		
		// ec의 색을 silver로 지정
		ec.color = "silver";
		
		// ec의 maker는 KIA로 지정
		ec.maker = "KIA";
		
		// ec의 door는 4개로 지정
		ec.door = 4;
		
		// ec의 key를 true로 지정
		ec.setKey(true);
		
		// 생성된 객체를 문자열로 출력
		System.out.println(ec);
		
		// ec의 현재 배터리 용량 출력
		System.out.println("현재 배터리 용량: " + ec.getBattery());
		
		// ec를 출발 시키기
		ec.start();
		
		System.out.println();
		System.out.println("- 미쉐린 타이어를 장착한 전기차 만들기 -");
		// 배터리 용량은 10_000
		
		// Tire 여러개 받아서 전달
		Tire tire1 = new Tire("미쉐린 타이어", "앞 왼쪽");
		Tire tire2 = new Tire("미쉐린 타이어", "앞 오른쪽");
		Tire tire3 = new Tire("미쉐린 타이어", "뒤 왼쪽");
		Tire tire4 = new Tire("미쉐린 타이어", "뒤 오른쪽");
		
		ElectricCar elecCar = new ElectricCar(10_000, tire1, tire2, tire3, tire4);
		
		System.out.println();
		System.out.println("- 미쉐린 타이어를 장착한 전기 툭툭이 만들기 -");
		// 배터리 용량은 5_000
		
		tire2.setPosition("앞");
		tire2 = new Tire("미쉐린 타이어", "앞");
		
		// Tire 배열 받아서 전달
		Tire[] tires3 = { tire2, tire3, tire4 };
		
		ElectricCar elecTuk = new ElectricCar(5_000, tires3);
		elecTuk = new ElectricCar(5_000, tire2, tire3, tire4);
		
		
		System.out.println();
		System.out.println("- 미쉐린 타이어를 장착한 전기 오토바이 만들기 -");
		// 배터리 용량은 1_000
		
		tire3.setPosition("뒤");
		
		ElectricCar elecBike = new ElectricCar(1_000, tire2, tire3);

	}

}
