package javaz.oop;

public class Singleton {
	// 1. 정수 타입의 공유 필드 i 선언
	//    단, 외부 클래스에서 접근할 수 없도록 정의
	private static int i;
	
	//    ┏> 클래스명(=생성자??)을 의미함, 꼭 타입이 Singleton이진 않음(클래스명이 타입이 됨??)
	// 2. Singleton 타입의 공유 필드 instance 선언
	//    단, 외부 클래스에서 접근할 수 없도록 정의
	private static Singleton instance; // 초기화 안 했으므로 기본값인 null을 가짐
	
	// 3. 기본생성자를 외부 클래스에 접근할 수 없도록 정의
	private Singleton() { }
	
	// 4. instance 필드를 반환하는 공유 메서드 getInstance 정의
	//    (instance가 null인 경우에는 객체를 생성하여 반환하고, 그렇지 않은 경우에는 그냥 반환하도록 처리) 
	//    단, 매개변수 및 접근 제한없음
	public static Singleton getInstance() {
		if (instance ==  null) { 
			instance = new Singleton(); 
			}
		return instance;
	}
}
