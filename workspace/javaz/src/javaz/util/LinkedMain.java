package javaz.util;

import java.util.LinkedList;

// Queue<E> 인터페이스
// - 데이터 처리 전에 잠시 저장하는 자료구조
// - First In First Out
// - taile에서 추가, head에서 삭제

// LinkedList
// Queue와 List 인터페이스 구현 클래스
// - ArrayList와 거의 유사
//   - 순차적으로 추가/삭제 하는 경우에 사용(ArrayList)
// - 중간 데이터의 추가/삭제의 경우 사용(LinkedList)
//   - 데이터의 수정/삭제가 빈번한 경우 사용

public class LinkedMain {
	public static void main(String[] args) {
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("ant");	System.out.println(ll);
		ll.add("bee");	System.out.println(ll);
		System.out.println();
		
		ll.add(1, "CAT");	System.out.println(ll);
		ll.addFirst("DOG");	System.out.println(ll);
		System.out.println();
		
		ll.offer("fly");	System.out.println(ll);
		ll.offerFirst("GYM");	System.out.println(ll);
		ll.push("zoo");		System.out.println(ll);
		System.out.println("---------------------");
		
		System.out.println(ll.get(0) + " " + ll);
		System.out.println(ll.getFirst() + " " + ll);
		System.out.println(ll.peek() + " " + ll);
		System.out.println(ll.pop() + " " + ll);
		System.out.println(ll.poll() + " " + ll);
	}

}
