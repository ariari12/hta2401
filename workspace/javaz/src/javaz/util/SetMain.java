package javaz.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Set<E> 인터페이스
// - 데이터 중복 불가
// - 저장된 순서 유지 X
// - 특별한 기준에 따른 정렬 X
public class SetMain {

	public static void main(String[] args) {
		// 1. Set 객체 set 생성
//		Set set = new Set(); 	 // 인터페이스는 객체 생성 X
//		Set set = new HashSet(); // 부모 클래스 정의 시 자식 클래스의 객체로 받을 수 있음 O
//		Set<String> set = new HashSet(); // O
//		HashSet<String> hs = new HashSet<String>(); // O
		
//		Set<int> set = new HashSet<int>();	     // 기본형은 담을 수 없음 X
		Set<Object> set = new HashSet<Object>();
		System.out.println(set.size());
		set.add(1);
		set.add('2');
		set.add("3");
		set.add(true);
		set.add(4.0);
		set.add(true);
		
		System.out.println(set);
		System.out.println(set.size());
		
		// set에 1이 들어 있다면 지우기
		if (set.contains(1)) {
			set.remove(1);
		}
		System.out.println(set);
		System.out.println(set.size());
		System.out.println();
		
		System.out.println("Iterator 객체 사용 with while");
		Iterator<Object> iterator = set.iterator();
		while (iterator.hasNext()) { // 다음 요소가 있으면
			Object obj = iterator.next(); // 다음 요소 가져오기
			System.out.println(obj);
		}
		System.out.println();
		
		System.out.println("Iterator with for");
		for (Object obj : set) {
			System.out.println(obj);
		}
		System.out.println();
		
		System.out.println("Iterator with for ii");
		for (iterator = set.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			System.out.println(obj);
		}
		System.out.println();
		
		set.clear();
		System.out.println(set);
		System.out.println(set.size());
	}

}
