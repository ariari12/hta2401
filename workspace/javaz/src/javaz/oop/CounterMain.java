package javaz.oop;

class Counter {
	// Counter 클래스의 객체가 생성될 때 생성되는 참조변수의 이름을 매개변수로 넘겨받아서 
	// 인스턴스 필드 instaceName을 초기화하는 생성자를 작성
		// 생성된 인스턴스의 누적 개수는 instanceNum에 저장
		// 생성된 인스턴스의 이름과 누적 개수를 다음과 같이 출력
		// "생성된 인스턴스이름: ~~, 누적 인스턴스 수: ~~"
	// 단, 모든 필드는 외부 클래스에서 접근할 수 없도록 처리
	private String instanceName;
	private static int instanceNum;
	
	public Counter(String instanceName) {
		this.instanceName = instanceName;
//		instanceNum = instanceNum + 1;
//		instanceNum += 1;
		instanceNum++;
		System.out.println("생성된 인스턴스 이름: " + instanceName 
				+ ", 누적 인스턴스 수: " + instanceNum);
	}
}

public class CounterMain {
	public static void main(String[] args) {
		// Counter 클래스의 객체 3개 생성
		// 단, 인스턴스의 이름은 임의로 지정하고 3번째 객체는 참조변수 없이 처리
		Counter c1 = new Counter("1호");
		Counter c2 = new Counter("2호");
		new Counter("3호");
	}

}
