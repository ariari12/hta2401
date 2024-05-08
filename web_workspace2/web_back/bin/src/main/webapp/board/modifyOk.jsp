<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// modifyOk.jsp
	String b = request.getParameter("bno");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");
	
	System.out.println("게시글 번호: " + b);
	System.out.println("작성자: " + writer);
	System.out.println("제목: " + title);
	System.out.println("내용: " + contents);
	
	if (b != null) {
		try {
			int bno = Integer.parseInt(b);
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
			// 파라미터값: 새로운 값 지정후 수정
			vo.setBno(bno);
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContents(contents);
			
			System.out.println("vo: " + vo);
			
			dao = new BoardDAO();
			dao.updateOne(vo);
		
		} catch (NumberFormatException e) {
			
		}
	}
	
	response.sendRedirect("list.jsp");
%>