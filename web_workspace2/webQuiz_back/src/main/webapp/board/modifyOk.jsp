<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// modifyOk.jsp
	String b = request.getParameter("bno");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	
	try {
		if (b != null && writer != null && title != null && contents != null) {
			
			System.out.println("================ modifyOk.jsp ================");
			System.out.println("bno: " + b);
			System.out.println("writer: " + writer);
			System.out.println("title: " + title);
			System.out.println("contents: " + contents);
			
			int bno = Integer.parseInt(b);
			BoardDAO dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
			
			if (vo == null) { throw new IllegalArgumentException(); }
			
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContents(contents);
			
			dao = new BoardDAO();
			dao.updateOne(vo);
			
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