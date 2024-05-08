package javaz.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResources {
	
	// 파일명을 매개변수로 받아서 읽어온 후 화면에 표시하는 fileRead 메서드
	public void fileRead(String filename) {
		// AutoClosable이라는 인터페이스의 자식 클래스는 아래와 같이 try 내에서 사용 가능
		// 이 경우 close 자동 처리되므로 수동으로 설정할 필요 없음
		try(FileReader fr = new FileReader(filename)) {
			char[] ch = new char[1000];
			fr.read(ch);
			
			for (char c : ch) {
				System.out.print(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일명을 매개변수로 받아서 읽어온 후
	// "파일명.bak" 파일로 저장하는 클래스 메서드 fileReadWrite
	public static void fileReadWrite(String fileName) {
			// 사용하려는 리소스가 여러 개인 경우 세미콜론으로 구분
		try(FileReader fr = new FileReader(fileName);
			FileWriter fw = new FileWriter(fileName + ".bak");) {
			char[] ch = new char[1000];
			
			fr.read(ch);
			for (char c : ch) {
				System.out.print(c);
			}
			
			fw.write(ch);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public static void main(String[] args) {
		String filename = ".classpath";
		
		TryWithResources twr = new TryWithResources();
		twr.fileRead(filename);  // fileRead 메서드 호출
		// fileReadWrite 메서드 호출
		twr.fileReadWrite(filename);
		fileReadWrite(filename); 
		TryWithResources.fileReadWrite(filename);
		
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
		
		FileReader fr = null;
		try {
			fr = new FileReader(filename);
			char[] ch = new char[1000];  // 파일에서 읽은 내용 저장하는 배열
			fr.read(ch); 			     // 파일에서 읽기
			
			// ch 배열의 값을 화면에 출력
			for (char c : ch) {
				System.out.print(c);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일 읽기 오류 발생!!!");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {  // 예외 발생 여부와 관계없이 닫기 처리
			try {
				if (fr != null) {
					fr.close();	
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}
		
	}

}
