package javaz.api;

class ClassA {  }

class ClassB {
	// 현재 객체를 "This is ClassB"로 반환하는 부모 메서드 오버라이딩
	@Override
	public String toString() {
		return "This is ClassB";
	}
}

public class ObjectMain {

	public static void main(String[] args) {
		// 1. toString() 객체를 문자열로 반환 ---------------------------
		ClassA ca = new ClassA(); 			// ClassA의 객체 ca 생성
		ClassB cb = new ClassB(); 			// ClassB의 객체 cb 생성
		System.out.println(ca.toString());  // ca를 화면에 출력
		System.out.println(cb);				// cb를 화면에 출력
		
		System.out.println();
		// 2. equals(Object obj) 객체의 값 비교 -----------------------
		String a = "haha";
		String b = "haha";
		String c = new String("haha");
		String d = new String ("haha");
		
		System.out.println(a == b);
		System.out.println(b == c);
		System.out.println(c == d);
		System.out.println();
		
		System.out.println(a.equals(b));
		System.out.println(b.equals(c));
		System.out.println(c.equals(d));
		System.out.println();
		
		// 3. hashCode() 객체를 구별하기 위한 고유 정수값 -----------
		//    - 객체 생성 시 동적으로 부여되며
		// 		객체를 구별하는 기준이 됨 
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());
		System.out.println();
		
		a = "ha";
		b = "haha";
		c = "hahaha";
		d = "hahahaha";
		
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());
		System.out.println();
		
		// 4. getClass() 어떤 클래스로 객체가 생성되었는지 정보 반환 ----
		System.out.println(a.getClass());
		System.out.println(b.getClass().getName());
		System.out.println(c.getClass().getPackageName());
		System.out.println(d.getClass().getSimpleName());
		
		System.out.println();
		// 현재 클래스 실행 시 명령행 매개변수로 아이디를 입력받아
					   // └> Run > Run Configuration > Argument 항목에 값 입력
					   //    여러 값 입력 시에는 공백으로 구분
		// 아이디가 admin이면 "관리자 로그인"을 출력하고
		// 그렇지 않으면 "일반 로그인"을 출력

		// 첫 번째 인덱스([0])만 검증
		if (args[0].equals("admin")) {
			System.out.println("관리자 로그인");
		} else {
			System.out.println("일반 로그인");
		}
		System.out.println();
		
		// 배열 전체 검증
		for (String str : args) {
			System.out.println("ID: " + str);
			if (str.equals("admin")) {
				System.out.println("관리자 로그인");
			} else {
				System.out.println("일반 로그인");
			}
			System.out.println();
		}
		
	}

}
