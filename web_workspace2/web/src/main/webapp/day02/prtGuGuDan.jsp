<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prtGuGuDan.jsp</title>
</head>
<body>
	<!-- 입력받은 구구단을 출력 -->
	<!-- action 값을 따로 지정하지 않으면 자기 자신에게 리턴하게 된다. -->
	<!-- <form action="showGuGuDan.jsp" method="get"> -->
	<form action="" method="get">
		<input type="text" name="dan" id="" />
		<input type="submit" value="출력" />
	</form>
	
	<%
		String d = request.getParameter("dan");
		/* out.println(d); */
		
		if (d != null) {
			// 문자 ==> 숫자로 형변환
			int dan = Integer.parseInt(d);
			for (int i = 1; i <= 9; i++) {
				out.println("<h3> " + dan + " * " + i + " = " + (dan * i) + " </h3>");
			}
		}
	%>
</body>
</html>