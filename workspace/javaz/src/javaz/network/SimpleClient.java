package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleClient {
	private static final int SERVERPORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private BufferedReader br;		// 서버의 메시지 읽기
	private PrintWriter pw;			// 서버에게 메시지 보내기
	
	public SimpleClient() {
		// 클라이언트 소켓 생성
		System.out.println("***** THIS IS a CLIENT *****");
		String nickname = "bbb";
		String msg = "";
		
		try (Socket clientSocket = new Socket("localhost", SERVERPORT)) {
			// 메시지 입력받기
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("전송할 메시지를 입력해주세요: ");
			msg = br.readLine().trim();
			br.close();
			
			// 서버로 메시지를 보낼 스트림 생성
			pw = new PrintWriter(clientSocket.getOutputStream(), true);
			
			// 서버로 닉네임 보내기
			pw.println(nickname + "#" + msg);
			
			// 3. 서버에서 보낸 메시지를 읽어올 스트림 생성
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// 4. 서버에서 보낸 메시지 출력
			System.out.println(timeDateStamp(TIME_PTN) + br.readLine().trim());
			
		} catch (ConnectException e) {
			System.err.println(">> 서버와 연결되지 않았습니다.");
			System.err.println(">> 서버 가동 상태를 확인해 주세요.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 5. finally 구문을 추가하여 스트림 객체들 닫기
			if (pw != null) { pw.close(); }
			try {
				if (br != null) { br.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 날짜 또는 시간 패턴을 매개변수로 받아서
	// 지정된 패턴의 문자열을 반환하는 timeDatestamp 메서드
	public String timeDateStamp(String ptn) {
		return new SimpleDateFormat(ptn).format(new Date());
	}
	
	public static void main(String[] args) {
		new SimpleClient();
	}

}
