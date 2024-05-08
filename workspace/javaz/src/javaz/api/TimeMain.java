package javaz.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

public class TimeMain {
	public static void main(String[] args) {
		// 현재 시점의 날짜 및 시간 객체 생성
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();
		LocalDateTime ldt = LocalDateTime.now();

		System.out.println(ld);
		System.out.println(lt);
		System.out.println(ldt);
		System.out.println();
		
		// 특정 시점의 날짜 및 시간 객체 생성
		ld = LocalDate.of(2020, 10, 10);
		lt = LocalTime.of(11, 22, 33);
		ldt = LocalDateTime.of(2020, 10, 10, 11, 22, 33);
		
		System.out.println(ld);
		System.out.println(lt);
		System.out.println(ldt);
		System.out.println();
		
		// 문자열 파싱
		ld = LocalDate.parse("1999-11-22");
		lt = LocalTime.parse("01:02:03");
		ldt = LocalDateTime.parse("1999-11-22T01:02:03");
		
		System.out.println(ld);
		System.out.println(lt);
		System.out.println(ldt);
		System.out.println();
		
		// 시간대 지정
		ldt = LocalDateTime.now();
		ZonedDateTime seoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
		
		System.out.println(ldt);
		System.out.println(seoul);
		System.out.println(london);
		System.out.println();
		
		System.out.println("올해는 " + ld.getYear());
		System.out.println("윤년? " + ld.isLeapYear());
		System.out.println("지금은 " + ld.getMonth() + " " + ld.getMonthValue() + "월");
		System.out.println("오늘은 " + ld.getDayOfWeek() + " " + ld.getDayOfWeek().getValue() + "요일");
		
		System.out.println();
		System.out.println(lt.getHour() + " : " + lt.getMinute() + " : " + lt.getSecond());
		System.out.println();
		
		System.out.println("이 달의 첫요일은 " 
				+ ldt.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek() + " "
				+ ldt.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue() + "요일");
		
		System.out.println("이 달의 마지막날은 " 
				+ ldt.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth() + "일");
		System.out.println();
		
		/////////////////////////////////////////////////////
		// 명령행 매개변수로 생년월일을 입력받아 LocalDate 타입의 변수 birth에 저장
		LocalDate birth = LocalDate.parse(args[0]);
//		birth = LocalDate.parse("2006-03-19");
		System.out.println("19세가 되는 해 " + birth.plusYears(19).getYear());
		
		ld = LocalDate.now();
		System.out.println("오늘은 " + ld);
		System.out.println("생년월일 " + birth);
//		System.out.println(birth.isAfter(ld) ? "after" : "");
//		System.out.println(birth.isEqual(ld) ? "equal" : "");
//		System.out.println(birth.isBefore(ld) ? "before" : "");
		
		// 올해의 생일이 지났으면 "생일 이후"
		// 생일이면 "생일"
		// 생일이 지나지 않았으면 "생일 이전"을 출력
		birth = birth.withYear(ld.getYear());
		System.out.println(birth);
		
		System.out.println(ld.isAfter(birth) ? "생일 이후" : "");
		System.out.println(ld.isEqual(birth) ? "생일" : "");
		System.out.println(ld.isBefore(birth) ? "생일 이전" : "");
		System.out.println();
		
		if (LocalDate.now().getYear() - birth.getYear() >= 19) {
			System.out.println("19세 이상입니다.");
		}
		
		if (LocalDate.now().getYear() - birth.getYear() < 19) {
			System.out.println("19세 미만입니다.");
		}
		
	}

}
