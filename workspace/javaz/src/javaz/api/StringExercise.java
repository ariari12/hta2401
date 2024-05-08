package javaz.api;

public class StringExercise {
	// 1. "주민등록번호|출생년도|월|일|성별"을 저장하는 공유 상수
	//     BIRTHINFO_HEADLINE
	public static final String BIRTHINFO_HEADLINE = "주민등록번호|출생년도|월|일|성별";
	
	// 2. 주민등록번호를 배열로 넘겨받는 인스턴스 메서드 printBirthInfo
	//    - 넘겨받은 배열의 데이터를 출생년도, 월, 일로 분리하고
	//      성별을 판별하여 화면에 표시
	public void printBirthInfo(String[] idNos) {
		for (String idNo : idNos) {
			// 생년월일과 뒷자리 분할
			String birthInfo = idNo.split("-")[0];
			String identify = idNo.split("-")[1];
			// 출생년도 / 월 / 일로 분할
			String year = birthInfo.substring(0, 2);
			String month = birthInfo.substring(2, 4);
			String date = birthInfo.substring(4, 6);
			// 성별코드 확인
			String genderCode = identify.substring(0,1);
			
			// 출생년도 판별(19nn / 20nn)
			if (genderCode.equals("1") || genderCode.equals("2")) {
				year = "19" + year;
			} else if (genderCode.equals("3") || genderCode.equals("4")) {
				year = "20" + year;
			}
			
			String gender = "남성";
			
			// 성별 판별
			if (genderCode.equals("2") || genderCode.equals("4")) {
				gender = "여성";
			}
			
			// 출력
			System.out.print(idNo + "|" + year + "|" + month + "|" + date + "|" 
							+ gender);
			System.out.println();
		}
		
		System.out.println();
		
		for (String idNo : idNos) {
			String year = idNo.substring(0, 2);
			String month = idNo.substring(2, 4);
			String date = idNo.substring(4, 6);
			char gender = idNo.charAt(idNo.indexOf("-") + 1);
			
			if (gender <= '2') { year = "19" + year; }
			else			   { year = "20" + year; }
			
			System.out.println(idNo + "|" + year + "|" + month + "|" + date + "|"
							   + (gender % 2 == 0 ? "여성" : "남성"));
		}
	}
	
	// 3. 파일명을 매개변수로 받아서 파일이름과 확장자로 분리하여
	//    출력하는 공유 메서드 printFileInfo
	public static void printFileInfo(String fileName) {
		int seperatorIndex = fileName.lastIndexOf(".");
		
		System.out.println("파일 정보 : " + fileName);
		System.out.println("파일명 : " + fileName.substring(0, seperatorIndex));
		System.out.println("확장자 : " + fileName.substring(seperatorIndex + 1));
		
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		System.out.println("파일명 : " + name);
		System.out.println("확장자 : " + ext);
		System.out.println();
	}

	public static void main(String[] args) {
		// 4. 명령행 매개변수로 주민등록번호 4개를 입력받아
		//    2번의 메서드 호출
		StringExercise se = new StringExercise();
		se.printBirthInfo(args);
		
		System.out.println();
		
		// 5. 다음 변수들을 이용하여 3번의 메서드 호출
		String file1 = "my.music.best.one.mp3";
		String file2 = "long_long_file_two.mpeg";
		
		printFileInfo(file1);
		printFileInfo(file2);

	}

}
