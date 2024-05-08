<%@page import="vo.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empList.jsp</title>
<style type="text/css">
	table {
		width: 80%;
		margin: 0 auto;
		border-collapse: collapse;
		text-align: center;
	}
	table, th, td {
		border: 1px solid blue;
	}
</style>
</head>
<body>
	<%
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.selectAll();
	%>
	<!-- table>(tr>th*8)+(tr>td*8) -->
	<table>
		<tr>
			<th>empno</th>
			<th>ename</th>
			<th>job</th>
			<th>mgr</th>
			<th>hiredate</th>
			<th>sal</th>
			<th>comm</th>
			<th>deptno</th>
		</tr>
	<%
		for (EmpVO vo : list) {
	%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getJob() %></td>
				<% 
					out.print("<td> ");
					if (vo.getMgr() != 0) { out.print(vo.getMgr()); }
					out.println(" </td>");
				%>
				<td><%= vo.getHiredate() %></td>
				<td><%= vo.getSal() %></td>
				<td><%= vo.getComm() %></td>
				<td><%= vo.getDeptno() %></td>
			</tr>
	<%
		}
	%>
		</table>
</body>
</html>