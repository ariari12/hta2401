<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl04.jsp</title>
</head>
<body>
	<h1>${ param.no1 }</h1>
	<h1>${ param.no2 }</h1>

	<!-- EL -->
	<h1>큰 값: ${ param.no1 > param.no2 ? param.no1 : param.no2 }</h1>
	
	<!-- jstl -->
	<c:if test="${ param.no1 >= param.no2 }">
		<h1>${ param.no1 }이 더 큰 값</h1>
	</c:if>
	<c:if test="${ param.no2 > param.no1 }">
		<h1>${ param.no2 }이 더 큰 값</h1>
	</c:if>
</body>
</html>