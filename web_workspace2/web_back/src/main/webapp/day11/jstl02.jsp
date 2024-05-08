<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl02.jsp</title>
</head>
<body>
<%-- 	<%
		String n1 = (String)request.getAttribute("num1");
		String n2 = (String)request.getAttribute("num2");
		
		int num1 = 0;
		int num2 = 0;
		int result = 0;
		
		if (n1 != null && n2 != null) {
			num1 = Integer.parseInt(n1);
			num2 = Integer.parseInt(n2);
			result = num1 + num2;
	%>
	
	<h1><%= num1 %> + <%= num2 %> = <%= result %></h1> --%>
	<h1>${ num1 } + ${ num2 } = ${ num1 + num2 }</h1>
	
	<!-- 두수중 큰 값 출력하기 -->
	<h1>${ num1 > num2 ? num1 : num2 }</h1>
	
<%-- 	<%
		}	
	%> --%>
	
	<%-- 
		<c:if test="조건">
					// 만족시 실행
		</c:if> 
	--%>
	
	<c:if test="${ num1 > num2 }">
		<h1>num1이 더 크다</h1>
	</c:if>
</body>
</html>