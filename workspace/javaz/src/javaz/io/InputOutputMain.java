package javaz.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class InputOutputMain {
	// 표준 입력
	public static void standardInput() throws IOException {
		System.out.print("문자 하나를 입력해주세요. : ");
		int input = System.in.read();
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);
	}
	
	// InputStream 이용
	public static void inputStream() throws IOException {
		System.out.print("단어 하나를 입력해주세요. : ");
		InputStream is = System.in;
//		int input = is.read();	// 1바이트만 읽기 가능
		
		byte[] inputBytes = new byte[10];
		int input = is.read(inputBytes); // 읽은 값을 inputBytes에 저장
		System.out.println("input data : " + input);
		System.out.println("input char : " + (char)input);
		System.out.println(Arrays.toString(inputBytes));
		System.out.print("input word : [");
		// read시 붙는 \r(13), \n(10) 제외하기 위해 읽은 갯수(input) - 2
		for (int i = 0; i < input - 2; i++) { 
			System.out.print((char)inputBytes[i]);
		}
		System.out.println("]");
		System.out.println(new String(inputBytes)); // 한글 처리
		is.close();
		System.out.println();
	}
	
	// InputStream을 try with resources로 열고 닫기
	public static void inputStream2() {
		try (InputStream is = System.in) {
//			int input = 0;
//			System.out.println("---- 단어의 아스키코드 알아보기 ----");
//			System.out.print("단어를 입력해 주세요(종료는 Ctrl + z): "); // -1
//				
//			while (true) {
//				input = is.read();
//					
//				if (input == -1) {
//					System.out.println();
//					System.out.println("- 프로그램이 종료되었습니다. -");
//					return;
//				}
//					
//				if (input == 13) { continue; }
//		
//				if (input == 10) {
//					System.out.print("단어를 입력해 주세요(종료는 Ctrl + z): "); // -1
//					continue;
//				}
//			
//				System.out.println((char)input + " : " + input);
//			}
//				
			
			System.out.println("---- 단어의 아스키코드 알아보기 ----");
			System.out.print("단어를 입력해 주세요(종료는 Ctrl + z): "); // -1
			int input = 0;
			while (true) {
				input = is.read();
				
				switch (input) {
				case 13: continue; // '\r'
				case 10: System.out.print("단어를 입력해 주세요(종료는 Ctrl + z): ");
						 continue; // '\n'
				case -1: System.out.println();
						 System.out.println("- 프로그램이 종료되었습니다. -");
						 System.exit(0);
				default: System.out.println((char)input + " : " + input);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 표준 출력
	public static void standardOutput() {
		System.out.write('A');
		System.out.write(66);
		System.out.write('c');   // 기본 버퍼 512바이트가 다 채워져야 출력
		System.out.write('\n');  // 또는 13, 10을 쓰면 버퍼를 비우고 출력
		System.out.write(10);	 // 
	}
	
	// OutputStream 이용
	public static void outputStream() throws IOException {
		OutputStream os = System.out;
		os.write(68);
		os.write('E');
		os.flush();		// 출력 버퍼 비우기
		
		os.write('F');
		os.write('G');
		os.write('\n');
		
		byte[] outputBytes = { 72, 101, 108, 108, 111 };
		os.write(outputBytes);
		os.write('\n');
		
		
		String hangul = "가나다라마바사아자차카타파하";
		byte[] hangulBytes = hangul.getBytes();
		os.write(hangulBytes);
		os.write('\n');
		
		os.close();		// 스트리 닫기 - 채워진 버퍼를 이용
		
		System.err.write(outputBytes);
	}
	
	public static void main(String[] args) throws IOException {
		outputStream();
//		standardOutput();
//		standardInput();
//		inputStream();
//		inputStream2();
	}

}
