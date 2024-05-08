<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%
	String b = request.getParameter("bno");
	
	try {
		if (b != null) {
			int bno = Integer.parseInt(b);			
			BoardDAO dao = new BoardDAO();
			dao.raiseHits(bno);
			
			dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
			
			if (vo == null) { throw new NumberFormatException(); }
%>
	<div class="container">
		<h2>상세보기</h2>
	
		<table class="table">
			<tr>
				<th>작성자</th>
				<td><%= vo.getWriter() %></td>
				<th>조회수</th>
				<td><%= vo.getHits() %></td>
				<th>작성일시</th>
				<td><%= vo.getRegdate() %></td>
			</tr>
			
			<tr>
				<th colspan="2">제목</th>
				<td colspan="4"><%= vo.getTitle() %></td>
			</tr>
			<tr>
				<th colspan="2">내용</th>
				<td colspan="4"><%= vo.getContents() %></td>
			</tr>
			
			<tr>
				<td colspan="6">
					<a href="list.jsp" class="btn btn-primary">목록</a>
					<a href="modify.jsp?bno=<%= vo.getBno() %>" class="btn btn-success">수정</a>
					<a href="deleteOk.jsp?bno=<%= vo.getBno() %>" class="btn btn-danger">삭제</a>
				</td>
			</tr>
		</table>
	</div>
	
<%
		}
	} catch (NumberFormatException e) {
%>
		<form action="" name="frm"></form>
		<script type="text/javascript">
			alert("유효하지 않은 게시글 번호입니다.");
			document.frm.action = "list.jsp";
			document.frm.submit();
		</script>
<%
	}
%>
</body>
</html>