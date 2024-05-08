package javaz.api.thread;

import javax.swing.JOptionPane;

// 1. Thread 클래스를 상속받는 Quiz 클래스 정의
//   1.1 값 입력 여부(true/false)를 체크하는 공유 필드 inputFlag 선언
//   1.2 퀴즈 출제 및 정답 확인 작업 스레드
//   1.3 퀴즈 결과 메시지를 매개변수로 받아서 출력하는
//       클래스 메서드 showResult
class Quiz extends Thread {
	public static boolean inputFlag;

	@Override
	public void run() {
		String input = JOptionPane.showInputDialog("자바의 최상위 클래스의 이름은?" 
						+ "\n(제한 시간: " + Countdown.remainTime + "초)");
		String msg = "실행이 취소되었습니다.";
		String answer = "Object";

		do {
			if (input != null) { // 확인 버튼이 눌린 경우
				if (input.trim().isEmpty()) { // 값 X
					input = JOptionPane.showInputDialog(
							"답을 입력해주세요.\n자바의 최상위 클래스의 이름은?" 
									+ "\n(제한 시간: " + Countdown.remainTime + "초)");
				} else { // 값 O
					inputFlag = true;
					msg = input.equals(answer) ? "정답입니다!" : "오답입니다.";
					msg += "\n입력: " + input + "\n정답: " + answer;
					showResult(msg);
				}
			} else { // 취소 또는 X 버튼이 눌린 경우
				inputFlag = true;
				showResult(msg);
			}
		} while (Countdown.remainTime > 0);
	}

	public static void showResult(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		System.exit(0); // 시스템 정상 종료
	}
}

// 2. Runnable 인터페이스를 구현하는 Countdown 클래스 정의
//   2.1 남은 시간을 저장하는 정적 멤버 변수 remainTime을 선언하면서
//       10으로 초기화
//   2.2 카운트 다운 작업 스레드 메서드
class Countdown implements Runnable {
	public static int remainTime = 10;

	@Override
	public void run() {
		for (remainTime = 10; remainTime >= 1; remainTime--) {
			if (Quiz.inputFlag) { return; }

			System.out.println(remainTime + "초");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (remainTime == 0 && !Quiz.inputFlag) {
			System.out.println(" - 제한 시간 종료 -");
			// 결과 출력 메서드 호출
			Quiz.showResult("-제한 시간 종료-\n 정답: 자바의 최상위 클래스는 Object입니다.");
		}
	}

}

public class ThreadQuiz {
	public static void main(String[] args) {
		// 3. Quiz 클래스 객체 quiz 생성
		Quiz quiz = new Quiz();

		// 4. Countdown 클래스 객체를 매개변수로 Thread 타입의
		// 참조변수 c 생성
		Thread c = new Thread(new Countdown());

		// 5. 3과 4의 스레드 시작 시키기
		c.start();
		quiz.start();
	}

}
