<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style type="text/css">
	td:first-child>th,td {
		width: 16.6%;
	}
</style>
</head>
<body>
<%
	String b = request.getParameter("bno");
	
	try {
		if (b != null) {
			int bno = Integer.parseInt(b);			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
			
			if (vo == null) { throw new IllegalArgumentException(); }
%>
	<div class="container">
		<h2>수정하기</h2>
	
		<form action="modifyOk.jsp">
			<input type="hidden" name="bno" value="<%= vo.getBno() %>" />
			
			<table class="table table-hover">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id="" value="<%= vo.getWriter() %>" /></td>
					<th>조회수</th>
					<td><%= vo.getHits() %></td>
					<th>작성일시</th>
					<td><%= vo.getRegdate() %></td>
				</tr>
				
				<tr>
					<th colspan="2">제목</th>
					<td colspan="4"><input type="text" name="title" id="" value="<%= vo.getTitle() %>" /></td>
				</tr>
				<tr>
					<th colspan="2">내용</th>
					<td colspan="4">
						<textarea name="contents" id="" cols="30" rows="10"><%= vo.getContents() %></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<a href="list.jsp" class="btn btn-primary">목록</a>
						<input type="submit" class="btn btn-success" value="수정" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<%
		}
	} catch (IllegalArgumentException e) {
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