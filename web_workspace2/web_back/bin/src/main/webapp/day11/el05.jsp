<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el05.jsp</title>
</head>
<body>
	<%
		pageContext.setAttribute("id1", "aaa");
		request.setAttribute("id2", "bbb");
		session.setAttribute("id3", "ccc");
		application.setAttribute("id4", "ddd");
	%>
	<a href="el06.jsp">이동하기</a>
	
	<!-- el06.jsp에 내장객체에 담은 속성값을 꺼내서 h3태그를 사용하여 이름 : 값 형식으로 출려하세요 -->
</body>
</html>