package javaz.oop;

public class Initialization {
	int instanceField = 1; // 인스턴스 변수
	static int staticField = 2; // 클래스 변수(= 모든 인스턴스가 공유함)

	// 인스턴스 초기화 블럭 - 3번째로 실행됨 *new가 없을 경우(객체 생성 X) 호출 안 됨
	{
		System.out.println("3. instance init block");
		System.out.println("   instanceField: " + instanceField);
		System.out.println("   staticField: " + staticField);
	}

	// 클래스 초기화 블럭 - 1번째로 실행됨(메모리에 올라가는 순서)
	static {
		System.out.println("1. class init block");
		// 인스턴스 참조하여 호출해야 하는데, 참조할 인스턴스가 없으므로 오류 발생함
//		System.out.println("   instanceField: " + instanceField);
		System.out.println("   staticField: " + staticField);
	}

	// 1. 기본생성자 정의 - "constructor block" 출력
	public Initialization() {
		System.out.println("4. constructor block");
	}

	public static void main(String[] args) {
		System.out.println("2. main() start"); // 2번째로 실행됨
		// 2. 현재 클래스의 생성자 호출
		new Initialization(); // 4번째로 실행됨
		System.out.println("5. main() end"); // 5번째로 실행됨

	}

}
