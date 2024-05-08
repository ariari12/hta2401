<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan.jsp</title>
</head>
<body>
	<!-- JSP 사용해서 h1 태그 구구단 3단 출력 -->
	
	<%
		for (int i = 1; i <= 9; i++) {
			
		// 표현식 Expression
	%>
		<!-- HTML 주석 : id: aaa -->
		<%-- JSP 주석 pw : bbb => 브라우저 소스에 안 보임(전달X) --%>
		<h1> 3 * <%= i %> = <%= 3 * i %> </h1>
		
	<%
		}
	%>
</body>
</html>