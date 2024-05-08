package javaz.util.member;

import java.util.HashMap;
import java.util.Map;

public class MemberLogin {
	public static Map<String, String> memberMap;
	private MemberDAO mdao;

	public MemberLogin() {
		// memberMap 객체를 생성하여
		// admin, 1234
		// aaa, 1111
		// bbb, 2222
		// ccc, 3333을 각각 키와 값으로 memberMap에 저장
		memberMap = new HashMap<String, String>();
		memberMap.put("admin", "1234");
		memberMap.put("aaa", "1111");
		memberMap.put("bbb", "2222");
		memberMap.put("ccc", "3333");
		
		mdao = new MemberDAO();
	}
	
	// 로그인 체크
	public boolean loginChk(String id, String pw) {
//		System.out.println(memberMap);
		if (memberMap.containsKey(id) && memberMap.get(id).equals(pw)) {
			return true;
		}
		
		if (mdao.existChk(id, pw)) {
//			System.out.println(memberMap);
			return true;
		}
		
		return false;
	}
	
	// 로그아웃
	public void logout(String id) {
		memberMap.remove(id);
		System.out.println(">> 로그아웃이 완료되었습니다.");
	}
	
	// 계정 체크 - 탈퇴 후 메인으로 돌아가기 위해
	public boolean memberChk(String id) {
		if (memberMap.get(id) == null) {
			return false;
		}
		
		return true;
	}
	
}
