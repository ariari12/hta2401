package javaz.basic;

// if문
// - 조건식과 수행문을 포함하는 블럭{ }으로 구성
// - if 다음의 조건식 또는 연산의 최종 결과가 boolean인 수식만 가능
// - 조건식을 만족하는 경우의 수행문이 하나일 경우에는 블럭{ } 생략 가능 - 생략하지 않는 것 권장

// 수행문이 하나인 경우
// if(boolean 수식)
//    수행문1;

// 수행문이 여러개인 경우
//if(boolean 수식) {
//    수행문1;
//    수행문2;
//    ...
//    수행문n;
// }

// 조건이 두 개인 경우
// if (boolean 수식) {
//    수행문1;
//    수행문2;
//    ...
//    수행문n;
// } else {
//    수행문
// }

// 조건이 둘 이상인 경우
// if (boolean 수식) {
//    수행문1;
//    수행문2;
//    ...
//    수행문n;
// } else if (boolean 수식){
//    수행문
// } else if (boolean 수식){     // else if는 필요한 만큼 추가 가능
//    수행문
// } else if (boolean 수식){
//    수행문
// }
// [else {
//     ...     // else는 생략 가능
// }]

// if (boolean 수식) 수행문1;
// else             수행문1;   // else if는 필요한 만큼 추가 가능
// else             수행문1;
// ...
// [else            수행문1;]  // else는 생략 가능
 
public class If {

	public static void main(String[] args) {
		System.out.println("-- 통합대기환경지수 --");
		int jisu = -160; // 지수 
		String status = "알 수 없음"; // 상태  좋음 / 보통 / 나쁨 / 매우나쁨
		
		if (jisu >= 0) { // 중첩 if - 0 이상이 입력된 경우
			if (jisu <= 50) {
				status = "좋음";
			} else if (jisu <= 100) {
				status = "보통";
				
			} else if (jisu <= 250) {
				status = "나쁨";
			} else {
				status = "매우나쁨";
			}
			System.out.println("현재 지수: " + jisu);
			System.out.println("현재 상태: " + status);
		} else { // 그렇지 않은 경우
			System.out.println("-- 처리 오류");
			System.out.println("0 이상의 수치를 입력해주세요.");
		}
		
		System.out.println();
		System.out.println("-- BOCY MASS INDEX --");
		
		double height = 172.5; 			 // cm
		double heightToM = height / 100; // kg
		double weight = 65.8;
		double bmi = weight / (heightToM * heightToM);
		String result = ""; 			 // 미정
		
		if (bmi >= 35) {
			result = "Extremely Obese";
		} else if (bmi >= 30) {
			result = "Obese";
		} else if (bmi >= 25) {
			result = "Overweight";
		} else if (bmi >= 18.5) {
			result = "Normal";
		} else {
			result = "Underweight";
		}
		
		System.out.printf("키(cm): %.1f\n", height); // 소수점 이하 한자리
		System.out.printf("몸무게(kg): %.1f\n", weight);
		System.out.printf("BMI: %.1f\n", bmi);
		System.out.println("결과: " + result);
	}
}
