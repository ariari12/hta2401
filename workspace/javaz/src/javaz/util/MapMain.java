package javaz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

// Map<K, V> 인터페이스
// - Key와 Value를 매핑
//   - 검색적 개념을 담고 있는 인터페이스
// - Key로 각 Value를 참조하는 구조

// - Key
//   - 중복 불가
//   - 배열의 인덱스와 같은 역할
//   - 정렬의 기준 없음

// - Value
//   - 중복 가능
//   - 배열의 요소와 같은 역할
//   - Map에 저장하는 데이터

// HashMap 클래스
// - non-thread safe
// - 동기화 처리가 필요한 경우
//   Collentions.synchronized( ~~ ) 이용

public class MapMain {
	public static void main(String[] args) {
//		Map<Integer, String> map = new Map<Integer, String>(); 		// X
		Map<Integer, String> map = new HashMap<Integer, String>();  // O
		
		map.put(31, "경기");
		map.put(33, "강원");
		map.put(42, "대전");
		map.put(64, "제주");
		map.put(64, "제주주");
		System.out.println(map);
		System.out.println(map.get(33));
		System.out.println();
		
		System.out.println("keySet() ----------------");
		// 호출되는 값을 저장하여
		// map의 값을 "키 : 값"의 형태로 출력
		Set<Integer> nums = map.keySet();
		for (int num : nums) {
			System.out.println(num + " : " + map.get(num));
		}
		
		System.out.println();
		System.out.println("forEach() with BiConsumer 인터페이스 - 람다식");
		map.forEach((k, v) -> System.out.println(k + " : " + v));
		System.out.println();
		
		System.out.println("forEach() Anony class");
		BiConsumer<Integer, String> bc = new BiConsumer<Integer, String>() {
			@Override
			public void accept(Integer t, String u) {
				System.out.println(t + " : " + u);
			}
		};
		
		map.forEach(bc);
		
		System.out.println();
		System.out.println(map.size());
		
		map.remove(42);	System.out.println(map.size());
		map.clear();	System.out.println(map.size());
	}

}
