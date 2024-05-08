package javaz.io;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// 추가 검토 사항
// 1. 없는 파일 접근 시도 시 예외 catch
// 2. 중복되는 작업(파일명 받기 / 경로 받기) 메서드 분리하여 재사용

public class FileManager {
	private BufferedReader br, reader;
	private BufferedWriter bw;
	private String fileName, path, copyFileName, copyPath;
	private String input;
	private File file, dir;
	private Path realPath;
	
	public FileManager() {
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			realPath = Paths.get(".").toRealPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 0. 메인메뉴
	public void menu() throws IOException {
		while (true) {
			System.out.println("------------------ JAVA FILE MANAGER ---------------------");
			System.out.println("  1.NEW   2.OPEN   3.COPY   4.DELETE   5.LIST   6. QUIT");
			System.out.print(">> 선택 : ");
			
			input = reader.readLine();
			
			switch (input) {
			case "1": newFile(); break;
			case "2": openFile(); break;
			case "3": copyFile(); break;
			case "4": deleteFile(); break;
			case "5": list(); break;
			case "6": System.out.println(">> 프로그램을 종료합니다.");
					  reader.close();
					  System.exit(0);
			default:  System.out.println("1 ~ 6 사이의 값을 입력해주세요.");
					  break;
			}
		}
	}
	
	// 1. 신규 생성
	public void newFile() throws IOException {
		System.out.println(">> 신규 파일 작성");
		System.out.print("   파일명 : ");
		fileName = requestFileName();
		path = requestPath();
		
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		System.out.println("   저장할 내용을 입력해 주세요.(종료는 /q)");
		requestWrite();
	}
	
	// 2. 열람
	public void openFile() throws IOException {
		System.out.println(">> 파일 열기");
		
		System.out.print("   파일명 : ");
		fileName = requestFileName();
		path = requestPath();
		
		if (!isExist()) {
			System.out.println("파일 혹은 경로가 존재하지 않습니다.");
			return;
		}
		
		System.out.println(">> 파일 내용: ");
		
		br = new BufferedReader(new FileReader(path + fileName));
		
		while ((input = br.readLine()) != null) {
			System.out.println(input);
		}
		
		br.close();
		
		while (true) {
			System.out.println();
			System.out.println("   1. 내용 추가   2. 내용 변경(덮어쓰기)   3.이름 변경   4.종료");
			System.out.print(">> 선택: ");
			input = reader.readLine();
			
			switch (input) {
			case "1": writeExisting(); break;
			case "2": writeNewing(); break;
			case "3": fileName = rename();
					  break;
			case "4": System.out.println(">> 메인 메뉴로 돌아갑니다.");
					  return;
			default:  System.out.println("1 ~ 4 사이의 값을 입력해주세요.");
					  break;
			}
		}
			
	}
	
	// 2.1 기존 내용 쓰기(기존 값 읽기)
	public void writeExisting() throws IOException {
		bw = new BufferedWriter(new FileWriter(path + fileName, true));
		
		System.out.println("   추가할 내용을 입력해 주세요.(종료는 /q)");
		while(true) {
			if ((input = reader.readLine().trim()).equalsIgnoreCase("/q")) {
				System.out.println(">> 파일 작성 완료");
				break;
			}
			
			bw.append(input);
			bw.newLine();
		}
		
		bw.close();
	}
	
	// 2.2 신규 내용 쓰기
	public void writeNewing() throws IOException {
		System.out.println("   덮어쓸 내용을 입력해 주세요.(종료는 /q)");
		requestWrite();
	}
	
	// 2.3 이름 변경
	public String rename() throws IOException {
		Path currentPath = Paths.get(path + fileName);
		String newName = "";
		
		System.out.print("   변경할 이름을 적어주세요: ");
		newName = requestFileName();
		
		Path newPath = Paths.get(path + newName);
		
		Files.move(currentPath, newPath, StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println(">> 이름이 변경되었습니다.");
			
		return newName;
	}
	
	// 3. 복사
	public void copyFile() throws IOException {
		System.out.println(">> 파일을 복사합니다.");
		
		System.out.print(">> 원본 파일명 : ");
		fileName = requestFileName();
		path = requestPath();
		
		if (!isExist()) {
			System.out.println("파일 혹은 경로가 존재하지 않습니다.");
			return;
		}
		
		Path original = Paths.get(path + fileName);
		
		// 사본 경로, 파일명 지정하여 생성
		System.out.print(">> 사본 파일명 : ");
		copyFileName = requestFileName();
		copyPath = requestPath();
		
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		Path copy = Paths.get(copyPath + copyFileName);
		
		Files.copy(original, copy);
		
		System.out.println("파일 복사 완료");
	}
	
	// 4. 삭제
	public void deleteFile() throws IOException {
		System.out.println(">> 파일을 삭제합니다.");
		
		System.out.print(">> 삭제 파일명 : ");
		fileName = requestFileName();
		path = requestPath();
		
		if (!isExist()) {
			System.out.println("파일 혹은 경로가 존재하지 않습니다.");
			return;
		}
		
		Path file = Paths.get(path + fileName);
		System.out.print(">> 정말 삭제하시겠습니까?(y): ");
		if (reader.readLine().trim().equalsIgnoreCase("y")) {
			Files.delete(file);
			System.out.println(">> 파일 삭제 완료");
			return;
		}
		
		System.out.println(">> 삭제 작업을 취소했습니다.");
	}
	
	// 5. 목록 출력
	public void list() {
		file = new File(realPath.toString());
		File[] files = file.listFiles();
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		int cntFile = 2;
		int cntDir = 0;
		long totalFileSize = 0;
		
		System.out.println(path + ">dir");
		
		for (File f : files) {
			String dir = "";
			long size = 0;
			String name = f.getName();
			
			if (f.isDirectory()) {
				dir = "<DIR>";
				cntDir++;
			}
			
			if (f.isFile()) {
				size = f.length();
				cntFile++;
				totalFileSize += f.length();
			}
			
			System.out.print(dateFmt.format(new Date(f.lastModified())));
			System.out.printf("\t%-9s", dir);
			System.out.printf("\t%10s", size);
			System.out.println("\t" + name);
		}
		System.out.printf("\t   %d개 파일\t\t\t %,20d 바이트\n", cntFile, totalFileSize);
		System.out.printf("\t   %d개 디렉터리\t\t\t%,12d 바이트 남음\n", cntDir, file.getFreeSpace());
		
		System.out.println(path + ">");
		System.out.println();
	}
	
	// 공통 메서드: 파일명 받기
	public String requestFileName() throws IOException {
		while (true) {
			fileName = reader.readLine().trim();
			
			if (fileName.isEmpty()) {
				System.out.println(">> 이름을 입력해 주세요.");
				continue;
			}
			
			break;
		}
		
		return fileName;
	}
	
	// 공통 메서드: 경로 받기
	public String requestPath() throws IOException {
		System.out.print("   경로명(미입력 시 현재 디렉토리): ");
		path = reader.readLine().trim();
		path = path == null || path.isEmpty() ? 
				realPath.toString() : path ;
		path += System.getProperty("file.separator");
		System.out.println(path + fileName);
		
		return path;
	}
	
	// 공통 메서드: 존재하는 경로인지 확인
	public boolean isExist() {
		dir = new File(path);
		file = new File(path + fileName);
		
		if (dir.exists() && file.exists()) { return true; }
		
		return false;
	}
	
	// 공통 메서드: 파일에 저장할 내용 입력 받기
	public void requestWrite() throws IOException {
		bw = new BufferedWriter(new FileWriter(path + fileName));
		
		while (true) {
			input = reader.readLine().trim();
			if ((input == null || input.equalsIgnoreCase("/q"))) {
				System.out.println(">> 파일 작성 완료");
				break;
			}
			
			bw.write(input);
			bw.newLine();
		}
		
		bw.close();
	}
	
	public static void main(String[] args) {
		try {
			new FileManager().menu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
