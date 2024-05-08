<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// hello.jsp?txt=aaa
	String txt = request.getParameter("txt");

	if(txt != null) {
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getOne(txt);
		out.println("<h2>" + vo.getName() + "</h2>");
	}
%>