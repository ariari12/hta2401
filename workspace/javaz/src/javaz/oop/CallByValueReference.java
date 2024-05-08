package javaz.oop;

class Pizza {
	private int radius;
	private String name;
	
	// 1. 모든 필드의 값들을 매개변수로 넘겨받아서 초기화하는 생성자
	Pizza(int radius, String name) {
		this.radius = radius;
		this.name = name;
	}
	
	// 2. Pizza 객체 두 개를 매개변수로 넘겨받아 radius가 더 큰 객체를 반환하는 [공유] 메서드 getLargePizza
	public static Pizza getLargePizza(Pizza p1, Pizza p2) {
		return p1.radius > p2.radius ? p1 : p2; 
	}
	
	// 3. Pizza 객체와 정수형 크기를 매개변수로 넘겨받아 
	// Pizza 객체의 radius가 20미만이면 전달받은 매개변수의 값으로 변경하는 makeLargePizza [클래스] 메서드
	public static void makeLargePizza(Pizza pizza, int large) {
		// pizza.radius = pizza.radius < 20 ? large : pizza.radius;
		if (pizza.radius < 20) {
			pizza.radius = large;
		}
		large = 30;
	}
	
	// 4. radius의 값을 매개변수로 받아 변경하는 setter setRadius()
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	// 5. radius의 값을 반환하는 getter
	public int getRadius() {
		return radius;
	}
	
	public String getName() {
		return name;
	}
	
} // END class Pizza

public class CallByValueReference {
	public static void main(String[] args) {
		int medium = 15;
		int large = 20;
		
		// 1. Pizza 클래스의 인스턴스 shrimpPizza 생성
		//    크기는 미디엄, 이름은 새우피자
		Pizza shrimpPizza = new Pizza(medium, "새우피자");
		
		// 2. Pizza 클래스의 인스턴스 potatoPizza 생성
		//    크기는 라지, 이름은 감자피자
		Pizza potatoPizza = new Pizza(large, "감자피자");
		
		// 3. Pizza 클래스 타입의 참조변수 largePizza를 선언하여
		//    shirimpPizza와 potatoPizza 중 큰 피자를 저장
		Pizza largePizza = Pizza.getLargePizza(shrimpPizza, potatoPizza);
		
		// 4. getter를 이용하여 각 객체의 필드값 출력
		System.out.println(shrimpPizza.getName() + "의 크기: " + shrimpPizza.getRadius());
		System.out.println(potatoPizza.getName() + "의 크기: " + potatoPizza.getRadius());
		System.out.println("둘 중 더 큰 피자는 [" + largePizza.getName() + "]입니다.");
		
		// 5. shrimpPizza와 potatoPizza를 매개변수로 받아 
		// 크기 radius가 20미만인 피자는 20으로 변경하는 메서드 호출
		Pizza.makeLargePizza(shrimpPizza, large);
		Pizza.makeLargePizza(potatoPizza, large);
		System.out.println();
		System.out.println(shrimpPizza.getName() + "의 크기: " + shrimpPizza.getRadius());
		System.out.println(potatoPizza.getName() + "의 크기: " + potatoPizza.getRadius());
		System.out.println("medium size: " + medium);
		System.out.println("large size: " + large);
		
		// 기본자료형: 다른 메서드에서 값을 바꾸더라도 메인에서의 값은 변경되지 않음(값을 통해 작업하므로)
		// 참조자료형: 다른 메서드에서 값을 바꾸면 메인에서도 값이 바뀐다(주소를 통해 작업하므로)

	}

}
