package javaz.oop;

public class SingletonMain {

	public static void main(String[] args) {
		// Singleton 클래스의 객체 s1 생성
		// 기본 생성자가 private이므로 호출 불가함
//		Singleton s1 = new Singleton(); // X
//		Singleton s11 = new Singleton();
		
		//Singleton 클래스의 인스턴스 s2 반환받기
		Singleton s2 = Singleton.getInstance();
		Singleton s3 = Singleton.getInstance();
		
		// 동일한 주소(=해시코드)가 찍힘 = 객체 하나를 공유하여 사용
		System.out.println(s2);
		System.out.println(s3);
		// 생성자 접근제어자 푼 후 new로 생성한 인스턴스들은 주소가 각자 다름
//		System.out.println(s1);
//		System.out.println(s11);
	}

}
