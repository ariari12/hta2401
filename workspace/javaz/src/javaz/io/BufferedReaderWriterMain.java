package javaz.io;

import java.io.*;

public class BufferedReaderWriterMain {
	public static void main(String[] args) {
		String filename = ".classpath";
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
//			int input = 0;
			String input = "";
//			while((input = br.read()) != -1) {
			while((input = br.readLine()) != null) {
//				System.out.print((char)input);
					System.out.println(input);
			}
				
			br.close();
			fr.close();
			
			filename = "buffered.txt";
			
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw, 5);
			
			for (int i = '1'; i <= '9'; i++) {
				bw.write(i);
			}
			
			System.out.println("파일 쓰기 완료!");
			
			bw.flush();
			
			bw.newLine(); // 라인 추가
			bw.append("appended 덧붙이기");
			
			bw.close();
			fw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
