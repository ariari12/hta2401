<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h2>사원추가</h2>
		<form action="writeOk.jsp" method="get">
		<table class="table table-striped">
			<tr>
				<th>사번</th>
				<td><input type="text" name="empno" id="" /></td>
			</tr>
			<tr>
				<th>사원명</th>
				<td><input type="text" name="ename" id="" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="job" id="" /></td>
			</tr>
			<tr>
				<th>매니저</th>
				<td><input type="text" name="mgr" id="" /></td>
			</tr>
			<tr>
				<th>입사일자</th>
				<td><input type="date" name="hiredate" id="" /></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input type="text" name="sal" id="" /></td>
			</tr>
			<tr>
				<th>수수료</th>
				<td><input type="text" name="sal" id="" /></td>
			</tr>
			<tr>
				<th>부서번호</th>
				<td><input type="text" name="deptno" id="" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>