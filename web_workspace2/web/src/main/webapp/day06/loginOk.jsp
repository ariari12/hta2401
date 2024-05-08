<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDAO dao = new MemberDAO();
	
	MemberVO vo = dao.searchUser(id, pw);
	
	if(vo != null) {
		session.setAttribute("vo", vo);
	}
	
	response.sendRedirect("layout.jsp");
			
%>
