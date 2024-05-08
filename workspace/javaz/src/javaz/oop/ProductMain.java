package javaz.oop;

// 1. 제품명(name), 가격(price), 보너스점수(bonus)를 필드로 갖는 Product 클래스 정의
class Product {
	String name;
	int price;
	int bonus;
}

// 2. Product 클래스를 상속받는 TV 클래스 정의
class TV extends Product {  }

// 3. Product 클래스를 상속받는 Computer 클래스 정의
class Computer extends Product {  }

// 4. Product 클래스를 상소갇는 Audio 클래스 정의
class Audio extends Product {  }

// 5. 보유금액(money), 보너스점수(bonus)를 속성으로 갖는 Buyer 클래스 정의
class Buyer {
	int money;
	int bonus;
	// 5.1 TV 객체를 매개변수로 받아서
	//     "TV를 구매합니다."를 출력하는 buy 메서드 작성
	public void buy(TV tv) {
		System.out.println("TV를 구매합니다.");
	}
	
	// 5.2 Computer 객체를 매개변수로 받아서
	//     "Computer를 구매합니다."를 출력하는 buy 메서드 오버로딩
	public void buy(Computer computer) {
		System.out.println("Computer를 구매합니다.");
	}
	
	// 5.3 Audio 객체를 매개변수로 받아서
	//     "Audio를 구매합니다."를 출력하는 buy 메서드 오버로딩
	public void buy(Audio audio) {
		System.out.println("Audio를 구매합니다.");
	}
}

class NewBuyer {
	int money;
	int bonus;
	
	// TV, Computer, Audio를 구매할 수 있는 buy 메서드 작성
	// "전자제품을 구매합니다." 출력
	public void buy(Product p) {
		System.out.println(p.getClass().getSimpleName() + "를 구매합니다.");
	}
}

public class ProductMain {
	public static void main(String[] args) {
		// 6. Buyer 클래스의 인스턴스 b 생성
		Buyer b = new Buyer();
		
		// 7. TV 클래스의 참조변수 tv 생성
		TV tv = new TV();
		
		// 8. Audio 클래스의 객체 audio 생성
		Audio audio = new Audio();
		
		// 9. Computer 클래스 타입의 객체 com 생성
		Computer com = new Computer();
		
		// 10. 7, 8, 9번의 객체를 이용하여 Buyer 클래스의 buy 메서드 호출
		b.buy(tv);
		b.buy(audio);
		b.buy(com);
		
		System.out.println("------------------");
		
		// 11. NewBuyer 클래스의 객체 nb를 생성
		NewBuyer nb = new NewBuyer();
		
		// 12. 7, 8, 9번의 객체를 이용하여 NewBuyer 클래스의 buy 메서드 호출
		//     (필요 시 적당한 객체를 생성하여 처리)
		Product p = new Product();
		
		p = tv;
		nb.buy(p);
		System.out.println("=========");
		p = com;
		nb.buy(p);
		System.out.println("=========");
		p = audio;
		nb.buy(p);
		System.out.println("=========");

	}

}
