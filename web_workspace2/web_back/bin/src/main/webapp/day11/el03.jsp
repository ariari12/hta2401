<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el03.jsp</title>
</head>
<body>
	<%
		String n1 = request.getParameter("no1");
		String n2 = request.getParameter("no2");
		
		int no1 = 0;
		int no2 = 0;
		int result = 0;
		
		if (n1 != null && n2 != null) {
			no1 = Integer.parseInt(n1);
			no2 = Integer.parseInt(n2);
			result = no1 + no2;
			System.out.println(result);
	%>

	<form action="">
<%-- 		<input type="text" name="no1" id="" value="${ param.no1 }" /> +  --%>
		<input type="text" name="no1" id="" value="<%= no1 %>" /> + 
		<input type="text" name="no2" id="" value="<%= no2 %>" />
		<input type="submit" value="=" />
		<input type="text" name="result" id="" value="<%= result %>" />
	</form>
	<%
		} else {
			
	%>
		<form action="">
<%-- 		<input type="text" name="no1" id="" value="${ param.no1 }" /> +  --%>
		<input type="text" name="no1" id="" value="<%= no1 %>" /> + 
		<input type="text" name="no2" id="" value="<%= no2 %>" />
		<input type="submit" value="=" />
		<input type="text" name="result" id="" value="<%= result %>" />
	</form>
	<%
		}
	%>
</body>
</html>