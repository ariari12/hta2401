<%@page import="vo.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchDept.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="">
			<input type="text" name="deptno" id="" />
			<input type="submit" value="검색" />
		</form>
		
		<%
			String deptno = request.getParameter("deptno");
			
			if (deptno == null || deptno.length() == 0) {
				DeptDAO dao = new DeptDAO();
				ArrayList<DeptVO> list = new ArrayList<DeptVO>();
				list = dao.selectAll();
				
				out.println("<div id='result'>");
				out.println("<table class='table table-hover'>");
				for (DeptVO vo : list) {
		%>
					<tr>
						<td><%= vo.getDeptno() %></td>
						<td><%= vo.getDname() %></td>
						<td><%= vo.getLoc() %></td>
					</tr>
		<%
				}
				out.println("</table>");
				out.println("</div>");
				
			} else if (deptno != null) {
				int dno = Integer.parseInt(deptno);
				DeptDAO dao = new DeptDAO();
				DeptVO vo = dao.selectOne(dno);
				
				out.println("<div id='result'>");
				out.println("<table class='table table-hover'>");
		%>
				<tr>
					<td><%= vo.getDeptno() %></td>
					<td><%= vo.getDname() %></td>
					<td><%= vo.getLoc() %></td>
				</tr>
		<%
				}
				out.println("</table>");
				out.println("</div>");
		%>
	</div>
</body>
</html>