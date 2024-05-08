<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>b.jsp</title>
</head>
<body>
	<!-- a.jsp 파일을 불러오기 -->
	<!-- 1. include 지시자를 사용 -->
	<!-- copy&paste 효과 -->
	<%-- <%@ include file="a.jsp" %> --%>
	
	<!-- 2. jsp action tag -->
	<jsp:include page="a.jsp"></jsp:include>
	<!-- 컴파일 된 결과를 삽입 => 변수값을 불러오지 못함 -->
	
	<%-- <%
		// a변수를 출력
		out.println("b.jsp에서 a의 값: " + a);
	%> --%>
</body>
</html>