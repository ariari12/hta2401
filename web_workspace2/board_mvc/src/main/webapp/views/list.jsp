<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<div class="container">	
		<h2>게시판</h2>
		<h2>list.jsp</h2>
		<a href="board.do?cmd=write" class="btn btn-primary">글쓰기</a>
		<table class="table table-striped">
			<tr>
				<th>게시물번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="dto" items="${list}" >
				<tr>
					<td>${dto.bno}</td>
					<td>${dto.writer}</td>
					<td><a href="board.do?cmd=detail&bno=${dto.bno}">${dto.title}</a></td>
					<td>${dto.hits}</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	
	
</body>
</html>