<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>responseEx.jsp</title>
</head>
<body>

	<%
		int status = response.getStatus();
	
		out.println("<h1> 상태: " + status + "</h1>");
		
		// 200: 정상
		// 404: NOT FOUND
		// 405: doGet, doPost
		// 500: server side logic error
	%>
	
	<h2>다른 페이지로 이동</h2>
	
	<!-- menu.jsp로 이동  -->
	<ol>
		<li>forward</li>
		<%
			// 주소는 여기로 찍히지만 내용은 menu.jsp
			// RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
			// rd.forward(request, response);
		%>
		<li>redirect</li>
		<%
			// 주소도 내용도 menu.jsp로 찍힘
			response.sendRedirect("menu.jsp");
		%>
	</ol>

</body>
</html>