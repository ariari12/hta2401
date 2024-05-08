<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl05.jsp</title>
</head>
<body>
	<%
		for(int i = 1; i <= 10; i+= 2) {
			out.println("<h3>" + i + "</h3>");
		}
	%>
	
	<hr>
	
	<c:forEach begin="1" end="10" var="i" step="2">
		<h3>${ i }</h3>
	</c:forEach>
</body>
</html>