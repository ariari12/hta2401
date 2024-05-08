package javaz.oop.rpg;

public class RollPlayingGameMain {

	public static void main(String[] args) {
		Princess princess = new Princess("princess");
		Shooter shooter = new Shooter("shooter");
		
		shooter.move(140, 200);
		// 공격 메서드 호출
		shooter.attack();
		shooter.helpSkill();
		// 슈터 전용 메서드 호출
		shooter.shoot();
		
		System.out.println();
		
		princess.move(50, 50);
		// 공격 메서드 호출
		princess.attack();
//		princess.helpItem();  // 인터페이스의 공유 매서드는 인스턴스 이용해서 접근 불
		Item.helpItem();	  // 이렇게 접근 가능
		princess.helpSkill();
		// 공주 전용 메서드 호출
		princess.heal();
		
		System.out.println();
		
		shooter.setLevel(9);
		shooter.setLevel(-9);
		princess.setLevel(9);
		princess.setLevel(-9);

	}

}
