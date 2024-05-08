package javaz.oop.rpg;

public abstract class Player implements Skill, Item {
	// 모든 플레이어들이 공통으로 가질 속성 ------------
	private int x; 		 // x좌표
	private int y; 		 // y좌표
	private int heart;	 // 하트
	private int power;	 // 파워
	private int level;	 // 레벨
	private int gold;	 // 소지금
	private int mode;	 // 난이도(1~5)
	private String name; // 플레이어 이름
	
	// 플레이어의 이름을 매개변수로 넘겨받아 초기화하는 생성자
		// 모든 플레이어의 하트와 파워는 5로 초기화
	public Player(String name) {
		this.name = name;
		heart = Character.HEART;
		power = Character.POWER;
		level = 1;
		gold = 500;
		mode = 3;
		
		
		System.out.println("=== MY RPG Character ===");
		System.out.printf("%-11s: %s\n", "캐릭터", name);
		System.out.printf("%-12s: %d\n", "LEVEL", level);
		
		System.out.printf("%-12s: ", "HEART");
		for (int i = 1; i <= heart; i++) {
			System.out.print("♥");
		}
		System.out.println();
		
		System.out.printf("%-12s: ", "POWER");
		for (int i = 1; i <= power; i++) {
			System.out.print("○");
		}
		System.out.println();
		
		System.out.println();
	}
	
	// 추상 메서드 - 이동하기
	public abstract void move(int x, int y);
	
	// 인스턴스 메서드 - 하트 설정하기
	public void setHeart(int increase) {
		heart += increase;
		if (increase != 0) {
			System.out.printf("%-12s HEART %3d %-6s|   ",
					"["+ name + "]", increase, (increase > 0 ? "증가" : "감소"));
			for (int i = 1; i <= heart; i++) {
				System.out.print("♥");
			}
			System.out.println();
		}
	}
	
	// 인스턴스 메서드 - 파워 설정하기
	public void setPower(int increase) {
		power += increase;
		if (increase != 0) {
			System.out.printf("%-12s POWER %3d %-6s|   ",
					"["+ name + "]", increase, (increase > 0 ? "증가" : "감소"));
			for (int i = 1; i <= power; i++) {
				System.out.print("○");
			}
			System.out.println();
		}
	}
	
	// 각 플레이어 객체를 문자열로 반환하는 메서드를 이용하여
	// 이동 좌표를 표시하도록 처리
	public String toString() {
		return String.format("%-12s 이동!!!  x좌표: %d, y좌표: %d", "[" + name + "]", x, y);
	}
	
	// 필요에 따라 추가 기능 정의 ---------------------------
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public String getName() { 
		return name;
	}

	public void setLevel(int increase) {
		level += increase;
		if (increase != 0) {
			System.out.printf("%-12s LEVEL %3d %-6s|   %d\n",
					"["+ name + "]", increase, (increase > 0 ? "증가" : "감소"), level);
		}
	}

}
