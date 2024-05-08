package javaz.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// java.util.stream 패키지
// - I/O 스트림이 아닌 컬렉션에서 시작되는 스트링 api
// - 다양헌 데이터 소스(배열 및 컬렉션 - Last, Set, Map 등)를
//   표준화된 방법으로 처리
// - 컬렉션의 저장 요소를 하나씩 참조하여 람다식으로 처리 가능
// - 한 메서드의 출력 스트림이 다른 메서드의 입력 스트림이 될 수 있음

// 스트림 연산: 생성 > 중간 처리 > 최종 처리

// 스트림 생성
// - 배열 또는 컬렉션으로 생성

// 스트림 중간처리 연산
// - 입력 데이터를 출력 데이터로 가공
// - map(), filter(), sorted(), mapToInt(), ...

// 스트림 최종 연산
// - 처리된 데이터를 모아서 결과 작성
// - sum(), average(), count(), reduce(), collect(), 
//   forEach(), ...

public class StreamMain {
	public static void main(String[] args) {
		int[] arr0to4 = { 0, 1, 2, 3, 4 };
		System.out.println(Arrays.toString(arr0to4));
		System.out.println("------------------------");
		
		// 기본형 스트림
		IntStream intStrm = Arrays.stream(arr0to4);
				  intStrm = IntStream.range(0, 5);
				  intStrm = IntStream.rangeClosed(0, 4);
				  intStrm = IntStream.of(0, 1, 2, 3, 4);
				  intStrm = IntStream.of(arr0to4);

		intStrm.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("------------------------");
		
//		intStrm = new Random().ints();  		// 무한반복
//		intStrm = new Random().ints(5); 		// 5개 반환
		intStrm = new Random().ints(5, 0, 5);   // 0~4 사이의 값을 5개 반환
		intStrm.forEach(System.out :: println);
		System.out.println("------------------------");
		
		// 실수형 범위 난수 5개를 생성하여 Doublestream 타입의 변수 dblstrm에 저장
		DoubleStream dblStrm = new Random().doubles(5);
		dblStrm.forEach(System.out :: println);
		System.out.println("------------------------");
		
		// 객체형 스트림
		Integer[] intg5to9 = { 5, 6, 7, 8, 9 };
		List<Integer> intgList = Arrays.asList(5, 6, 7, 8, 9);
		
		String[] arr = { "pop", "oak", "eye", "key" };
		
//		Stream<Integer> strmIntg = Arrays.stream(arr0to4); 	// 기본자료형 X 
															// IntStream에 받아야 함
		Stream<Integer> strmIntg = Arrays.stream(intg5to9); // 참조자료형 O
						strmIntg = Stream.of(intg5to9);
						strmIntg = intgList.stream();
						
		Stream<String> s1 = Arrays.stream(arr);
						
		strmIntg.forEach(i -> System.out.print(i + " "));
		System.out.println();
		System.out.println("------------------------");
		
		List<String> list = Arrays.asList("sky", "seA", "Art", "sun", "Sue");
		System.out.println(list);
		
		System.out.println("오름차순 정렬 ---------------------");
		// 대소문자 구분 O
		Collections.sort(list);
		System.out.print("i   :: ");
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
//				if (t.startsWith("s") || t.startsWith("S")) { 
				if (t.toLowerCase().startsWith("s")) { 
					System.out.print(t + " | ");
				}
			}
		});
		System.out.println();
		
//		System.out.println(list);
		Collections.shuffle(list);
//		System.out.println(list);
		
		// 대소문자 구분 X
//		list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
		list.sort(String :: compareToIgnoreCase);
		System.out.print("ii  :: ");
		list.forEach(str -> {
				if (str.toUpperCase().startsWith("S")) {
					System.out.print(str + " | ");
				}
		});
		System.out.println();
		Collections.shuffle(list);
		System.out.println();
		
		//////////////////////////////////////////
		System.out.println(list);
		System.out.println("내림차순 정렬 ---------------------");
		// 대소문자 구분 O
		System.out.print("i   :: ");
		list.stream()
//			.filter(str -> str.toLowerCase().startsWith("s"))
			.map(str -> str.toLowerCase().startsWith("s"))
			.sorted(Collections.reverseOrder())
			.forEach(str -> System.out.print(str + " > "));
		System.out.println();
		Collections.shuffle(list);
		
		System.out.print("ii  :: ");
		list.stream().filter(str -> str.toLowerCase().startsWith("s"))
					 .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
					 .forEach(str -> System.out.print(str + " > "));
		System.out.println();
		
		System.out.println("---------------------");
		
		////////////////////////////////////////
		// list에서 A가 포함된 문자열들 필터링 
		s1 = list.stream().filter(str -> str.contains("A"));
		
		// list의 문자열들을 모두 대문자로 변경한 새로운 데이터 생성
		s1 = list.stream()
				 .map(str -> str.toUpperCase());
		
		// list의 문자열들을 모두 대문자로 변경
		s1 = list.stream()
				 .map(String :: toLowerCase);
		
		System.out.println(s1);
		System.out.println(s1.collect(Collectors.toList())); // Stream > List 변환
		System.out.println();
		
		// ---------------------------------------------------
		List<Integer> nums = Arrays.asList(1, 3, 5, 7, 9);
		// IntStream은 한 번 작업 후 다시 부르려면 예외 발생함
		// -> 한 IntStream(기본자료형 Stream)당 하나의 작업만 가능-
		IntStream intNums = IntStream.of(10, 30, 50, 70, 90);
		
		System.out.println(intNums.sum());
//		System.out.println(intNums.count());
//		System.out.println(nums.stream().reduce(0, Integer :: sum));
		System.out.println(nums.stream().reduce(0, (x, y) -> x + y));
		System.out.println(nums.stream().reduce(10, (x, y) -> x + y));
		System.out.println(nums.stream().reduce(100, (x, y) -> x + y));
		System.out.println();
		
		// ----------------------------------------------------
		// 1. list의 값을 모두 대문자로 변경한 후
		//    새로운 List 타입의 변수 upperList에 저장
		List<String> upperList = list.stream().map(String :: toUpperCase)
											  .collect(Collectors.toList());
		System.out.println(list);
		System.out.println(upperList);
		System.out.println();
		
		// 2. nums에서 3의 배수를 찾아서 2배한 결과를
		//    새로운 List 타입의 변수 sams에 저장
		List<Integer> sams = nums.stream().filter(n -> n % 3 == 0)
										  .map(n -> n * 2)
										  .collect(Collectors.toList());
		System.out.println(nums);
		System.out.println(sams);
		System.out.println();
		
		// 3. list의 각 단어의 길이를 화면에 출력
		list.forEach(str -> System.out.println(str + "의 길이: " + str.length()));
		System.out.println();
	}
}
