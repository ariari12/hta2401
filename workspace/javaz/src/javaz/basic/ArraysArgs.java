package javaz.basic;

// command line argument
// - 명령행 매개변수
// - 프로그램 실행 시 필요한 정보를 전달
public class ArraysArgs {

	public static void main(String[] args) {
		// args 배열의 0, 1, 2번째 인덱스의 값을 화면에 출력
		// Run > Run Configuration > Arguments 탭의 Program Argument 항목 아래에 값
		// String 매개 변수를 입력(공백으로 원소 구분), 아래와 같이 출력하여 확인 가능
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		
		System.out.println();
		System.out.println(" - for문을 이용하여 재출력");
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		
		System.out.println();
		System.out.println(" - foreach문을 이용하여 재출력");
		
		for (String arg : args) {
			System.out.println(arg);
		}
		
	}

}
