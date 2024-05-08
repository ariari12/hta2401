package javaz.api.thread;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

// 1. Thread 클래스를 상속받는 AutoSavedaemon 클래스
//    1.1 스레드로 처리할 내용
//	      ThreadDaemon 클래스의 autoSave 속성이 true이면
//		  interval 필드의 저장 간격에 따라
//		  파일 자동 저장 메서드 호출
//
//    1.2 파일 자동 저장 처리 메서드 fileAutoSave
//	      "[~~:~~:~~] 파일이 자동 저장 되었습니다."
class AutoSaveDaemon extends Thread {

	@Override
	public void run() {
		while (ThreadDaemon.autoSave) {
			try {
				fileAutoSave(); // sleep 아래에 있을 경우: 최초에 저장 안 됨
				Thread.sleep(ThreadDaemon.interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void fileAutoSave() {
		SimpleDateFormat dateFmt = new SimpleDateFormat("[hh:mm:ss]");
		System.out.println(dateFmt.format(new Date()) + " 파일이 자동 저장되었습니다.");
		
//		LocalTime lt = LocalTime.now();
//		System.out.print("[" + lt.getHour() + ":");
//		System.out.print(lt.getMinute() + ":" + lt.getSecond() + "] ");
//		System.out.println("파일이 자동 저장 되었습니다.");
	}
	
}

public class ThreadDaemon {
	static boolean autoSave;	// 파일 자동 저장 여부
	static int interval;		// 파일 자동 저장 간격 1/1000초	

	public static void main(String[] args) {
		System.out.println(">> 파일 작업 설정");
		
		try {
			System.out.print("   >> 자동 저장(y) : ");
			autoSave = (System.in.read() == 'y');
			System.in.skip(2);
			
			// 자동 저장이 y인 경우
			// 2. AutoSaveDaemon 클래스의 객체를 생성하고
			//    2.1 생성된 객체의 setDaemon 메서드를 true로 설정한 후
			//    2.2 스레드로 시작 시키기
			if (autoSave) {
				System.out.print("   >> 저장 간격(단위 : 초) : ");
				interval = (System.in.read() - '0') * 1000;
				System.in.skip(2);
			}
			
			AutoSaveDaemon asd = new AutoSaveDaemon();
			asd.setDaemon(true);
			
			System.out.println("---------------------------------");
			System.out.println(">> 파일 작업 시작 - 작업 제한 시간 10초");
			asd.start();
			for (int i = 0; i <= 10; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			
			System.out.println(">> 파일 작업 종료");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
