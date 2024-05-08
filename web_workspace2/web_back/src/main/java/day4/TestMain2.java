package day4;

import dao.MemberDAO;
import vo.MemberVO;

public class TestMain2 {
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.searchUser("aaa", "bbb"); 
		System.out.println("vo: " + vo);
		System.out.println("name: " + vo.getName());
	}
}
