<%@page import="vo.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	// server02.jsp
	// out.println("SCOTT,BLACK,KING");

	// db로부터 사원명을 가져와서 , 구분자 넣어서 화면에 출력
	EmpDAO dao = new EmpDAO();
	ArrayList<EmpVO> list = dao.selectAll();
	
	for (int i = 0; i < list.size(); i++) {
		EmpVO vo = list.get(i);
		out.println(vo.getEname());
		if (i != list.size() - 1) { out.println(","); }
	}
%>