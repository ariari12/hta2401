<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// deleteOk.jsp
	String b = request.getParameter("bno");

	try {
		if (b != null) {
			
			int bno = Integer.parseInt(b);
			BoardDAO dao = new BoardDAO();
			dao.deleteOne(bno);
			
			response.sendRedirect("list.jsp");
			
		} else { throw new IllegalArgumentException(); }
	} catch (IllegalArgumentException e) {
%>
		<form action=""></form>
		<script type="text/javascript">
			alert("유효하지 않은 요청입니다.");
			document.frm.action = "list.jsp";
			document.frm.submit();
		</script>
<%
	}
%>