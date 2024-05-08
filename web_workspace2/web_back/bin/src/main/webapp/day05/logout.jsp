<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이 부분은 사용자에게 무언가 보여줄 내용이 없음
	
	// HTML 부분을 작성하지 않아도 된다.
	
	session.invalidate(); // 무효화
	
	// 로그인 페이지로 리다이렉트
	response.sendRedirect("login.jsp");

%>