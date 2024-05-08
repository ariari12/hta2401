package javaz.basic;

import java.util.Arrays;

public class Array1DExercise {

	public static void main(String[] args) {
		System.out.println("=== JAVA TEST SCORE ===");
		// 이름 5개를 저장할 문자열 배열 names를 선언하면서
		// Kim, Lee, Han, Ann, Ben으로 초기화
		
		// 99, 88, 77, 66, 50을 javas 배열 변수에 저장하여 생성
		
		// 저장된 데이터를 다음 형태로 출력
		// 순위	이름		점수
		// 1	~~~		~~
		// 2	~~~ 	~~
		// 3	~~~		~~
		// 4	~~~ 	~~
		// 5	~~~		~~
		// ----------------
		// 총점 : ~~~
		// 평균 : ~~.~~
		
		String[] names = { "Kim", "Lee", "Han", "Ann", "Ben" };
		int[] javas = { 99, 88, 77, 66, 50 };
		int total = 0;
		double avg;
		
		for (int java : javas) {
			total += java;
		}
		
		avg = (double)total / javas.length;
		
		System.out.println("   순위\t이름\t점수");
		for (int i = 0; i < names.length; i++) {
			// 순위, 이름, 점수 출력
			System.out.println("   " + (i + 1) + "\t" + names[i] + "\t" + javas[i]);
			// total에 각 점수를 누적 합산
			// total += javas[i] // 아래 오름차순 시 중복 연산되므로 for 밖에서 계산 수행
		}
		System.out.println("-----------------------");
		System.out.println("총점 : " + total);
		System.out.printf("평균 : %.2f", avg);
		
		System.out.println();
		System.out.println();
		System.out.println("오름차순(낮은 점수가 위로)");; 		
		
		
		// 버블 정렬 - 서로 이웃한 데이터들을 비교하여 맨 뒤로 보냄	
		for (int i = 0; i < javas.length; i++) {
			for (int j = 0; j < i; j++) {
//				if (javas[j] > javas[i]) {	// 오름차순 정렬
				if (javas[j] < javas[i]) {	// 내림 차순 정렬
					int temp = javas[i];
					javas[i] = javas[j];
					javas[j] = temp;
				}
			}
		}
		
		
		int tempJava = 0;
		String tempName = "";
		for (int i = 0; i < javas.length; i++) {
			for (int j = i + 1; j < javas.length; j++) {
				if (javas[i] > javas[j]) {
					tempJava = javas[i];
					tempName = names[i];
					javas[i] = javas[j];
					names[i] = names [j];
					javas[j] = tempJava;
					names[j] = tempName;
				}
			}
		}
		System.out.println("   순위\t이름\t점수");
		for (int i = 0; i < names.length; i++) {
			System.out.println("   " + (names.length - i) + "\t" + names[i] + "\t" + javas[i]);
		}
		System.out.println("-----------------------");
		System.out.println("총점 : " + total);
		System.out.printf("평균 : %.2f", avg);		

	}

}
