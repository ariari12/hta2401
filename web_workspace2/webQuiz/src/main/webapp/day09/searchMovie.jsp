<%@page import="vo.MovieVO"%>
<%@page import="dao.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// searchMovie.jsp
	String name =  request.getParameter("name");

	if(name != null) {
		MovieDAO dao = new MovieDAO();
		MovieVO vo = dao.selectOne(name);
		
		if(vo != null) {
			out.println(vo.getSrc());
		}
	}
%>