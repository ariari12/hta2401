package javaz.api;

// 1. Thread 클래스 상속 받기
class ThreadExtends extends Thread {
	public ThreadExtends() { }

	public ThreadExtends(String name) {
		super(name);
	}

	// 2. 부모 메서드를 오버라이딩하여
	//   2.1 1초마다 스레드의 이름과 정수 1 ~ 3 출력을 스레드로 처리
	@Override
	public void run() {
		for (int i = 1; i <= 3; i++) {
			Thread.yield();
			System.out.println(getName() + " " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// a. Runnable 인터페이스 구현
class RunnableImplements implements Runnable {
	// b. 스레드로 처리할 내용 - 부모 메서드 재정의
	//    1초마다 스레드 이름과 정수 4 ~ 6 출력
	@Override
	public void run() {
		for (int i = 4; i <= 6; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class ThreadMain {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("---- main() begin ----");
		Thread t = Thread.currentThread();
		
		// c. Runnable 인터페이스 구현한 클래스의 참조변수 t4 생성
		RunnableImplements t4 = new RunnableImplements();
		// d. t4의 이름을 "티사"로 지정
		Thread t44 = new Thread(t4);
		t44.setName("티사");
		
		Thread t5 = new Thread(new RunnableImplements(), "티오");
		
		// 스레드 우선순위 확인
		System.out.println("티사: " + t44.getPriority());
		System.out.println("티오: " + t5.getPriority());
		t44.setPriority(Thread.MAX_PRIORITY); // 우선 순위 최대 설정
		t5.setPriority(1); // 우선 순위 최저 설정
		System.out.println("----------------");
		System.out.println("티사: " + t44.getPriority());
		System.out.println("티오: " + t5.getPriority());
		
		// e. t4를 스레드로 시작 시키기
//		t4.setName("티사");	// X
//		t4.start(); 	    // X 
//		t44.start();
//		
//		t5.start();
		
		// 2.2 Thread 클래스를 상속받은 클래스의 인스턴스 t2 생성
		ThreadExtends t2 = new ThreadExtends();
		t2.setName("티투"); // 스레드 이름 설정
		
		// 2.3 스레드 작업 시작 시키기
//		t2.run()    // X - main보다 먼저 실행됨
		t2.start(); // O 스레드 시작 시키기 - main보다 나중에 실행됨
		
		Thread t3 = new ThreadExtends("티삼"); 	// 스레드 이름을 매개변수로 받아서
		t3.start();							  	// 부모 생성자에게 전달하도록 처리
		
		t2.join(); // 이 스레드들이 종료될 때까지
		t3.join(); // 현재 스레드(main) 대기
		t44.join();
		t5.join();
		
		System.out.println("실행 중인 스레드 이름: " + t.getName());
		System.out.println("---- main() end ----");

	}

}
