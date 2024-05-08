package javaz.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

// System 클래스
// - 운영체제와 관련된 필드와 메서드 제공
// - 객체 생성 불필요
//   - 모든 멤버가 static으로 선언되어 ㅣㅇㅆ음
// - 표준 입력/출력/에러, 프로그램 종료, 메모리 정리
// - 현재 시간 및 시스템 프로퍼티, 환경 변수 사용 등 가능

public class SystemMain {
	public static void main(String[] args) throws IOException {
		// 1. 1부터 int형의 최대값까지 합산 소요 시간 계산
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd a hh시 mm분 ss초");
												   // [HH]: 24시간 표기
												   // [a hh]: 오후 오전 표기, 12시간 표기
		long start = System.currentTimeMillis();
		Date date = new Date(start);
		long sum = 0L;
		System.out.println("currentTimeMillis : " + start);
		System.out.println("시작 : " + sdf.format(date));
		// 1부터 int형의 최대값까지 누적 합산
		for (long i = 1L; i <= Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		date = new Date(end);
		System.out.println("종료 : " + sdf.format(date));
		System.out.println("소요 시간 : " + (double) (end - start) / 1000 + "초");
		System.out.printf("1부터 %,d까지의 합 : %,d\n", Integer.MAX_VALUE , sum);
		System.out.println();
		
		// 2. 시스템 프로퍼티 읽어보니 ----------------
		System.out.println("운영체제: " + System.getProperty("os.name"));
		System.out.println("사용자명: " + System.getProperty("user.name"));
		System.out.println("사용자 홈디렉토리: " + System.getProperty("user.home"));
		System.out.println("파일 구분자: " + System.getProperty("file.separator"));
		System.out.println("경로 구분자: " + System.getProperty("path.separator"));
		System.out.println();
		
		Properties props = System.getProperties();
		props.list(System.out);
		System.out.println();
		
		// 3. 환경 변수 값 읽어오기 ----------------
		System.out.println("[JAVA_HOME] " + System.getenv("JAVA_HOME"));
		System.out.println("[PATH] " + System.getenv("Path"));
		System.out.println();
		
		System.out.println("--- Path list ---");
		String[] pathes = System.getenv("Path")
				 				.split(System.getProperty("path.separator"));
		for (String path : pathes) {
			System.out.println(path);
		}
		// replace 사용하여 출력
		System.out.println(System.getenv("Path")
				     			 .replace(System.getProperty("path.separator"), "\n"));
		System.out.println();
		
		// 4. 프로그램 종료
		for (int i = 3; i >= 0 ; i--) {
			System.out.println(i);
			// 0을 출력했으면
			
			if (i == 0) {
				System.err.println("시스템을 종료합니다.");
//				break;          // 1. 반복을 중단
//				return;         // 2. 프로그램을 호출한 쪽으로 반환
//				System.exit(0); // 3. 시스템 종료
			}
		}
		 System.out.println("out of for");
		
		 // 5. 표준 입출력
		 System.out.print("문자 하나를 입력해주세요. : ");
		 int input = System.in.read();
		 System.out.println("input data : " + (char) input);
		 System.out.println("--------------");
		
	}

}
