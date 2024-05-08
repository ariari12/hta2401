<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerOk.jsp</title>
</head>
<body>
	<%
		// 1. 파라미터 값 가져오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String motive = request.getParameter("motive");
		
		// 2. 출력
		out.println("<h3> id: " + id + "</h3>");
		out.println("<h3> pw: " + pw + "</h3>");
		out.println("<h3> name: " + name + "</h3>");
		out.println("<h3> gender: " + gender + "</h3>");
		out.println("<h3> motive: " + motive + "</h3>");
		
		
		System.out.println("id: " + id);
		System.out.println("pw: " + pw);
		System.out.println("name: " + name);
		System.out.println("gender: " + gender);
		System.out.println("motive: " + motive);
		
		
		// 3. MemberDAO
		MemberDAO dao = new MemberDAO();
	
		// 4. 파라미터값을 가지고 vo객체를 생성
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setGender(gender);
		vo.setMotive(motive);
		
		// 0은 이후 시퀀스 값으로 대체되어 무시됨
		vo = new MemberVO(0, id, pw, name, gender, motive);
		
		// 5. db에 입력
		dao.addMember(vo);
		
		dao.close();
		
		// 6. login.jsp로 리다이렉트
		response.sendRedirect("login.jsp");
	%>
</body>
</html>