package javaz.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDirExercise {	
	public static void main(String[] args) {
		while (true) {
			System.out.print("디렉터리 정보가 필요한 경로를 입력해주세요(종료는 \\q): ");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String path = "";
			
			try {
				path = br.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (path.equals("\\q")) {
				System.out.println("작업을 종료합니다..");
				return;
			}
			
//			if (path.isEmpty()) {
//				path = ".";
//			}
			
			File file = new File(path);
			File[] fileList = file.listFiles();
			SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
			String fullPath = file.getAbsolutePath();
			String displayPath = path.equals(".") ? 
					fullPath.substring(0, fullPath.lastIndexOf(File.separator)) :
					fullPath;
			
			if (fileList == null) {
				System.out.println("존재하지 않는 경로입니다.");
				System.out.println("============================");
				continue;
			}
			
			System.out.println(" " + displayPath + " 디렉터리");
			System.out.println();
			
			int cntFile = 0;
			int cntDir = 2;	// 기본값 = 현재 디렉터리 + 부모 디렉터리
			long totalFileSize = 0L;
			
			
			//현재 디렉터리, 부모 디렉터리 출력
			System.out.println(dateFmt.format(file.lastModified()) 
					+ "\t<DIR>\t.");
			System.out.println(dateFmt.format(file.lastModified())
					+ "\t<DIR>\t..");
			
			for (File f : fileList) {
				String dir = "";
				String size = "";
				String name = f.getName();
				
				if (f.isDirectory()) {
					dir = "<DIR>";
					cntDir++;
				}
				
				if (f.isFile()) {
					size = f.length() + "";
					cntFile++;
					totalFileSize += f.length();
				}
				
				System.out.print(dateFmt.format(new Date(f.lastModified())));
				System.out.printf("\t%-9s", dir);
				System.out.printf("\t%10s", size);
				System.out.println("\t" + name);
			}
			System.out.printf("%15d개 파일%,15d 바이트\n", cntFile, totalFileSize);
			System.out.printf("%15d개 디렉터리 %,d 바이트 남음\n", cntDir, file.getFreeSpace());
			System.out.println();
			System.out.println(file.getAbsolutePath() + "> ");
		}
	}
}
