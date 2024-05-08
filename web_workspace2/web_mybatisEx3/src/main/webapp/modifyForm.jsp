<%@page import="kr.co.jhta.web.vo.DeptVO"%>
<%@page import="kr.co.jhta.web.dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyForm.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<%
		String dn = request.getParameter("deptno");	
	
		if (dn != null) {
			try {
				int deptno = Integer.parseInt(dn);
				DeptDAO dao = new DeptDAO();
				DeptVO vo = dao.selectOne(deptno);
			
	%>
	<div class="container">
		<h2>부서수정</h2>
		<form action="modifyOk.jsp">
		<table class="table table-hover">
			<tr>
				<th>부서번호</th>
				<td><%= vo.getDeptno() %></td>
				<td><input type="hidden" name="deptno" id="" value="<%= vo.getDeptno() %>" /></td>
			</tr>
			<tr>
				<th>부서명</th>
				<td><input type="text" name="dname" id="" value="<%= vo.getDname() %>" /></td>
			</tr>
			<tr>
				<th>부서위치</th>
				<td><input type="text" name="loc" id="" value="<%= vo.getLoc() %>" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-warning" value="수정" />
				</td>
			</tr>
		</table>
		</form>
	</div>
	<%
			} catch (IllegalArgumentException e) {
				
			}
		}
	%>
</body>
</html>