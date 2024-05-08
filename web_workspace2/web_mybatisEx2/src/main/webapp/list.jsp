<%@page import="kr.co.jhta.web.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jhta.web.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<!-- bootstrap을 사용해서 사원의 목록을 표형식으로 출력 -->
	<div class="container">
		<table class="table table-striped">
			<tr>
				<th>사번</th>
				<th>사원명</th>
				<th>직업</th>
				<th>매니저</th>
				<th>입사일자</th>
				<th>급여</th>
				<th>수수료</th>
				<th>부서번호</th>
			</tr>
		<%
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.selectAll();
		
			for(EmpVO vo : list) {
		%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getJob() %></td>
				<td><%= vo.getMgr() %></td>
				<td><%= vo.getHiredate() %></td>
				<td><%= vo.getSal() %></td>
				<td><%= vo.getComm() %></td>
				<td><%= vo.getDeptno() %></td>
			</tr>
		<%
			}
		%>
			<tr>
				<td colspan="8">
					<a href="write.jsp" class="btn btn-primary">사원등록</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>