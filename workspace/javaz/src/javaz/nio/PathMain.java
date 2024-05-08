package javaz.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

// java.nio 패키지
// - 기존 java.io 패키지를 개선한 입출력 관련 패키지
//   - io 스트림 : 입력, 출력 스트림을 구분하여 별도로 생성
//   - nio 채널 : 양방향 입출력이 가능 - 하나만 생성
// - 클라이언트 수가 많고, 적은 용량 빠른 입출력 작업

// java.nio.file 패키지
// - 파일 시스템에 존재하는 파일이나 디렉터리 경로의
//   생성/조작/비교, 경로 요소 기능 등 제공
// - Files 클래스를 이용
public class PathMain {
	public static void main(String[] args) throws IOException {
		// 슬래시(/)로 경로 구분 시 경로구분자(\)로 자동으로 변환해줌
		Path path = Paths.get("src/javaz/nio/PathMain.java");
		System.out.println(path);
		System.out.println(path.getFileName());
		System.out.println(path.getParent());
		System.out.println(path.getParent().getFileName());
		System.out.println("TOTAL COUNT : " + path.getNameCount());
		System.out.println("------------------------------");
		
		for (Path p : path) {
			System.out.println(p.getFileName());
		}
		
		System.out.println("------------------------------");
		System.out.println(path.toAbsolutePath());
		System.out.println();
		
		System.out.println("파일이름 : " + path.getFileName());
		System.out.println("디렉터리 : " + Files.isDirectory(path));
		System.out.println("파일 : " + Files.isRegularFile(path));
		System.out.println("------------------------------");
		System.out.println();
		
		Path dir = Paths.get("src/javaz/nio/temp");
		Path file = Paths.get("src/javaz/nio/temp/file.txt");
		System.out.println("디렉터리 이름: " + dir.getFileName());
		System.out.println("파일 이름: " + file.getFileName());
		System.out.println();
		
		System.out.println("디렉터리: " + Files.exists(dir));
		System.out.println("파일: " + Files.exists(file));
		// 지정된 경로가 파일이 존재하지 않는 경우 생성하기
		// "temp 디렉터리가 존재하지 않습니다."
		// "디렉터리를 생성합니다.
		if (Files.notExists(dir)) {
			System.out.println("------------------------------");
			System.out.println(dir.getFileName() + " 디렉터리가 존재하지 않습니다.");
			System.out.println("디렉터리를 생성합니다.");
//			Files.createDirectory(dir);		// 본인 디렉터리만 생성
			Files.createDirectories(dir);	// 부모 디렉터리 없을 경우 같이 생성해줌
			System.out.println("------------------------------");
		}
		
		// "file.txt 파일이 존재하지 않습니다."
		// "파일을 생성합니다."
		if (!Files.exists(file)) {
			System.out.println("------------------------------");
			System.out.println(file.getFileName() + " 파일이 존재하지 않습니다.");
			System.out.println("파일을 생성합니다.");
			Files.createFile(file);
			System.out.println("------------------------------");
		}
		System.out.println();
		
		// 스트림을 이용하여 파일 처리
		path = Paths.get("src/javaz/nio");
		Files.list(path).forEach(System.out :: println);
		System.out.println();
		
		// src/user.txt 파일을 스트림으로 연결하여 화면에 출력
		Path userTxt = Paths.get("src/user.txt");
		Files.lines(userTxt).forEach(System.out :: println);
		
		System.out.println();
		List<String> soo = Files.lines(userTxt)
								.filter(s -> s.contains("Soo"))
								.collect(Collectors.toList());
		
		System.out.println(soo);
		
	}

}
