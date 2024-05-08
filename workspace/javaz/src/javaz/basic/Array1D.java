package javaz.basic;

import java.util.Arrays;

//array배열
//- 같은 자료형의 원소를 정해진 개수만큼 가지고 있는 객체
//- 여러 개의 저장 공간을 하나의 배열이름으로 접근
//- 각 공간은 숫자 인덱스(0부터 시작)를 가짐
//- 배열이 생성될 때 크기가 정해진 후 변경 X
//- length 필드: 배열의 길이 정보 저장
//- 2차원 이상의 배열: 배열의 배열 구조 

// 1차원 배열
// 선언 | 생성 | 초기화 | 사용

public class Array1D {	
	
	public static void main(String[] args) {
		// 배열의 선언 -----
		// 데이터타입[] 변수명;
		// 데이터타입 변수명[];
		
		// 배열의 생성 -----
		// 변수명 = new 데이터타입[배열의 크기];
		
		// 배열의 선언 및 생성 --------
		// 데이터타입[] 변수명 = new 데이터타입[배열의 크기]
		
		// 배열의 초기화 ----------
		// 자동 초기화 - 생성 시 기본값(int는 0, boolean은 false...)
		// 직접 초기화 - 변수명[인덱스] = 초기화값;
		
		// 배열의 선언 및 생성과 초기화
		// 데이터타입[] 변수명 = new 데이터타입[] { 값1, 값2, ..., 값n };
		// 데이터타입[] 변수명 = { 값1, 값2, ..., 값n };
		
		double[] pct = new double[] { 0.1, 0.2, 0.3 };
		String[] users = { "Kim", "Lee", "Han" };
//				 users = { "Ann", "Ben", "Ken" }; 
				 // 이미 선언된 배열의 경우는 위처럼은 X, 아래처럼은 O
				 users = new String[] { "Kim", "Lee", "Han" };
		
		int[] scores; // 정수 데이터들을 저장하는 scores 배열 선언
		String[] grades; // 문자 데이터들을 저장하는 grades 배열 선언
		
		scores = new int[3]; // scores 배열 변수를 3개 크기를 갖도록 생성
		grades = new String[3]; // grades 배열 변수를 3개 크기를 갖도록 생성
		
		boolean[] pass = new boolean[3]; //논리값 데이터 3개를 저장하는 pass 배열 선수 선언 및 생성
		
		// scores 배열의 첫번째는 99, 세번째는 77로 초기화
		scores[0] = 99;
		scores[2] = 77;
//		scores[3] = 77; // 배열의 범위를 넘어가는 인덱스 지정 예외
		
		// scores 배열의 값을 화면에 출력
		System.out.println(scores); // 배열의 주소가 출력됨
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);
		
		// System.out.println(Arrays.toString(scores));
		
		System.out.println();
		System.out.println("scores 배열 값 출력 - for문 이용");
		for (int i = 0; i <= 2; i++) {
			System.out.println(scores[i]);
		}
		
		System.out.println();
		System.out.println("scores 배열 값 출력 - length 필드 이용");
		for (int i = 0; i < scores.length; i++) {
			System.out.println(scores[i]);
		}
		
		System.out.println();
		System.out.println("scores 배열 값 출력 - foreach 이용");
		for (int score : scores) {
			System.out.println(score);
		}
		
		System.out.println();
		System.out.println("foreach 이용");
		for (boolean  b : pass) {
		//  배열변수타입 변수명 배열 변수명
			System.out.println(b);		}
		
		

	}

}
