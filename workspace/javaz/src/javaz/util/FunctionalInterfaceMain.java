package javaz.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// java.util.functional
// - 람다식을 사용할 때마다 함수형 인터페이스를
//   작성하지 않도록 하기 위해 제공되는 패키지
// - Consumer, Supplier, Function, Operator, Predicate 등이
//   많이 사용되는 함수형 인터페이스들
public class FunctionalInterfaceMain {
	public static void main(String[] args) {
		// 반환값 X, 매개변수 1
		Consumer<Integer> consumer = (i) -> {
			System.out.println(i + " * 2 = " + i * 2);
		};
		consumer.accept(3);
		
		System.out.println();
		// 반환값 O, 매개변수 2
//		BiFunction<Integer, Integer, Double> bf 
//		= (x, y) -> Math.pow(x, y);
		BiFunction<Integer, Integer, Integer> bf 
		= (x, y) -> x + y;
		System.out.println(bf.apply(10, 2));
		
		System.out.println();
		// 반환값 O, 매개변수 X
		Supplier<Boolean> sup = () -> new Random().nextBoolean();
		System.out.println(sup.get());
		System.out.println();
		
		// 반환값 O, 매개변수 1
		Predicate<Integer> prep = (x) -> x > 100;
		System.out.println(prep.test(1000));
		System.out.println();
		
		// java.util.function의 Function를 이용하여
		// 1. 원의 반지름을 매개변수로 전달하면
		//    넓이를 반환하도록 람다식을 정의
		Function<Integer, Double> fn1 = (r) -> r * r * Math.PI;
		System.out.println(fn1.apply(3));
		System.out.println();
		
		// 2. 문자열을 매개변수로 전달하면 길이를 반환하도록
		//    람다식을 정의해서 fn2에 저장
		Function<String, Integer> fn2 = s -> s.length();
		System.out.println(fn2.apply("Hello"));
		System.out.println();
		
		// ace, bus, sky를 List 타입의 객체 list에 저장한 후
		// Consumer 인터페이스를 람다식으로 구현하여
		// 한 줄씩 출력
		List<String> list = Arrays.asList("ace", "bus", "sky");
		list.forEach(s -> System.out.println(s));
		System.out.println();
		
		// ant, bee, top을 각각 a, b, t를 키로
		// Map 타입의 객체 map에 저장한 후
		// BiConsumer 인터페이스를 람다식으로 구현하여
		// "키 : 값"의 형태로 출력
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "ant");
		map.put("b", "bee");
		map.put("t", "top");
		map.forEach((s1, s2) -> System.out.println(s1 + " : " + s2));
		
	}

}
