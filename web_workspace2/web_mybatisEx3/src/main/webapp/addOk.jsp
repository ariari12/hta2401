<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// addOk.jsp
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	if(dname != null && loc != null) {
		DeptVO vo = new DeptVO();
		vo.setDname(dname);
		vo.setLoc(loc);
		
		DeptDAO dao = new DeptDAO();
		dao.insertOne(vo);
	}
	
	response.sendRedirect("list.jsp");
%>