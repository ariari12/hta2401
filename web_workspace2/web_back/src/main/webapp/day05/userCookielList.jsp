<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userCookielList.jsp</title>
</head>
<body>
	<h1>쿠키</h1>
	
	<%
		Cookie[] clist = request.getCookies();
	
		for (Cookie c : clist) {
			out.println("<h2> 쿠키: " + c.getName() + " : " + c.getValue() + "</h2>" );
		}
	%>
</body>
</html>