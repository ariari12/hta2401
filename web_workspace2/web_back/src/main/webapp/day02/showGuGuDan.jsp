<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>showGuGuDan.jsp</title>
</head>
<body>
	<%
		int dan = Integer.parseInt(request.getParameter("dan"));
		for (int i = 1; i <= 9; i++) {
			out.println("<h3> " + dan + " * " + i + " = " + (dan * i) + " </h3>");
		}
	%>
</body>
</html>