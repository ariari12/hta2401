<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// idCheckup.jsp
	// 1. 파라미터 값 가져오기
	String id = request.getParameter("id");

	// 2. id가 널이 아니라면
	if (id != null) {
		// 3. dao 객체
		MemberDAO dao = new MemberDAO();
		// 4. dao.getOne(id) ==> vo를 리턴받는다.
		MemberVO vo = dao.getOne(id); 
		
		// 5. vo가 널이라면 존재하지 않는 아이디 : 화면에 true 출력
		if (vo == null) {
			out.println("true");
		} else {
		// 6. vo가 널이 아니라면 존재하는 아이디 : 화면에 false 출력
			out.println("false");
		}
		
	}
%>