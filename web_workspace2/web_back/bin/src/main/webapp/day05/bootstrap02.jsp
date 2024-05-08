<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bootstrap02.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h3>표만들기</h3>
		<table class="table table-hover">
			<tr>
				<th>NO</th>
				<th>제품명</th>
				<th>가격</th>
			</tr>
			<tr>
				<td>1</td>
				<td>김밥</td>
				<td>3000</td>
			</tr>
			<tr>
				<td>2</td>
				<td>햇반</td>
				<td>2500</td>
			</tr>
		</table>
		
		<button type="button">버튼</button>
		<button type="button" class="btn">버튼</button>
		<button type="button" class="btn btn-primary">버튼</button>
		<button type="button" class="btn btn-success">버튼</button>
		<button type="button" class="btn btn-danger">버튼</button>
		<button type="button" class="btn btn-warning">버튼</button>
		<button type="button" class="btn btn-info">버튼</button>
		<button type="button" class="btn btn-light">버튼</button>
		<button type="button" class="btn btn-dark">버튼</button>
		<button type="button" class="btn btn-link">링크버튼</button>
		
	</div>
</body>
</html>