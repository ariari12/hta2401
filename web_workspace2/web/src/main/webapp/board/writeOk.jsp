<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// writeOk.jsp
	
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	String ip = request.getRemoteAddr();
	
	// 널체크 필요하나 지금은 생략
	out.println("writer: " + writer);
	out.println("title: " + title);
	out.println("contents: " + contents);
	out.println("ip: " + ip);
	
	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setContents(contents);
	vo.setIp(ip);
	
	out.println("<br>");
	out.println("vo: " + vo);
	// vo.setIp(request.getRemoteAddr());
	
	BoardDAO dao = new BoardDAO();
	dao.addOne(vo);
	
	response.sendRedirect("list.jsp");
%>