<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello.jsp</title>
</head>
<body>
	<h2>hello.jsp</h2>
	
	<h3>${msg}</h3>
	
	<%-- <%
		Object obj = request.getAttribute("msg");
		String msg = (String)obj;
		out.println("<h2>" + msg + "</h2>");
	%> --%>
</body>
</html>