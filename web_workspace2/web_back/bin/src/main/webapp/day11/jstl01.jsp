<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl01.jsp</title>
</head>
<body>
	<!-- 
		태그로 자바의 문법적 기능을 수행
		
		JSTL (Java Standard Tag Library)
		1. 간단한 프로그램 로직 구사
		2. for문, if문 태그 지원
		3. 날짜, 시간, 숫자 형식
		4. 데이터베이스의 입출력
		5. xml 문서의 처리
		6. 문자열을 처리하는 함수 호출 
	-->
	
	<c:set var="num1" value="300" scope="request"></c:set>
	<!-- request.setAttribute("num1", "300";) -->
	
	<c:set var="num2" value="200" scope="request"></c:set>
	
	<jsp:forward page="jstl02.jsp"></jsp:forward>
	
	<!-- jstl02.jsp에서 두수의 합을 h1태그로 출력 -->
</body>
</html>