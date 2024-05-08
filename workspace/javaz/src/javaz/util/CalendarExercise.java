package javaz.util;

import java.util.Calendar;
import java.util.Locale;

public class CalendarExercise {
	private static Calendar cal;

	// 1. 기본생성자에서 cal 객체 생성
	public CalendarExercise() {
		cal = Calendar.getInstance();
	}

	// 2. 오늘이 속한 달의 달력을 출력하는 printMonth 공유 메서드
	public static void printMonth() {
		System.out.println(" " + cal.get(Calendar.YEAR) + "년 " 
							+ (cal.get(Calendar.MONTH) + 1) + "월");

		String[] weekdays = { "일", "월", "화", "수", "목", "금", "토" };
		String weekday = weekdays[cal.get(Calendar.DAY_OF_WEEK) - 1];
		
		cal.set(Calendar.DATE, 1);
		int start = cal.get(Calendar.DAY_OF_WEEK);
		int last = cal.getActualMaximum(Calendar.DATE);

//		System.out.println(weekday + "요일");
		System.out.println();
		System.out.println(" Sun Mon Tue Wed Thr Fri Sat");
		System.out.println(" ---------------------------");

		for (int i = 1; i < start; i++) {
			System.out.print("    ");	// 시작 요일까지 공백찍기
		}
		
		for (int i = 1; i <= last; i++) {
			System.out.printf("%4d", i);	// 날짜 찍기
			
			if (start++ % 7 == 0) { System.out.println(); }
		}

		System.out.println();
		System.out.println();
	}

	// 3. 지금 시간을 출력하는 printNow 정적 메서드
	public static void printNow() {
		System.out.print(" " + (cal.get(Calendar.AM_PM) == 0 ? "오전" : "오후") + " ");
		System.out.println(cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) 
							+ ":" + cal.get(Calendar.SECOND));
		System.out.println(" ------------");
	}

	// 4. 명령행 매개변수로 특정 연도와 월을 입력받아 달력을 표시하는 printCalendar 메서드
	public void printCalendar(String[] args) {
		int year = 0;
		int month = 0;

		if (args.length != 2) {
			System.out.println("년도와 월을 입력해주세요. (ex) 2024 2");
			return;
		}

		// 숫자 아닐 경우 예외 처리
		try {
			year = Integer.parseInt(args[0]);
			month = Integer.parseInt(args[1]);
			
			// 혹은 아래처럼 파싱 및 설정 바로 진행(이후 set 할 필요 없어짐)
			cal.set(Calendar.YEAR, Integer.parseInt(args[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(args[1]));
			
			// 년도 검증 - 범위 어디부터 어디까지??
			
			// 월 검증
			if (month < 1 || month > 12) {
				System.out.println("월 오류: " + month);
				System.out.println("월 항목에 1 ~ 12 사이의 숫자를 입력해주세요.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요. (ex) 2024 2");
		}

		// 입력 받은 년도/월의 첫째 날로 세팅
		cal.set(year, month - 1, 1);

		// 첫째 날의 요일 확인
		int dayOf1 = cal.get(Calendar.DAY_OF_WEEK);
		
		// 둘째주 일요일의 날짜 확인 
		// [토요일 값(7)] - [1일 요일] + 1: 앞으로 며칠 진행하면 첫째주가 끝나는지, 첫째주 토요일 날짜
		// 마지막 부분의 + 1: 둘째주 일요일 날짜 도출 
		int secondWeekSun = Calendar.SATURDAY - dayOf1 + 1 + 1;
		
		// 입력받은 년도-달의 마지막 날짜 확인
		int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(" " + cal.get(Calendar.YEAR) + "년 " 
							+ (cal.get(Calendar.MONTH) + 1) + "월");
		System.out.println(" ----------------------------------------------------");
		
		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			cal.set(Calendar.DAY_OF_WEEK, i);
			System.out.printf("%3s\t", cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA));
		}
		System.out.println();
		System.out.println(" ----------------------------------------------------");

		// 마이너스로 시작해서 첫째날 요일에 date가 1이 되도록 설정
		for (int date = secondWeekSun - 7; date <= lastday; date++) {
			// 매주 일요일일 때마다 개행(첫째주 제외)
			if (date % 7 == secondWeekSun % 7 && date >= secondWeekSun) {
				System.out.println();
			}
			
			// day 값이 마이너스일 경우 건너뛰기
			if (date < 1) {
				System.out.print("\t");
				continue;
			}
			
			System.out.printf("%4d\t", date);
		}

		System.out.println();
		System.out.println();

	}

	public static void main(String[] args) {
		CalendarExercise ce = new CalendarExercise();
		// 5. 2, 3, 4번 메서드 호출
		printNow();
		printMonth();
		printNow();
		ce.printCalendar(args);
//		ce.printCalendar(new String[] { "2024", "2" }); // 테스트용
	}

}
