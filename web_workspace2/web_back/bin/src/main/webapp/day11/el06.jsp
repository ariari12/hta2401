<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el06.jsp</title>
</head>
<body>
	<%
		/* Object id1 = pageContext.getAttribute("id1");
		Object id2 = request.getAttribute("id2");
		Object id3 = session.getAttribute("id3");
		Object id4 = application.getAttribute("id4"); */
	%>
	<h3>id1 : ${ id1 }</h3>
	<h3>id2 : ${ id2 }</h3>
	<h3>id3 : ${ sessionScope.id3 }</h3>
	<h3>id4 : ${ applicationScope.id4 }</h3>
</body>
</html>