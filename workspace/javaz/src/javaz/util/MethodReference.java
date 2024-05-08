package javaz.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntFunction;

// 메서드 참조
// - 기존 메서드를 참조하여 간단한 람다식 작성
// - 클래스 또는 객체이름 :: 메서드 이름
//  ex)  System.out :: println

// - 메서드를 참조하여 매개변수의 정보 및 리턴 타입 확인
//   - 람다식에서 불필요한 매개변수를 제거
//   - 메서드 참조도 인터페이스의 익명 구현 객체로 생성

// 1. Consumer 인터페이스를 구현하는 ConsumerImpl 클래스 정의
//    매개변수로 받은 문자열을 화면에 출력
class ConsumerImpl implements Consumer<String> {
	@Override
	public void accept(String str) {
		System.out.println(str);
	}
}

public class MethodReference {
	public static void main(String[] args) {
		// 1.1 Consumer 타입의 객체 cs 생성
		Consumer<String> cs = new ConsumerImpl();
		cs.accept("1. named class");
		
		// 2. 익명의 내부 클래스를 이용하여 Consumer 인터페이스 객체
		cs = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};
		cs.accept("2. anony class");
		
		// 3. 람다식
		cs = str -> System.out.println(str);
		cs.accept("3. lambda");
		
		// 4. 메서드 참조
		cs = System.out :: println;
		cs.accept("4. method reference");
		System.out.println();
		
		// 정적 메서드 참조
		ToIntFunction<String> toInt = s -> Integer.parseInt(s);
		ToIntFunction<String> toIntM = Integer :: parseInt;
		
		int i = toInt.applyAsInt("123");
		int iM = toIntM.applyAsInt("456");
		System.out.println();
		
		//////////////////////////
		Cal c = new Cal();
		IntBinaryOperator intB = (x, y) -> c.add(x, y);
		System.out.println(intB.applyAsInt(1, 2));
		
		intB = c :: add;  // 인스턴스 메서드 참조
		System.out.println(intB.applyAsInt(3, 4));
		
		// 생성자 참조
		InterfaceCal ic = () -> new Cal();
		InterfaceCal icc = Cal :: new;
		ic.get();
		icc.get();
		
		System.out.println();
		//////////////////////////////////
		// 1. List 타입의 변수 list에 bee, air, sky, Sea, Ace를 저장
		List<String> list = Arrays.asList("bee", "air", "sky", "Sea", "Ace");
		
		System.out.println("원본   : " + list);
		
		// 2. 기본 정렬 - 대소문자 구분 O
		Collections.sort(list);
		System.out.println("정렬i  : " + list);
		
		// 3. 대소문자 구분 X 정렬
		// 익명의 객체로 Comparator 인터페이스의 compare() 재정의
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		System.out.println("정렬ii : " + list);
		
		// 람다식으로 구현
		list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
		System.out.println("정렬iii: " + list);
		
		// 메서드 참조 구현
		list.sort(String :: compareToIgnoreCase);
		System.out.println("정렬iv : " + list);
		
	}
}

interface InterfaceCal {
	public Cal get();
}

class Cal {
	public Cal() {
		System.out.println("Cal's default constructor");
	}
	
	public Cal(String msg) {
		System.out.println("Cal's msg: " + msg);
	}
	
	public int add(int x, int y) { return x + y; }
	
	public static int minus(int x, int y) { return x - y; }
}
