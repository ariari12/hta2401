<%@page import="vo.FileVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.FileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
</head>
<body>
	<table>
		<tr>
			<th>파일번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>첨부파일이미지</th>
		</tr>
		
		<%
			FileDAO dao = new FileDAO();
			ArrayList<FileVO> list = dao.selectAll();
			for (FileVO vo : list) {
		%>
				<tr>
					<td><%= vo.getNo() %></td>
					<td><%= vo.getTitle() %></td>
					<td><%= vo.getWriter() %></td>
					<td><%= vo.getContents() %></td>
					<td><img src="../upload/<%= vo.getFilename() %>" alt="<%= vo.getFilename() %>" /></td>
				</tr>
		<%
				
			}
		%>
		
	</table>
</body>
</html>