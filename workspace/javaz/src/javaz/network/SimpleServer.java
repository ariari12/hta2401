package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

// TCP; Transmission Control Protocol
// - 신뢰성 있게 통신하기 위해 서로 먼저 연결 설정 후
//   데이터 전송
// - 전화와 유사

// UDP; User Datagram Protocol
// - 연결 X
// - 데이터를 고정 패킷으로 분할하여 패킷 앞에 주소를 붙여서 전송
// - 편지와 유사

// Socket
// - TCP를 위한 구조
// - 응용 프로그램끼리 연결을 위한 연결 끝점(end point)
// - port를 이용하여 만들어짐

// ServerSocket 클래스
// - 클라이언트에 대한 연결을 기다리고 수락

// Socket 클래스
// - 서버에 연결 요청

public class SimpleServer {
	private static final int PORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private Socket clientSocket;
	private BufferedReader br;		// 클라이언트의 메시지 읽기
	private PrintWriter pw;			// 클라이언트에게 메시지 보내기
	
	// 기본생성자 - ServerSocket 객체 serverSocket 생성
	public SimpleServer() {
		// 서버 소켓 생성
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println(timeDateStamp(DATE_PTN));
			System.out.println(timeDateStamp(TIME_PTN) + "***** SERVER STARTED *****");
			
			// 클라이언트의 요청 대기
			while (true) {
				System.out.println(timeDateStamp(TIME_PTN) + "클라이언트 요청 대기 중...");
				// 클라이언트 요청 수락
				clientSocket = serverSocket.accept();
				System.out.println(timeDateStamp(TIME_PTN) + "클라이언트 접속!!");
				
				// 클라이언트의 메시지를 읽을 스트림 생성
				br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				// 클라이언트 접속 정보 출력 --------------------------------
				System.out.println("> 접속 IP \t: " + clientSocket.getInetAddress());
				System.out.println("> 접속 PORT\t: " + clientSocket.getLocalPort());
				System.out.println("> 원격 PORT\t: " + clientSocket.getPort());
				
				String origin = br.readLine();
				String[] msgs = origin.split("#");
				String nickname = msgs[0];
				String msg = origin.substring(nickname.length() + 1);
				
				System.out.println("> 클라이언트 닉네임\t: " + nickname);
				System.out.println("> 클라이언트 메시지\t: " + msg);
				System.out.println();
				
				// 1. 클라이언트에게 보낼 메시지 출력 스트림 생성
				pw = new PrintWriter(clientSocket.getOutputStream(), true);
				
				// 2. "~~~님 서버에 접속되었습니다."
				pw.println(nickname + "님 서버에 접속되었습니다.");
				
				System.out.println(timeDateStamp(TIME_PTN) + "데이터 전송 완료!!");
			}
			
		} catch (BindException e) {
			System.err.println(">> 서버가 이미 가동 중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 6. finally 추가해서 스트림 객체들과 클라이언트 소켓 닫기
			if (pw != null) { pw.close(); }
			try {
				if (br != null) { br.close(); }
				if (clientSocket != null) { clientSocket.close(); }
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
		new SimpleServer();
	}

}
