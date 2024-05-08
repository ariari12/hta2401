package javaz.util;

import java.util.ArrayList;
import java.util.List;

class Pizza {
	private String name;	// Shrimp, Potato, Chicago
	private String size;	// small, medium, large
	private int price;		// 10_000, 20_000, 30_000
	
	// 1. 멤버 변수들을 매개변수로 받아서 초기화하는 생성자
	public Pizza(String name, String size, int price) {
		this.name = name;
		this.size = size;
		this.price = price;
	}
	
	// 2. setter / getter
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	
}

public class ListPizza {
	public static void main(String[] args) {
		// 3. Pizza 객체들을 저장하는 List 타입의 인스턴스 pizzas 생성
		// 4. 임의의 Pizza 객체 3개를 생성하여 pizzas에 저장
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("Shrimp", "small", 10_000));
		pizzas.add(new Pizza("Potato", "medium", 20_000));
		pizzas.add(new Pizza("Chicago", "large", 30_000));
		
		//////////////////////////////////////////////////////
		
		System.out.println("------ Pizza order list ------");
		System.out.println("no.\tname\tsize\tprice");
		
		// 5. pizzas에 저장된 값을 화면에 출력
		int i = 1;
		int sum = 0;
		for (Pizza pizza : pizzas) {
			System.out.println(i++ + "\t" + pizza.getName() + "\t"
					+ pizza.getSize() + "\t" + pizza.getPrice());
			sum += pizza.getPrice();
		}
		
		System.out.println("------------------------------");
		System.out.println("전체 주문 수량: " + pizzas.size());
		System.out.printf("총 주문 금액: %,d", sum );
	}

}
