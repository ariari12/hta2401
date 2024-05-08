<%@page import="vo.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectEmp.jsp</title>
<style type="text/css">
	table {
		width: 80%;
		margin: 0 auto;
		text-align: center;
		border-collapse: collapse;
	}
	table, th, td {
		border: 1px solid red;
	}
</style>
</head>
<body>
	<%
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.selectAll();
	%>
	
	<!-- table>(tr>th*5)+(tr>td*5) -->
	<table>
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>급여</th>
			<th>입사일</th>
			<th>부서번호</th>
		</tr>
		<%
			for (EmpVO vo : list) {
		%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getSal() %></td>
				<td><%= vo.getHiredate() %></td>
				<td><%= vo.getDeptno() %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>