package javaz.api;

// Exception 클래스를 상속받는 사용자 정의 예외 클래스
public class SamchoException extends Exception {
	// 예외 메시지를 매개변수로 넘겨받아
	// 부모 생성자에게 전달하는 생성자
	public SamchoException(String errMsg) {
		super(errMsg);
	}
}
