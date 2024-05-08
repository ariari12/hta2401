package javaz.oop;

// 이름과 전화번호를 저장하는 클래스
class Person {
	// 이름 필드 name
	// 전화번호 멤버 변수 tel
	// 둘 다 외부에서 접근 불가능하도록 선언
	private String name;
	private String tel;
	
	// 기본생성자
	public Person() {  }

	public Person(String name) {
		this.name = name;
	}

	// 인스턴스의 속성들을 초기화하는 생성자
	public Person(String name, String tel) {
		this.name = name;
		this.tel = tel;
	}

	// 필드의 값을 설정하고 반환하는 setter / getter
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

} // END class Person

public class PersonMain {
	// 1. Person 클래스 타입의 배열 객체를 매개변수로 전달받아 화면에 표시하는 공유 메서드 showList
	public static void showList(Person[] people) {
		System.out.println("======= 연락처 정보 =======");
		System.out.println("이름\t전화번호");
		for (Person person : people) {
			// 설정된 값은 3개인데 배열의 크기는 100일 경우 NullPointerException 예외 발생함
			// 아래와 같이 null일 경우 반복 끝내는 구문 추가, 혹은 아래에서 배열의 크기 자체를 3으로 변경
			if (person == null) { break; }
			System.out.println(person.getName() + "\t" + person.getTel());
		}
	}

	public static void main(String[] args) {
		// 2. 1번에서 필요한 객체를 생성하여 1번 메서드 호출
		int total = 0;
		// Person[] people = new Person[3];
		Person[] people = new Person[100];
		people[total++] = new Person("Kim", "010-1111-2222");
		people[total++] = new Person("Lee", "010-2222-3333");
		people[total++] = new Person("Ann", "010-3333-4444");
		
		// 공유 메서드이므로 참조변수 필요하지 않음
		// PersonMain.showList(people);
		// 같은 클래스 내에서의 공유 메서드이므로 메서드 이름으로만 호출 가능
		showList(people);

	}

}
