<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>actionTag04.jsp</title>
</head>
<body>
	<h3>actionTag04.jsp</h3>

	<!-- actionTag05.jsp?n1=500 같은 효과 -->	
	<jsp:forward page="actionTag05.jsp">
		<jsp:param value="500" name="n1"/>
	</jsp:forward>
</body>
</html>