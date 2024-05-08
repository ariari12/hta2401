package javaz.test.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

public class FileManager {
	private FileWriter fw;
	private BufferedReader reader, br;
	private BufferedWriter bw;
	
	private String filename, path;
	private String input;
	private File file, dir;
	
	public FileManager() {
		reader = new BufferedReader(
						new InputStreamReader(System.in));
	}

	public void menu() throws IOException {
		while(true) {
			System.out.println("---------------- JAVA FILE MANAGER TESTER ----------------");
			System.out.println("  1.NEW  2.OPEN  3.COPY  4.DELETE " +
							   "5.LIST  6.QUIT");
			System.out.print(">> 선택 : ");
			switch (reader.readLine()) {	 
			case "1":	newFile();		break;
			case "2":	openFile();		break; 
			case "3":	copyFile();		break;
			case "4":	deleteFile();	break;
			case "5":	list();			break;
			case "6":	System.out.println("시스템을 종료합니다.");
						reader.close();
						System.exit(0);
			default:System.out.println(">> 1 ~ 6을 입력해주세요."); 
			}
			System.out.println();
		}//END while
	}//END menu()

	public void newFile() throws IOException {
		System.out.println(">> 신규 파일 작성");
		setFilename();	
		
		dir = new File(path);	//디렉토리 객체 생성
		if(!dir.exists()) {		//해당 디렉토리가 존재하지 않는 경우
			dir.mkdir();		//디렉토리 생성
		}
		
		bw = new BufferedWriter(new FileWriter(path + filename));
		System.out.println("   저장할 내용을 입력해 주세요.(종료는 /q)");
		while(true) {
			input = reader.readLine();	//한 줄 입력받기
			
			if(input == null || input.equalsIgnoreCase("/q")) {
				break;			//종료하기
			}
			bw.write(input);	//파일에 쓰기	
			bw.newLine();		//줄 바꾸기
		}//END while
		System.out.println(">> 파일 작성 완료");
		bw.close();
	}//END newFile()

	public void openFile() throws IOException {
		System.out.println(">> 파일 열기 ");
		setFilename();	
		
		file = new File(path + filename);	//파일 객체 생성
		if(!file.exists()) {		//해당 파일이 존재하지 않는 경우
			System.out.println(">> 해당 경로에 파일이 존재하지 않습니다.");
			return;					//안내 메시지 출력 후 중단
		}
		
		br = new BufferedReader(new FileReader(file));
		System.out.println(">> 파일 내용");
		while(true) {
			input = br.readLine();		//한 줄 읽어오기
			if(input == null) {
				break;			//종료하기
			}
			System.out.println(input);	//화면에 표시
		}//END while
		System.out.println(">> 1.내용추가   2.내용변경   3.이름변경   4.종료");
		System.out.println(">> 선택 : ");
		br.close();
	}//END openFile()

	public void copyFile() throws IOException {
		System.out.println(">> 파일을 복사합니다.");
		System.out.print(">> 원본");
		setFilename();
		file = new File(path + filename);	//파일 객체 생성
		if(!file.exists()) {	   //원본 파일이 존재하지 않는 경우
			System.out.println(">> 원본 파일이 존재하지 않습니다.");
			return;				   //안내 메시지 출력 후 중단
		}
		
		System.out.print(">> 사본");
		setFilename();
		dir = new File(path);	//디렉토리 객체 생성
		if(!dir.exists()) {		//사본 디렉토리가 존재하지 않는 경우
			dir.mkdir(); 		//디렉토리 생성
		}	
		
		br = new BufferedReader(new FileReader(file));//원본 파일 
		bw = new BufferedWriter(new FileWriter(path + filename));//사본 파일
		while( (input = br.readLine()) != null) {
			bw.write(input);
			bw.newLine();
		}
		System.out.println(">> 파일 복사 완료");
		bw.close();
		br.close();
	}//END copyFile()
	
	public void deleteFile() throws IOException {
		System.out.println(">> 파일을 삭제합니다.");
		System.out.print(">> 삭제");
		setFilename();
		
		file = new File(path + filename);	//파일 객체 생성
		if(!file.exists()) {	   //원본 파일이 존재하지 않는 경우
			System.out.println(">> 삭제할 파일이 존재하지 않습니다.");
			return;				   //안내 메시지 출력 후 중단
		}
		
		file.delete();	//파일 삭제
		System.out.println(">> 파일 삭제 완료");
	}//END deleteFile()
	
	public void list() {
		File src = new File("src");
		File[] files = src.listFiles();
		int dirCnt = 2;
		int fileCnt = 0;
		int fileSize = 0;

		SimpleDateFormat dateFmt 
			= new SimpleDateFormat("yyyy-MM-dd a hh:mm \t");
		
		System.out.println(src.getAbsolutePath() + " 디렉터리");				
		System.out.println();
		System.out.println(dateFmt.format(src.lastModified()) +"<DIR>\t   .");
		System.out.println(dateFmt.format(src.lastModified()) +"<DIR>\t   ..");						
		for (File file : files) {
			System.out.print(dateFmt.format(file.lastModified()));
			
			if(file.isDirectory()) {
				dirCnt++;
				System.out.printf("%-9s", "<DIR>\t");
			}
			
			if(file.isFile()) {
				fileCnt++;
				fileSize += file.length();
				System.out.printf("%10d ", file.length());
			}
			System.out.println(file.getName());
		}//END for
		System.out.printf("%15d개 파일 %15d 바이트\n ", fileCnt, fileSize);
		System.out.printf("%14d개 디렉터리 %,d 바이트 남음\n ", 
						  dirCnt,  src.getFreeSpace());
	}//END  list()
	
	public void setFilename() throws IOException {	
		System.out.print("   파일명 : ");
		filename = reader.readLine();
		
		System.out.print("   경로명(미입력 시 현재 디렉토리) : ");
		path = reader.readLine();
		if(path == null || path.trim().equals("")) {
			path = "." + File.separator;
		} else {
			path += File.separator;
		}
	}//END setFilename()

	public static void main(String[] args) throws IOException {
		new FileManager().menu();
	}//END main()
}//END class











