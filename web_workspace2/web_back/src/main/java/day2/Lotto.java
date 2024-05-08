package day2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
	int[] rnd;
	int[] m;
	
	public Lotto() {
		rnd = new int[45];
		m = new int[6];
		
		init(); // 초기화
		shuffle(); // 1000번 섞기
		set(); // 6개의 값 배열에 담기
		sort(); // 정렬
		
	}
	
	private void init() {
		for (int i = 0; i < rnd.length; i++) { rnd[i] = i + 1; }
		System.out.println(Arrays.toString(rnd));
	}
	
	private void shuffle() {
		
//		System.out.println("rnd[" + a + "]: " + a);
//		System.out.println("rnd[" + b + "]: " + b);
		
		for (int i = 0; i < 1000; i++) {
			int a = (int) (Math.random() * 45);
			int b = (int) (Math.random() * 45);
			int temp = 0;
			temp = rnd[a];
			rnd[a] = rnd[b];
			rnd[b] = temp;
		}
		
		List<Integer> rndShuffle = Arrays.stream(rnd).boxed().collect(Collectors.toList());
		Collections.shuffle(rndShuffle);
		rnd = rndShuffle.stream().mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(rnd));
	}
	
	private void set() {
//		for (int i = 0; i < 6; i++) {
//			m[i] = rnd[i];
//		}
		m = Arrays.copyOf(rnd, 6);
		System.out.println(Arrays.toString(m));
	}
	
	private void sort() {
		Arrays.sort(m);
		System.out.println(Arrays.toString(m));
	}
	
	public void print() {
		for(int num : m) {
			System.out.print(num + "\t");
		}
	}

	public int[] getM() {
		return m;
	}

	public void setM(int[] m) {
		this.m = m;
	}
	
}
