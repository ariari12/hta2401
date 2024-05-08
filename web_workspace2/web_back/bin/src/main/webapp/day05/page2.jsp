<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page2.jsp</title>
</head>
<body>
	<h2>page2.jsp</h2>
	<%
		Object obj1 = pageContext.getAttribute("id1");
		
		out.println("obj1: " + obj1);
		// 한글자만 출력
		String id1 = (String)obj1;
		/* out.println("<br>");
		out.println(str1.substring(0, 1)); */
		
		Object obj2 = request.getAttribute("id2");
		String id2 = (String)obj2;
		
		Object obj3 = session.getAttribute("id3");
		String id3 = (String)obj3;
		
		Object obj4 = application.getAttribute("id4");
		String id4 = (String)obj4;
		
		out.println("<h3> pageContext: " + id1 + "</h3>");
		out.println("<h3> request: " + id2 + "</h3>");
		out.println("<h3> session: " + id3 + "</h3>");
		out.println("<h3> application: " + id4 + "</h3>");
	%>
</body>
</html>