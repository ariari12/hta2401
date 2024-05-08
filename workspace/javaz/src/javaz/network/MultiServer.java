package javaz.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MultiServer {
	private static final int PORT = 5000;
	private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
	private static final String TIME_PTN = "[a hh:mm:ss] ";
	
	private Socket clientSocket;
	private static Map<String, PrintWriter> clientMap;
			 // 닉네임,   출력스트림
	
	public MultiServer() {
		// 1. 서버 소켓 생성
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			// 2. clientMap 객체 생성 및 동기화
			//    Collenctions의 synchronizedMap 메서드 이용하여 동기화 처리
			clientMap = Collections.synchronizedMap(new HashMap<>());
			
			System.out.println(timeDateStamp(DATE_PTN));
			
			// 3. 클라이언트 접속 무한 대기
			while (true) {
				System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 요청 대기 중...");
				// 4. 클라이언트 접속 승인
				clientSocket = serverSocket.accept();
				
				System.out.println(timeDateStamp(TIME_PTN) + " 클라이언트 접속!");
				System.out.println("> 접속 IP \t: " + clientSocket.getInetAddress());
				System.out.println("> 접속 PORT\t: " + clientSocket.getLocalPort());
				System.out.println("> 원격 PORT\t: " + clientSocket.getPort());
				System.out.println(clientMap);
				
				// 5. 클라이언트 처리를 스레드로 시작 시키기
				new ChatServerThread().start();
			}
			
		}catch (BindException e) {
			System.err.println(">> 서버가 이미 가동 중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 6. 클라이언트를 처리할 스레드 클래스
	class ChatServerThread extends Thread {
		// 7. 각 클라이언트에서 사용할 입출력 스트림들을 필드로 선언
		private BufferedReader br;
		private PrintWriter pw;
		private String origin, nickname, msg;
		
		ChatServerThread() {
			try {
				// 8. 입력 스트림 객체 생성
				br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				// 9. 입력 스트림을 읽어서 닉네임과 메시지 저장	
				origin = br.readLine().trim();
				nickname = origin.split("#")[0];
				msg = origin.substring(nickname.length() + 1);
				
				// 10. 출력 스트림으로 객체 생성
				pw = new PrintWriter(clientSocket.getOutputStream(), true);
				
				// 11. 출력 스트림으로 접속 성공 메시지 쓰기
				pw.println(timeDateStamp(DATE_PTN));
				pw.println(timeDateStamp(TIME_PTN) + " 서버 접속 성공!");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 12. 스레드로 처리할 내용
		@Override
		public void run() {
			// 12.1 닉네임과 출력 스트림을 clientMap에 저장
			clientMap.put(nickname, pw);
			// 12.2 최초 입장 메시지를 전체에게 브로드 캐스팅
			broadcast(nickname + "님이 들어오셨습니다.");
			// 12.3 클라이언트가 퇴장 전까지 입력 스트림을 읽어서(입력스트림이 널인지? 확인)
			//      전체에게 브로드 캐스팅
			try {
				while (br != null) {
					msg = br.readLine().trim();
					// 12.4 클라이언트가 퇴장한 경우
					//      퇴장 메시지를 전체에서 브로드 캐스팅한 후
					//      해당 클라이언트를 clientMap에서 삭제
					if (msg != null && !msg.equals("-1")) {
						broadcast(nickname + " > " + msg);
					} else {
						broadcast(nickname + "님이 나가셨습니다.");
						clientMap.remove(nickname);
						System.err.println("> " + nickname + " 퇴장");
						return;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pw != null) { pw.close(); }
					if (br != null) { br.close(); }
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		}
	}
	
	public static boolean isDuplicate(String nickname) {
		if (clientMap != null) {
			return Stream.of(clientMap)
						 .anyMatch(map -> map.containsKey(nickname));
		}
		return false;
	}

	public void broadcast(String msg) {
		// 모든 사용자에게 시간과 메시지 전송
		clientMap.forEach((user,pw) -> pw.println(timeDateStamp(TIME_PTN) + msg));
	}
	
	public String timeDateStamp(String ptn) {
		return new SimpleDateFormat(ptn).format(new Date());
	}
	
	public static void main(String[] args) {
		new MultiServer();
	}
}
