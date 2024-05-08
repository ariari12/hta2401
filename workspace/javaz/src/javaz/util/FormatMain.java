package javaz.util;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class FormatMain {

	public static void main(String[] args) {
		// 1. 숫자에 , 넣어 천 단위 구분
		DecimalFormat df = new DecimalFormat("#,##0");
		System.out.println(df.format(123456789.98765));
		System.out.println(df.format(123.12345));
		System.out.println();
		
		// 2. 빈 자리 0으로 채우기
		df = new DecimalFormat("0,000");
		System.out.println(df.format(123456789.98765));
		System.out.println(df.format(123.12345));
		System.out.println();
		
		// 3. 소수점 이하 둘째자리까지 표시
		df = new DecimalFormat("#,##0.00");
		System.out.println(df.format(123456789.98765));
		System.out.println(df.format(123.1));
		System.out.println();
		
		// 4. 메시지 포맷 문자열 사용
		String info = "Name: {0}\nPassword: {1}\nEmail: {2}\nBirth: {3}";
		Object[] objs = { "Lee", "1234", "lee@lee.com", "99.09.09" };
		
		System.out.println(MessageFormat.format(info, objs));
	}

}
