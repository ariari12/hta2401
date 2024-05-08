package javaz.test.member;

import java.util.HashMap;
import java.util.Map;

public class MemberLogin {
	private static Map<String, String> memberMap;

	public static Map<String, String> getMemberMap() {
		return memberMap;
	}

	public static void setMemberMap(Map<String, String> memberMap) {
		MemberLogin.memberMap = memberMap;
	}

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
	}

	// 로그인 체크
	public boolean loginChk(String id, String pw) {
		if (memberMap.containsKey(id) && pw.equals(memberMap.get(id))) {
			return true;
		} else {
			return false;
		}
	}

	// 로그아웃
}
