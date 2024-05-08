<%@page import="vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptList.jsp</title>
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
	.deptno {
		width: 10%;
	}
	.dname, .loc {
		width: 45%;
	}
</style>
</head>
<body>
	<%
		/* 부서 전체 정보를 가져오기 */
		
		DeptDAO dao = new DeptDAO(); 
	
		ArrayList<DeptVO> list = dao.selectAll();
		
		System.out.println("list: " + list);
	%>
	
	<!-- table>(tr>th*3)+(tr>td*3)*4 -->
	<table>
		<tr>
			<th>부서번호</th>
			<th>부서명</th>
			<th>부서위치</th>
		</tr>
		<%
			for(DeptVO vo : list) {
		%>
		<tr>
			<td class="deptno"><%= vo.getDeptno() %></td>
			<td class="dname"><%= vo.getDname() %></td>
			<td class="loc"><%= vo.getLoc() %></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>