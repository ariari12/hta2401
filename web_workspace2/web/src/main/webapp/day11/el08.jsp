<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el08.jsp</title>
</head>
<body>
	<%
		/* String[] hobby = request.getParameterValues("hobby");
		for (String x : hobby) {
			out.println("<h1>당신의 취미는 " + x + "입니다.</h1>");
		} */
	%>
	
	<h1>당신의 취미는 : ${ paramValues.hobby[0] }</h1>
	<h1>당신의 취미는 : ${ paramValues.hobby[1] }</h1>
	<h1>당신의 취미는 : ${ paramValues.hobby[2] }</h1>
	<h1>당신의 취미는 : ${ paramValues.hobby[3] }</h1>
</body>
</html>