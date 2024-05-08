package javaz.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileCopy {

	public static void main(String[] args) throws IOException {
		String file = "C:\\dev\\world.jpg";
		String separator = System.getProperty("file.separator");
		String path = file.contains(separator) ?
				file.substring(0, file.lastIndexOf(separator) + 1) : ".";
		String fileName = file.substring(file.lastIndexOf(separator) + 1, file.indexOf("."));
		String extension = file.substring(file.indexOf("."));
		
		// 이미지 파일 복사본 만들기
		try (FileInputStream fis = new FileInputStream(file)) {
			int count = 0;
			
			for (String s : new File(path).list()) {
				if (s.contains(fileName) && s.contains(extension)) { count++; }
			}
			
			String outputFileName = count == 1 ? 
							path + fileName + " - 복사본" + extension :
							path + fileName + " - 복사본 (" + count + ")" + extension;
			
			System.out.println(outputFileName);
			
			try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
				int input = 0;
				while ((input = fis.read()) != -1) {
//				System.out.println(input);
					fos.write(input);
				}
			}

		}
	}

}
