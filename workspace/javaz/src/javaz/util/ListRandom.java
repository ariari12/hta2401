package javaz.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRandom {

	public static void main(String[] args) {
		// 1. 문자열을 저장하는 countryList를 List 타입으로 생성
		List<String> countryList = new ArrayList<String>(); 
		
		// 2. 1에서 생성한 객체에 Gana, Canada, Vietnam, Sweden을 저장
		//    2.1 저장된 값을 화면에 출력
//		countryList.add("Gana");
//		countryList.add("Canada");
//		countryList.add("Vietnam");
//		countryList.add("Sweden");
		countryList = Arrays.asList("Gana", "Canada", "Vietnam", "Sweden");
		
		System.out.println("--- 여행지 목록 ---");
		int i = 1;
		for (String country : countryList) {
			System.out.println(i++ + "." + country);
		}
		System.out.println("----------------");
		
		// 3. 실행 시 마다 랜덤하게 한 나라를 출력
		int nansu = (int) (Math.random() * countryList.size());
		System.out.println("오늘의 추천 여행지 : " + countryList.get(nansu));
	}

}
