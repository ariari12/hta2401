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
	
	if (writer != null && title != null && contents != null) {
	
		System.out.println("================ writeOk.jsp ================");
		System.out.println("writer: " + writer);
		System.out.println("title: " + title);
		System.out.println("contents: " + contents);
		System.out.println("ip: " + ip);
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO(0, writer, title, contents, "now", 0, ip, 1);
		
		dao.addOne(vo);
	}
	
	response.sendRedirect("list.jsp");
%>