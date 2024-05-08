package javaz.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarMain {

	public static void main(String[] args) {
//		Calendar cal = new Calendar(); // X
		Calendar cal = Calendar.getInstance(); // O
		
		System.out.print(cal.get(Calendar.YEAR) + "년 ");
		System.out.print(cal.get(Calendar.MONTH) + 1 + "월 ");
		System.out.print(cal.get(Calendar.DATE) + "일 ");
		
		String[] weekdays = { "일", "월", "화", "수", "목", "금", "토" };
		System.out.print(weekdays[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일 ");
		
		System.out.print(cal.get(Calendar.AM_PM) == 0 ? "오전" : "오후" + " ");
		System.out.print(cal.get(Calendar.HOUR) + "시 ");
		System.out.print(cal.get(Calendar.MINUTE) + "분 ");
		System.out.println(cal.get(Calendar.SECOND) + "초");
		
		//////////////////////////////////
		System.out.println();
		System.out.println("- 설정 가능한 TimeZone ID 리스트 -");
		
		for (String str : TimeZone.getAvailableIDs()) {
			System.out.println(str);
		}
		
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		Calendar london = Calendar.getInstance(tz);
		
		System.out.println(london);
		
		System.out.println();
		System.out.println("런던 현재 시각");
		System.out.print(london.get(Calendar.YEAR) + "년 " 
							+ (london.get(Calendar.MONTH) + 1) + "월 " 
							+ london.get(Calendar.DATE) + "일 " 
							+ london.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.KOREA) + " ");
		System.out.println((london.get(Calendar.AM_PM) == 0 ? "오전" : "오후") + " " 
							+ london.get(Calendar.HOUR) + "시 " 
							+ london.get(Calendar.MINUTE) + "분 " 
							+ london.get(Calendar.SECOND) + "초");
		
		System.out.println();
		System.out.println("런던 현재 시각");
		System.out.println(new Date(london.getTimeInMillis()));
	}

}
