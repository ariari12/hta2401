<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// deleteOk.jsp
	
	String b = request.getParameter("bno");

	if(b != null) {
		try {
			int bno = Integer.parseInt(b);
			BoardDAO dao = new BoardDAO();
			System.out.println("bno: " + bno);
			
			dao.deleteOne(bno);
			
		} catch (NumberFormatException e) {
			
		}
	}
	
	response.sendRedirect("list.jsp");
%>