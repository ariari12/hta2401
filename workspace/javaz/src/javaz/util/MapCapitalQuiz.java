package javaz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 1. Capital 클래스
//   1.1 대한민국, 가나, 나이지리아, 아르헨티나의 수도를
//       생성자에서 Map 객체 map에 저장하고
//   1.2 특정 나라의 이름을 매개변수로 받아서
//       해당 수도가 map에 존재하면 출력하고
//       그렇지 않으면 '등록되지 않은 나라입니다.'를 출력하는 getCapital() 작성
class Capital {
	private Map<String, String> map;
	// 클래스에서 생성할 경우 클래스를 호출해야만 초기화가 됨
	
	public Capital() {
		// 생성자에서 생성하면 생성자가 만들어질 때마다 map이 초기화 됨
		map = new HashMap<String, String>();
		map.put("대한민국", "서울");
		map.put("가나", "아크라");
		map.put("나이지리아", "아부자");
		map.put("아르헨티나", "부에노스아리에스");
		
	}
	
	public void getCapital(String country) {
		if (map.containsKey(country)) {
			System.out.println(country + "의 수도: " + map.get(country));
		} else {
			System.out.println("입력하신 나라가 존재하지 않습니다.");
		}
	}
}

public class MapCapitalQuiz {

	public static void main(String[] args) {
		System.out.println("--- 각 나라의 수도 알아보기 ---");
		// 2. Scanner 객체를 이용하여 나라 이름 하나를 입력받고
		//    수도의 이름 또는 등록되지 않은 나라입니다.를 출력
		//    만일, q가 입력되면 '프로그램이 종료되었습니다.'를
		//    출력하고 퀴즈 종료
		//	  그렇지 않으면 퀴즈 계속하기
		
		Scanner sc = new Scanner(System.in);
		Capital c = new Capital();
		while (true) {
			System.out.print("나라 이름(종료는 q): ");
			String country = sc.nextLine();
			
			if (country.equalsIgnoreCase("q")) {
				break;
			}
			
			c.getCapital(country);
		}
		
		System.out.println("--- 프로그램이 종료되었습니다. ---");
		sc.close();

	}

}
