package javaz.io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public class ReaderWriterMain {
	public static void main(String[] args) throws IOException {
		String file = "src\\user.txt";
		
		// file과 연결된 파일입력스트림을
		// InputStream 타입의 객체 is에 저장한 후
		// 파일의 내용을 읽어서 화면에 표시
		InputStream is = new FileInputStream(file);
		
		int input = 0;
		while((input = is.read()) != -1) { // 1byte 스트링
			System.out.print((char)input); // 한글은 깨짐
		}
		is.close();
		
//		byte[] inputBytes = new byte[1000];
//		is.read(inputBytes);
//		System.out.println(new String(inputBytes).trim());
		
		System.out.println();
		System.out.println("-------------------------------------");
		// 2byte 스트림
		// Reader / Writer
		// - 문자 입출력 스트림의 취상위 추상 클래스
		// - 2바이트씩 읽고 쓸 수 있는 메서드들로 구성
		Reader reader = new FileReader(file);
		while((input = reader.read()) != -1) {
			System.out.print((char)input);
		}
		reader.close();
		
		System.out.println();
		System.out.println("-------------------------------------");
		
		// 파일에 쓰는 2바이트 스트림
		Writer writer = new FileWriter("write.txt");
		writer.write("Let's write! 파일에 써 보아요!");
		System.out.println("-- 파일 쓰기 완료 --");
		writer.close();
	}
}
