<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jnditest.jsp</title>
</head>
<body>
	<%
		Connection conn = DBConnection.getInstance().getConnection();
		out.println("conn: " + conn);
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO(1, "aaa", "타이틀1", "내용1", "2024-04-28", 1, "192.168.0.21", 1);
		BoardVO vo2 = new BoardVO(2, "bbb", "타이틀2", "내용2", "2024-04-29", 30, "192.168.0.28", 3);
		dao.addOne(vo);
		dao.addOne(vo2);
		
		System.out.println("전체조회");
		ArrayList<BoardVO> list = dao.selectAll();
		for (BoardVO vot : list) {
			System.out.println(vot);
		}
		
		vo = new BoardVO(1, "aaa", "타이틀1수정!!", "내용1", "2024-04-28", 1, "192.168.0.21", 1);
		
		dao.updateOne(vo);
		System.out.println("수정후조회");
		list = dao.selectAll();
		for (BoardVO vot : list) {
			System.out.println(vot);
		}
		
		dao.deleteOne(1);
		dao.deleteOne(2);
		System.out.println("삭제후조회");
		list = dao.selectAll();
		for (BoardVO vot : list) {
			System.out.println(vot);
		}
	%>
</body>
</html>