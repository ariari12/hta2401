package javaz.io;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 발생한 예외의 날짜와 시간 정보 및
// 발생한 예외 객체 클래스 이름과 예외 메시지를
// 파일로 저장
// - 파일명은 파일 생성 시점의 long 타입의 시간정보.log로 저장

class ExceptionLog {
	public static void writeLog(Exception e, Date now) {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss ");
		String path = "C:\\dev\\log\\";
		String filename = System.currentTimeMillis() + ".log";
		
		System.out.println("-- 예외 발생 로그 기록 중 ---");
		
		try (FileWriter fw = new FileWriter(path + filename)) {
			System.out.println(dateFmt.format(now) + e);
			
			fw.write(dateFmt.format(now) + e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("-- 예외 발생 로그 기록 완료 ---");
	}
}

public class ExceptionLogSaveFile {
	public static void main(String[] args) {
		try {
//			throw new NumberFormatException("숫자 포맷 예외?!");
//			throw new ClassNotFoundException("클래스 못찾아 예외...");
			throw new Exception("이것이 예외!!");
		} 
		catch (Exception e) {
			ExceptionLog.writeLog(e, new Date());
		}
	}

}
