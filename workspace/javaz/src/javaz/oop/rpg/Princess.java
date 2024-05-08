package javaz.oop.rpg;

public class Princess extends Player {
	public Princess(String name) {
		super(name);
	}

	// 부모 클래스의 추상 메서드 오버라이딩
	@Override
	public void move(int x, int y) {
		setX(x);
		setY(y);
		System.out.println(this);
		setHeart(1);
		setPower(1);
	}
		
	// 프린세스 전용 메서드 작성
	public void heal() {
		System.out.printf("%-12s heal~~~~~~~~\n", "[" + getName() + "]");
		setPower(-1);
		setHeart(1);
	}

	@Override
	public void attack() {
		System.out.printf("%-12s 공격!!!!!\n", "[" + getName() + "]");
		setPower(-1);
		setHeart(-1);
	}

	@Override
	public void helpSkill() {
		System.out.printf("%-11s치료 스킬 사용 가능\n", "[공주 도움말]");
	}
}
