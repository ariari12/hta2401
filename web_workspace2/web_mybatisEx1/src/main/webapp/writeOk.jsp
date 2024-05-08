<%@page import="kr.co.jhta.dao.DeptDAO"%>
<%@page import="kr.co.jhta.vo.DeptVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// writeOk.jsp
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	if (dname != null && loc != null) {
		DeptVO vo = new DeptVO();
		vo.setDname(dname);
		vo.setLoc(loc);
		DeptDAO dao = new DeptDAO();
		dao.addOne(vo);
	}
	
	// 리다이렉트
	response.sendRedirect("list.jsp");
%>