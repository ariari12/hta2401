package javaz.oop;

// 시나리오
// 동물은 이름과 사는 곳을 가지고 있다.
// 동물은 움직일 수 있다.
// 동물에서 임의의 속성 하나 추가, 동물의 기능 하나 추가

abstract class Animal{	// 추상 클래스 선언
	String name;
	String place;
	String breath;
	String food;
	int leg;


	public abstract void move(); // 추상 메서드 선언 
	
	public void eat() {
		System.out.println(name + "이(가) " + food + "을(를) 먹습니다.");
	}
	
	public void getBreathe() {
		System.out.println(name + "이(가) " + breath + "(으)로 호흡을 합니다.");
	}
	
	public void getLegs() {
		System.out.println(name + "은(는) " + leg + "개의 다리를 가지고 있습니다.");
	}
}

//사람은 말할 수 있다.
//돌고래는 초음파 소리를 낼 수 있다.
//사람, 돌고래 각 클래스에서 모든 속성의 값을 매개변수로 받아서 초기화하는 생성자

class Human extends Animal {
	boolean speak = true;
	
	public Human(String name, String area, String breath, String food, int leg) {
		this.name = name;
		this.place = area;
		this.food = food;
		this.breath = breath;
		this.leg = leg;
	}
	
	@Override
	public void move() {
		System.out.println(name + "이(가) " + place + "(을)를 걸어다닙니다.");
	}
	
	public void speak() {
		System.out.println(name + "이(가) 말했습니다.");
	}
}

class Dolphin extends Animal {
	boolean wave = true;
	
	public Dolphin(String name, String area, String breath, String food, int leg) {
		this.name = name;
		this.place = area;
		this.food = food;
		this.breath = breath;
		this.leg = leg;
	}
	
	@Override
	public void move() {
		System.out.println(name + "이(가) " + place + "을(를) 헤엄칩니다.");
	}
	
	public void ultraWave() {
		System.out.println(name + "이(가) 초음파 소리를 내었습니다.");
	}
}

public class AbstractMain {
	public static void main(String[] args) {
		// 사람 객체 생성
		Human human = new Human("나그네", "육지", "코", "닭꼬치", 2);
		// 돌고래 객체 생성
		Dolphin dolphin = new Dolphin("벨루가", "바다", "숨구멍", "물고기", 0);
		
		// 사람 움직이기
		human.move();
		// 사람 먹이기
		human.eat();
		// 사람 말하게 하기
		human.speak();
		
		System.out.println();
		
		// 돌고래 움직이기
		dolphin.move();
		// 돌고래 먹이기
		dolphin.eat();
		// 돌고래 초음파 소리 내기
		dolphin.ultraWave();
		

	}

}
