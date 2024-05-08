package javaz.api;

// Math 클래스
// - 수학 계산에 필요한 정적 필드 및 메서드로 구성
public class MathMain {

	public static void main(String[] args) {
//		Math m = new Math(); // X
		
		System.out.println(Math.ceil(3.4)); //올림
		System.out.println(Math.ceil(3.5));
		System.out.println();
		
		System.out.println(Math.floor(3.4)); // 버림
		System.out.println(Math.floor(3.5));
		System.out.println();
		
		System.out.println(Math.round(3.4)); // 반올림
		System.out.println(Math.round(3.5));
		System.out.println();
		
		double nansu = Math.random();  // 난수 추출
		System.out.println(nansu);
		
		// 1. nansu 변수의 값을 다음 변수에 저장
		int zeroOneTwo = (int) nansu;
		System.out.println(zeroOneTwo);
		
		// 2.  nansu 변수의 값이 0, 1, 2 중 하나가 되도록 하여 저장
		zeroOneTwo = (int) (nansu * 3);
		System.out.println(zeroOneTwo);
		
		// 3. nansu 변수의 값이 1, 2, 3 중 하나가 되도록 하여 저장
		zeroOneTwo = (int) (nansu * 3) + 1;
		System.out.println(zeroOneTwo);
		
		// 4. nansu 변수의 값이 10, 11, 12 중 하나가 되도록 하여 저장
		zeroOneTwo = (int) (nansu * 3) + 10;
		System.out.println(zeroOneTwo);
		
	}

}
