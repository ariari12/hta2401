<%@page import="dao.EmpDAO"%>
<%@page import="vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String empnum = request.getParameter("empno");

	if(empnum != null) {
		try {
			int empno = Integer.parseInt(empnum);
			
			EmpDAO dao = new EmpDAO();
			EmpVO vo = dao.selectOne(empno);
			
			if (vo != null) {
				out.println("<h6>사원번호: " + empno + "</h6>");
				out.println("<h6>사원명: " + vo.getEname() + "</h6>");
				out.println("<h6>급여: " + vo.getSal() + "</h6>");
				out.println("<h6>직무: " + vo.getJob() + "</h6>");
			} else {
				out.println("<h5>존재하지 않는 사원입니다.</h5>");
				out.println("<h6>사원번호</h6>");
				out.println("<h6>사원명</h6>");
				out.println("<h6>급여</h6>");
				out.println("<h6>직무</h6>");
			}
			
		} catch(NumberFormatException e) {
			out.println("<h5>숫자를 입력해주세요</h5>");
			out.println("<h6>사원번호</h6>");
			out.println("<h6>사원명</h6>");
			out.println("<h6>급여</h6>");
			out.println("<h6>직무</h6>");
		}
	}
%>