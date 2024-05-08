package javaz.api;

// StringBuffer
// - 문자열 수정이 가능한 클래스
// - 문자열의 저장 및 변경을 위한 메모리 버퍼 사용
//   - 초기 버퍼 크기는 16
//   - 동적 변경
// - thread-safe

public class StringBufferMain {
	public static void main(String[] args) {
		String a = "aaa";
//		StringBuffer b = "bbb"; // X
		
		// StringBuffer 클래스의 기본생성자를 이용하여
		// 1. sb1 인스턴스 생성
		StringBuffer sb1 = new StringBuffer();
		// 2. 정수를 매개변수로 받는 생성자를 이용하여 임의의 값을 지정하여 sb2 객체 생성
		StringBuffer sb2 = new StringBuffer(50);
		// 3. "Hello~~~~~~~~~~~~~~~~~~~~~"를 매개변수로 참조변수 sb3 생성
		StringBuffer sb3 = new StringBuffer("Hello~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
		System.out.println(sb1.capacity());
		System.out.println(sb2.capacity());
		System.out.println(sb3.capacity());
		System.out.println();
		
		System.out.println(sb1.length());
		System.out.println(sb2.length());
		System.out.println(sb3.length());
		System.out.println();
		
		System.out.println(sb3.hashCode());
		System.out.println(sb3);
		sb3.append("!");
		System.out.println(sb3);
		System.out.println(sb3.hashCode());
		System.out.println();
		System.out.println(sb3.replace(sb3.indexOf("~"), sb3.lastIndexOf("~") + 1, ""));
		System.out.println(sb3.hashCode());
		
	}

}
