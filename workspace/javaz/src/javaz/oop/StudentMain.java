package javaz.oop;

// 1. Person 클래스를 상속받는 Student 클래스 정의
class Student extends Person {
	// 2. 문자열을 저장하는 id를 클래스를 외부에서 접근 불가하도록 선언
	private String id;
	
	// 3. id와 name을 매개변수로 전달받아 초기화하는 생성자 작성
	//	  단, name은 부모 클래스의 생성자에 전달
	//	  필요 시 부모 클래스에 생성자 추가
	public Student(String id, String name) {
//		super(name, null); // Person의 기존 생성자를 사용하기 위해 tel에 null값 전달
		super(name); // Person에서 매개변수 두 개 받는 새 생성자 작성
		this.id = id;
	}
	
	// 4. Student 클래스의 모든 필드의 값을 매개변수로 전달받아 초기화하는 생성자 추가
	public Student(String id, String name, String tel) {
		super(name, tel);
		this.id = id;
	}
	
	// 5. id 필드의 getter/setter 추가
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	// 6. Student 객체를 다음과 같이 문자열로 반환하는 메서드 오버라이딩
	//	  학생 정보 : id = ~~, 이름 = ~~, 전화번호 = ~~
	@Override
	public String toString() {
		return "학생 정보 : id = " + id + ", 이름 = " + getName() + ", 전화번호 = " + getTel();
	}
	
	
} // END class Student

public class StudentMain {

	public static void main(String[] args) {
		Student student1 = new Student("1122001", "Laa");
		Student student2 = new Student("1122002", "San", "010-1234-5678");
		System.out.println(student1);
		System.out.println(student2);
	}

}
