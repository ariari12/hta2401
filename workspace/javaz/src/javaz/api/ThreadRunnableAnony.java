package javaz.api;

public class ThreadRunnableAnony {

	public static void main(String[] args) {
		// 1. 참조변수가 있는 익명의 객체로 Runnable 인터페이스를 구현하여
		//    7 ~ 10까지 스레드의 이름(myRun)과 함께 출력하도록 처리하고
		//    스레드로 시작 시키기
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 7; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName() + " " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r, "myRun");
		t.start();

		// 2. 참조변수가 없는 익명의 객체로 Thread 클래스를 상속받아
		//    11 ~ 16까지 스레드의 이름(myT)과 함께 출력
		//    스레드로 시작 시키기
		new Thread("myT") {
			@Override
			public void run() {
				for (int i = 11; i <= 16; i++) {
					System.out.println(getName() + " " + i);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
