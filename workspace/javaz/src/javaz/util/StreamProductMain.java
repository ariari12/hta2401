package javaz.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Product {
	private int pid;
	private String pname;
	private int price;
	
	// 1. 모든 필드를 매개변수로 받아서 초기화하는 생성자
	public Product(int pid, String pname, int price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}
	
	// 2. getters / setters
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}

public class StreamProductMain {
	public static void main(String[] args) {
		// 3. Product를 담는 List 객체 productList 생성
		// 3.1 productList에 다음 값을 갖는 객체 저장
		//     pid는 1111, 이름은 Robot,     가격은 1000;
		//      "   2222,   "  SpaceShip,   "  2000;
		//		"	3333,	"  Diamond,		"  9999;
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product(1111, "Robot", 1000));
		productList.add(new Product(2222, "SpaceShip", 2000));
		productList.add(new Product(3333, "Diamond", 10000));
		
		// 4. productList에서 가격이 2000 이상인 제품들만 새로운
		//    List 객체 newList에 저장
		// 4.1 newList의 이름과 가격 출력
		List<Product> newList = productList.stream()
										   .filter(p -> p.getPrice() >= 2000)
										   .collect(Collectors.toList());
		newList.forEach(p -> System.out.println("이름: " + p.getPname() 
												+ ", 가격: " + p.getPrice()));
		
		// 5. 전체 제품의 가격을 total에 저장
//		int total = productList.stream().map(p -> p.getPrice()).reduce(0, Integer :: sum);
		int total = productList.stream().mapToInt(p -> p.getPrice()).sum();
		
		// 6. 전체 제품 개수를 cnt에 저장
//		int cnt = productList.size();
//		int cntt = (int) productList.stream().mapToInt(Product :: getPrice).count();
		long cnt = productList.stream().mapToInt(Product :: getPrice).count();
		
		// 7. 전체 제품의 평균 가격을 avg에 저장
//		double avg = (double) total / cnt;
//		OptionalDouble avg = productList.stream().mapToDouble(p -> p.getPrice()).average();
		double avg = productList.stream().mapToInt(p -> p.getPrice()).average().getAsDouble();
		
		// 8. 5, 6, 7의 값 출력
		System.out.println();
		System.out.printf("전체 제품 가격: %,d\n", total);
		System.out.println("전체 제품 개수: " + cnt);
		System.out.printf("전체 제품 평균 가격: %.2f\n", avg);
	}

}
