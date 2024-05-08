package javaz.basic;

import java.time.LocalDate;
import java.time.YearMonth;

// switch문
// - 조건이 여러개이거나 특정 경우의 수가 정해져 있는 경우에 유용
// - switch의 조건은 int 이하의 정수 또는 String 값
// - 조건에 맞는 case 문을 수행
// - 조건에 일치하더라도 break(생략 가능) 문이 없으면 계속 수행
// - 모든 case와 일치하지 않는 경우 default(생략 가능) 문 수행

// switch(조건) {
//    case 값1 : // 수행문1;
//       		// 수행문2;
//				// ...		// 원하는 만큼 수행문 추가 가능
//              // 수행문n;
//				[ break; ]  // 생략 가능
//	  case 값2 : // 수행문1;
//    ...
//    case 값n : // 수행문1;
//    [default : //수행문1;]	// 생략 가능
// }

public class Switch {

	public static void main(String[] args) {
		System.out.println("--- SWITCH PIZZA MALL ---");
		String size = "smallnn"; // small / medium / large
		int price = 0;			// 10_000 / 20_000 / 30_000
		
		switch (size) {
		case "small":
			price = 10_000;
			break;
			
		case "medium":
			price = 20_000;
			break; // break를 생략할 경우 조건에 맞아도 가격 30,000 출력됨
			
		case "large":
			price = 30_000;
			break;
	
		default:
			System.out.println("피자 사이즈 선택 오류!!");
			System.out.println("small, medium, large 중 하나를 입력해 주세요.");
			//break; // switch문 중단
			//return;  // 프로그램 실행을 중단하고 호출한 곳으로 반환
		}
		
		System.out.println("사이즈: " + size);
		System.out.printf("가격(원): %,d", price);
		
		System.out.println();
		// 특정 연월의 마지막 날 알아보기 - 윤년 고려
		int year = 2000;
		int month = 2;
		int lastDate = 0; // 30 | 31 | 28 | 29
		
//		switch (month) {
//		case 1:	
//		case 3:
//		case 5:
//		case 7:
//		case 8:
//		case 10:
//		case 12: lastDate = 31; break;
//			
//		case 2:
//			lastDate = year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 
//					29 : 28; break;
//			
//		case 4:
//		case 6:
//		case 9:
//		case 11: lastDate = 30; break;
//
//		default:
//			System.out.println("월 정보 오류!!!");
//			System.out.println("1~12 사이의 숫자를 입력해 주세요.");
//			return;
//		}
		
		// default에서 마지막날 31인 달들 처리
		switch (month) {			
		case 2:
			lastDate = year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 
					29 : 28; break;
			
		case 4:
		case 6:
		case 9:
		case 11: lastDate = 30; break;

		default: lastDate = 31; break;
		}
		
		System.out.println(year + "년 " + month + "월의 마지막 날 : " + lastDate);
		
		// LocalDate를 통해 알 수 있음
		
		LocalDate dateLocal = LocalDate.parse("2000-02-06");
		YearMonth monthLocal = YearMonth.from(dateLocal);
		LocalDate firstDateLocal = monthLocal.atDay(1);
		LocalDate lastDateLocal = monthLocal.atEndOfMonth();
		
		System.out.println(firstDateLocal.getYear()); // 2000
		System.out.println(firstDateLocal.getMonthValue()); // 2
		System.out.println(lastDateLocal.getDayOfMonth()); // 29
		

	}

}
