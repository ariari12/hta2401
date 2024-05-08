<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewCounter.jsp</title>
</head>
<body>
	<%! 
		int cnt = 0; 
	%>
	<% 
		cnt++; 
	%>
	<h1>현재 페이지 방문횟수 : <%= cnt %>회</h1>
</body>
</html>