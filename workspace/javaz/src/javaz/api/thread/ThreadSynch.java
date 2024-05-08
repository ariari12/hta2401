package javaz.api.thread;

class MoneyBox {
	int money;
	
	public MoneyBox(int money) {
		this.money = money;
	}
	
	public void withdraw() {
		this.money -= 1;
	}
}

class MoneyMoa extends Thread {
	private MoneyBox moneyBox;
	
	public MoneyMoa(String name, MoneyBox moneyBox) {
		super(name);
		this.moneyBox = moneyBox;
	}

	// 각각의 스레드가 1원씩 5번 저금하는 작업 스레드
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
//			synchronized (moneyBox) {
//				moneyBox.money++;
//				System.out.println(getName() + " : " + moneyBox.money);
//			}
			synchronized (moneyBox) {
				System.out.print(getName() + " 출금 -1 : ");
				// 출금 후 잔고 출력
				moneyBox.withdraw();
				System.out.println(moneyBox.money);
			}
		}
		
	}
	
	
}

public class ThreadSynch {

	public static void main(String[] args) {
		// MoneyBox 객체 mBox 생성 - 초기값은 0으로 지정
		MoneyBox mBox = new MoneyBox(0);
				 mBox = new MoneyBox(10);
		
		// MoneyMoa 인스턴스 ann 생성 - 이름은 Ann으로 지정
		MoneyMoa ann = new MoneyMoa("Ann", mBox);
		
		// MoneyMoa 참조변수 ben 생성 - 이름은 Ben으로 지정
		MoneyMoa ben = new MoneyMoa("Ben", mBox);
		
		// ann과 ben 스레드 실행 시키기
		ann.start();
		ben.start();

	}

}
