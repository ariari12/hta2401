package javaz.basic;

import java.util.Arrays;

public class Array2D {

	public static void main(String[] args) {
		// 2차원 배열의 선언 -----
		// 데이터타입[][] 변수명;
		// 데이터타입     변수명[][];
		// 데이터타입[]   변수명[]
		
		// 배열의 생성 -----
		// 변수명 = new 데이터타입[행의 크기][열의 크기]; // 고정 길이 배열
		// 변수명 = new 데이터타입[행의 크기][];       // 가변 길이 배열
		
		int[][] scores;
		char grades[][];
		String[] users[];		
	
		// 배열의 선언 및 생성 --------
		// 데이터타입[] 변수명 = new 데이터타입[배열의 크기]
		
		// 배열의 초기화 ----------
		// 자동 초기화 - 생성 시 기본값(int는 0, boolean은 false...)
		// 직접 초기화 - 변수명[행인덱스][열인덱스] = 초기화값;
		
		// 배열의 선언 및 생성과 초기화
		// 데이터타입[][] 변수명 = new 데이터타입[][] { { 값1, 값2, ..., 값n }, 
									//		   { 값1, 값2, ..., 값n }, 
									//				   ...
									// 								};
		// 데이터타입[][] 변수명 = 				 { { 값1, 값2, ..., 값n },
									//		   { 값1, 값2, ..., 값n }, 
									//				   ...
									// 								};
		
		scores = new int[2][3];
		grades = new char[2][];
		
		scores[0][0] = 99;
		scores[0][1] = 88;
		scores[0][2] = 77; // 첫번째 행 완료
		scores[1][0] = 66;
		scores[1][1] = 55;
		scores[1][2] = 44; //두번째 행 완료
		
		double[][] pct = new double[][] { { 0.1, 0.2 }, {0.3, 0.4} };	
		
		String[][] levels = { { "AA", "BB" }, { "CC", "DD", "EE" } };
		
		System.out.println();
		System.out.println("pct 배열의 길이: " + pct.length);
		System.out.println("pct 배열의 첫 번째 행 길이: " + pct[0].length);
		System.out.println("pct 배열의 두 번째 행 길이: " + pct[1].length);
		System.out.println();
		System.out.println("levels 배열의 길이: " + levels.length);
		System.out.println("levels 배열의 첫 번째 행 길이: " + levels[0].length);
		System.out.println("levels 배열의 두 번째 행 길이: " + levels[1].length);
		
		System.out.println();
		System.out.println("pct 배열의 데이터");	
		for (int i = 0; i < pct.length; i++) {
			for (int j = 0; j < pct[i].length; j++) {
				System.out.print(pct[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("pct 배열의 데이터 - foreach");
		for (double[] d : pct) {
			for (double percent : d) {
				System.out.print(percent + " ");
			}
			System.out.println();
		}
	}

}
