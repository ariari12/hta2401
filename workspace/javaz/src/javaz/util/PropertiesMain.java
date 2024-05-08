package javaz.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

// Properties
// - 속성들을 모아서 하나의 객체로 만들기 위해 제공되는 클래스
// - 프로그램 시작 전에 필요한 속성들을 미리 인식시켜서
//   키와 값을 String 타입으로 제한
// - 프로퍼티 파일(~~~.properties)을 읽어들일 때 사용
public class PropertiesMain {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("USER", "dev");
		prop.setProperty("PASSWORD", "1111");
		
		System.out.println(prop);
		System.out.println(prop.get("USER"));
		System.out.println(prop.getProperty("PASSWORD"));
		System.out.println();
		
		Enumeration<?> keyz = prop.propertyNames();
		while (keyz.hasMoreElements()) {
			String key = (String) keyz.nextElement();
			System.out.println(key + " : " + prop.get(key));
		}
		System.out.println();
		
		// 키보드 입력과 연결된 Scanner 객체 sc 생성
		Scanner sc = new Scanner(System.in); 
		System.out.print("시스템 프로퍼티의 이름을 입력하세요 : ");
		String propName = sc.nextLine();
		sc.close();
		
		Properties props = System.getProperties();
//		props.list(System.out); // 확인용
		
		if (props.containsKey(propName)) {
			System.out.println(propName + "의 값: " + System.getProperty(propName));
		} else {
			System.out.println("입력하신 프로퍼티가 존재하지 않습니다.");
		}
	}

}
