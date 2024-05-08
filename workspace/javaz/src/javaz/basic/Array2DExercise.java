package javaz.basic;

public class Array2DExercise {

	public static void main(String[] args) {
		// 성적들을 저장하는 2차원 배열 변수 scores
		// 각 과목들의 총점을 저장하는 1차원 배열 변수 subjectSum

		int[][] scores = new int[][] { { 100, 95, 98 }, { 27, 48, 22 }, { 69, 77, 80 } };
		int[] subjectSum = new int[scores.length];

		System.out.println("번호\t국어\t영어\t수학\t총점\t 평균");
		System.out.println("---------------------------------------------");
		for (int i = 0; i < scores.length; i++) {			
			int studentSum = 0; // 각 학생의 과목별 총점 저장 변수 선인 및 초기화
			System.out.print(i + 1 + "번\t");
			for (int j = 0; j < scores[i].length; j++) {
				// 각 학생의 과목별 점수 출력
				System.out.printf("%d\t", scores[i][j]);
				// 각 학생의 총점 계산
				studentSum += scores[i][j];
				// 각 과목의 총점 계산
				subjectSum[i] += scores[j][i];
			}
			// 각 학생의 총점 출력
			System.out.print(studentSum + "\t");
			// 각 학생의 평균 계산 출력
			System.out.printf("%.2f\n", (double) studentSum / scores[i].length);
		}
		System.out.println("---------------------------------------------");
		for (int i = 1; i <= 2; i++) {
			if (i == 1) { System.out.print("총점\t"); }
			if (i == 2) { System.out.print("평균\t"); }
			for (int sum : subjectSum) {
				// 과목별 총점 출력
				if (i == 1) { System.out.print(sum + "\t"); }
				// 과목별 평균 출력
				if (i == 2) { System.out.printf("%.2f\t", (double) sum / scores.length); }
			}
			System.out.println();
		}
		// for문 대신 하나씩 출력
//		// 과목별 총점 출력
//		System.out.println();
//		System.out.print("총점\t");
//		for (int sum : subjectSum) {
//			System.out.print(sum + "\t");
//		}
//		
//		// 과목별 평균 출력
//		System.out.println();
//		System.out.print("총점\t");
//		for (int sum : subjectSum) {
//			System.out.printf("%.2f\t", (double) sum / scores.length);
//		}

	}

}
