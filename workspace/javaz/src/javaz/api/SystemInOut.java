package javaz.api;

import java.io.IOException;

public class SystemInOut {

	public static void main(String[] args) throws IOException {
		// 5. 표준 입출력
		System.out.print("문자 하나를 입력해주세요. : ");
		int input = System.in.read();
		System.out.println("input data : " + input);
		System.out.println("input data : " + (char) input);
//		System.out.println("--------------");
		
//		System.out.print("문자 하나를 입력해주세요. : ");
		input = System.in.read(); // 13 출력됨: 첫 칸으로 이동(\r)(Carriage Return)
		System.out.println("input data : " + input); 
		System.out.println("input data : " + (char) input);
//		System.out.println("--------------");
		
//		System.out.print("문자 하나를 입력해주세요. : ");
		input = System.in.read(); // 10 출력됨: 다음 줄로 이동(\n)(Line feed)
		System.out.println("input data : " + input); 
		System.out.println("input data : " + (char) input);
		System.out.println("--------------");
		
		System.in.close();

	}
	
	

}
