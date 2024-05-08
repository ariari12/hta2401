package javaz.oop.rpg;

interface Character {
	public static final int HEART = 5;
//	int POWER;  일반 필드 선언 불가
	int POWER = 5; // final 키워드가 없어도 상수
}

interface Move {
	public abstract void move(int x, int y);
}

interface Attack {
	void attack(); // abstract 키워드가 없어도 추상 메서드
}

interface Skill extends Move, Attack { // 인터페이스는 다중 상속 가능
	default void helpSkill() { // default 키워드를 사용하면 구현부 선언 가능
		System.out.printf("%-10s 공격과 이동 중에서 선택 가능합니다.\n", "[스킬 도움말]");
	}
}

interface Item {
	static final int POTION = 100;
	static int SWORD = 200;
	
	static void helpItem() {
		System.out.printf("%-10s 골드로 아이템 구매 가능\n", "[아이템 도움말]");
	}
}
