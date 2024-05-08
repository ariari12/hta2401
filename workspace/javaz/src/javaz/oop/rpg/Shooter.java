package javaz.oop.rpg;

public class Shooter extends Player {
	public Shooter(String name) {
		super(name);
	}

	@Override
	public void move(int x, int y) {
		setX(x);
		setY(y);
		System.out.println(this);
		setHeart(1);
		setPower(1);
	}
	
	public void shoot() {
		System.out.printf("%-12s shoot!!!!!\n", "[" + getName() + "]");
		setPower(-1);
		setHeart(-1);
	}

	@Override
	public void attack() {
		System.out.printf("%-12s 공격!!!!!\n", "[" + getName() + "]");
		setPower(-3);
		setHeart(-1);
	}
}
