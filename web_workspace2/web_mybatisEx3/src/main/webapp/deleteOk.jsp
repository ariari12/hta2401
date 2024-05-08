<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// deleteOk.jsp
	String dn = request.getParameter("deptno");

	try {
		if (dn != null) {
			int deptno = Integer.parseInt(dn);
			DeptDAO dao = new DeptDAO();
			System.out.println("deptno: " + deptno);
			dao.deleteOne(deptno);
		}
		
		response.sendRedirect("list.jsp");
	} catch (IllegalArgumentException e) {
%>
		<script type="text/javascript">
			alert("유효한 값이 아닙니다.");
			document.location.href = "list.jsp";
		</script>
<%
	}
%>