package javaz.oop;

public class ArrayPizza {
	// 1. Pizza 객체를 배열로 넘겨 받아서 화면에 표시하는 orderList 메서드
	//    ----- Pizza order list -----
	//    no.   pizza name	size
	//	  1 	새우피자		미디엄
	//	  2 	감자피자		라지
	//	  3		시카고피자		미디엄
	public void orderList(Pizza[] pizzas) {
		int max = 0;
		int maxIdx = 0;
		System.out.println("----- Pizza order list -----");
		System.out.println("no.\tpizza name\tsize");
		for (int i = 0; i < pizzas.length; i++) {
			if (max < pizzas[i].getRadius()) {
				max = pizzas[i].getRadius();
				maxIdx = i;
			}
			System.out.println((i + 1) + "\t" + pizzas[i].getName() 
					+ "    \t" + (pizzas[i].getRadius() == 15 ? "미디엄" : "라지"));
		}
		System.out.println("----------------------------");
		System.out.println("전체 주문 수량: " + pizzas.length);
		System.out.println("가장 큰 피자: " + pizzas[maxIdx].getName() + ", " + max);
	}

	public static void main(String[] args) {
		// 2. 위 1번에서 필요한 객체를 생성하여 1번 메서드를 호출
		Pizza[] pizzas = { 
			new Pizza(15, "하와이안피자"), 
			new Pizza(20, "고구마피자"), 
			new Pizza(15, "시카고피자")
		};
		
		//배열 생성 방법-2
//		Pizza p1 = new Pizza(15, "하와이안피자");
//		Pizza p2 = new Pizza(20, "고구마피자");
//		Pizza p3 = new Pizza(15, "시카고피자");
//		Pizza[] pizzaArr = { p1, p2, new Pizza(15, "시카고피자") };
		
		ArrayPizza ap = new ArrayPizza();
		ap.orderList(pizzas);

	}

}
