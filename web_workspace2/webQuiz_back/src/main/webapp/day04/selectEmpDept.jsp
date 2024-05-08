<%@page import="dao.EmpDAO"%>
<%@page import="vo.EmpDeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectEmpDept.jsp</title>
<style type="text/css">
	table {
		width: 80%;
		margin: 0 auto;
		border-collapse: collapse;
		text-align: center;
	}
	table, th, td {
		border: 1px solid red;
	}
</style>
</head>
<body>
	<%
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpDeptVO> list = dao.selectJoinAll();
	%>
	<!-- table>(tr>th*6)+(tr>td*6) -->
	<table>
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>급여</th>
			<th>부서번호</th>
			<th>부서명</th>
			<th>부서위치</th>
		</tr>
		<%
			for (EmpDeptVO vo : list) {
		%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getSal() %></td>
				<td><%= vo.getDeptno() %></td>
				<td><%= vo.getDname() %></td>
				<td><%= vo.getLoc() %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>