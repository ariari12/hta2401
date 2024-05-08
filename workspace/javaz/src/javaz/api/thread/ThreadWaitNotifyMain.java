package javaz.api.thread;

class Bakery {
	private int cake;
	private boolean empty = true;
	
	public synchronized int get() {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		empty = true;
		notifyAll();
		return cake;
	}
	
	public synchronized void put(int cake) {
		while(!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		empty = false;
		notifyAll();
		this.cake = cake;
	}
	
}

class Patissier implements Runnable {
	private Bakery bakery;
	
	public Patissier(Bakery bakery) {
		this.bakery = bakery;
	}

	@Override
	public void run() {
		// 케익을 10개 집어넣기
		// 1개를 넣은 후 랜덤하게 1초 미만을 쉬고 작동하도록 처리
		for (int i = 1; i <= 10; i++) {
			bakery.put(i);
			System.out.println("[Patissier] 만든 케익 수: " + i);
			try {
				Thread.sleep((long) (Math.random() * 999));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

class Customer implements Runnable {
	private Bakery bakery;
	
	public Customer(Bakery bakery) {
		this.bakery = bakery;
	}
	
	@Override
	public void run() {
		// 케익을 10개 가져가기
		// 1개를 넣은 후 랜덤하게 1초 미만을 쉬고 작동하도록 처리
		for (int i = 1; i <= 10; i++) {
			int cake = bakery.get();
			System.out.println("[Customer] 구매 케익 수: " + cake);
			try {
				Thread.sleep((long) (Math.random() * 999));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadWaitNotifyMain {

	public static void main(String[] args) {
		Bakery bakery = new Bakery();
		Patissier p = new Patissier(bakery);
		
		
//		p.start(); // X
		
		new Thread(p).start(); // Patissier 스레드 시작 시키기
		new Thread(new Customer(bakery)).start(); // Customer 스레드 시작 시키기

	}

}
