package javaz.api.thread;

// 1. Thread 클래스를 상속받는 ThreadStop 클래스 정의
//    1.1 외부에서 접근할 수 없도록 true/false 값을 속성으로 저장하는 stop 필드 선언
//    1.2 stop 필드가 true가 아닌 동안
//        "thread is running...."을 출력하는 스레드 메서드
//    1.3 1.1의 setter 메서드
class ThreadStop extends Thread {
	private boolean stop;

	@Override
	public void run() {
//		try {
//			while(!stop) {
//				System.out.println("thread is runing....");
//				Thread.sleep(1000);
//			}
//		} catch (InterruptedException e) {
//				e.printStackTrace();
//		}
		
		while(true) {
			System.out.println("thread is runing....");
			if (Thread.interrupted()) {
				System.out.println("thread interrupted!! - end");
				break;
			}
		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}

public class ThreadControl {

	public static void main(String[] args) {
		// 2. 1의 인스턴스 ts를 생성
		ThreadStop ts = new ThreadStop();

		// 2.1 ts를 스레드로 시작시키기
		ts.start();
//		ts.stop(); // X

		// 2.2 3초후 스레드를 정지 시키기
		try {
			Thread.sleep(100);
			ts.interrupt();
//			ts.setStop(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("thread stopped!!");

	}

}
