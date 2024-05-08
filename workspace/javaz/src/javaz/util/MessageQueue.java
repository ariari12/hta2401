package javaz.util;

import java.util.LinkedList;
import java.util.Queue;

class Message {
	private String command;
	private String from;
	private String content;
	
	public Message(String command, String from, String content) {
		this.command = command; // sms/katalk, alarm
		this.from = from;		// Lee, Han, clock
		this.content = content; // 
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

public class MessageQueue {

	public static void main(String[] args) {
		// 1. Message 객체를 저장하는 Queue 타입의 참조변수 msgQ 생성
		// 2. msgQ에 임의의 Message 객체 3개 저장
		Queue<Message> msgQ = new LinkedList<Message>();
		msgQ.add(new Message("sms", "Kim", "Hi~"));
		msgQ.add(new Message("alarm", "clock", "일어나!!"));
		msgQ.add(new Message("katalk", "Han", "ㅋㅋㅋㅋㅋ"));
		
		System.out.println("도착 메시지 건 수: " + msgQ.size() + "건");
		
		// 3. msgQ의 내용을 화면에 표시
		while (!msgQ.isEmpty()) { // 메시지큐에 메시지가 있지 않다면
			Message msg = msgQ.poll();
			String post = "";

			// 메시지의 command 종류에 따라 안내 문구 처리
			switch (msg.getCommand()) {
			case "sms": post = "님에게서 문자 도착"; break;
			case "katalk": post = "님에게서 카톡 수신"; break;
			case "alarm": post = " 알람 울림"; break;
			}
			
			System.out.println(msg.getFrom() + post);
		}
		
		System.out.println("------------------");
		System.out.println("도착 메시지 건 수: " + msgQ.size() + "건");

	}

}
