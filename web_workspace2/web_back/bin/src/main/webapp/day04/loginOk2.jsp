<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginOk2.jsp</title>
</head>
<body>
	<%
		/* 요청객체로부터 파라미터값 가져오기 */
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.searchUser(id, pw);
		
		out.println("<h2> 로그인 사용자명: " + vo.getName() + " </h2>");
		
	%>
</body>
</html>