package javaz.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputOutputMain {
	// 파일입력스트림
	public static void fileInputStream() throws IOException {
		String filename = ".classpath";
		
		// 읽어올 파일과 연결된 파일입력스트림 객체 생성
		FileInputStream fis = new FileInputStream(filename);
		
		int input = 0;
		// 파일에서 1바이트씩 읽어서 저장한 후
		// 더 이상 읽을 값이 없을 때까지(EOF; End Of File :: -1)
		while ((input = fis.read()) != -1) {
			// 화면에 표시
//			System.out.print((char)input);
			System.out.write(input);
		}
		//파일입력스트림 닫기
		fis.close();
	}
	
	// 파일출력스트림
	public static void fileOutputStream() throws IOException {
		String filename = ".classpath";

		FileInputStream fis = new FileInputStream(filename);
		// "원본파일명.bak" 파일로 저장하기
		FileOutputStream fos = new FileOutputStream(filename + ".bak");
		
		// 파일을 읽어서 
		// 읽어온 파일의 내용이 더이상 없을 때까지
		int input = 0;
		while ((input = fis.read()) != -1) {
			// 1바이트 씩 읽고 백업 파일에 쓰기
			fos.write(input);
		}
		System.out.println("-- 백업 파일 생성 완료 --");
		fos.close();
		fis.close();
	}

	// 키보드 입력을 파일로 저장한 후 저장된 내용을 화면에 출력
	public static void makeTextFile() throws IOException {
		System.out.println("> making text file");
		System.out.print("> 파일 경로(생략 시 현재 디렉토리): ");
		InputStream is = System.in;
		byte[] inputBytes = new byte[Byte.MAX_VALUE];
		
		// 입력 받은 경로 값을 path에 저장
		String path = "";
		int input = 0;
		input = is.read(inputBytes);
//		for (int i = 0; i < input - 2; i++) {
//			path += (char)inputBytes[i];
//		}
		path = new String(inputBytes).trim();
		
		path = path.equals("") || path == null || path.length() < 1 ? "." : path;
		
		System.out.print("> 파일명: ");
		
		// 입력 받은 파일명을 fileName에 저장
		String fileName = "";
		input = is.read(inputBytes);
//		for (int i = 0; i < input - 2; i++) {
//			fileName += (char)inputBytes[i];
//		}
		fileName = new String(inputBytes).trim();
		
		// 입력 받은 경로, 파일명으로 파일 산출
		FileOutputStream fos = new FileOutputStream(path 
								+ System.getProperty("file.separator") + fileName);
		
		System.out.println("> 파일 내용을 입력해 주세요(종료는 Ctrl + z).");
//		System.out.println("> 파일 내용을 입력해 주세요(종료는 /exit).");
		
		// 입력 받은 값(InputStream)을 파일(FileOutputStream)에 저장
		while ((input = is.read()) != -1) {
			fos.write(input);
		}
		
		// '/exit' 값을 받으면 입력 종료 - 잘 안 됨..
//		while (true) {
//			is.read(inputBytes);
//			String content = new String(inputBytes).trim();
//			System.out.println(content);
//			if (content.contains("/exit")) { break; }
//			
//			fos.write(inputBytes);
//		}
		
		System.out.println("> 파일 저장이 완료되었습니다.");
		System.out.println("> 파일 내용: ");
		
		// 파일의 내용을 읽어서 출력(FileInputStream)
		FileInputStream fis = new FileInputStream(path 
				+ System.getProperty("file.separator") + fileName);
		
		while((input = fis.read()) != -1) {
			System.out.write(input);
		}
		
		fis.close();
		fos.close();
		is.close();
	}
	
	public static void main(String[] args) throws IOException {
		makeTextFile();
//		fileOutputStream();
//		fileInputStream();
	}

}
