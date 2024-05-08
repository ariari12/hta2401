<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// modifyOk.jsp
	String dn = request.getParameter("deptno");
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	System.out.println("======== modifyOk.jsp ========");
	System.out.println("deptno: " + dn);
	System.out.println("dname: " + dname);
	System.out.println("loc: " + loc);
	
	try {
		if(dn != null && dname != null && loc != null) {
			int deptno = Integer.parseInt(dn);
			DeptDAO dao = new DeptDAO();
			DeptVO vo = new DeptVO(deptno, dname, loc);
			System.out.println("vo: " + vo);
			dao.updateOne(vo);
		}
		
		response.sendRedirect("list.jsp");

	} catch(IllegalArgumentException e) {
	%>
	<script type="text/javascript">
		alert("유효한 값이 아닙니다.");
		document.location.href = "list.jsp";
	</script>
<%
	}
%>