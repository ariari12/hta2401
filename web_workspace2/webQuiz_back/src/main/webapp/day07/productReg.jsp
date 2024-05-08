<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productReg.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="productRegOk.jsp" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="pname" id="" /></td>
				</tr>
				<tr>
					<th>상품이미지</th>
					<td><input type="file" name="fname" id="" /></td>
				</tr>
				<tr>
					<th>상품수량</th>
					<td><input type="text" name="cnt" id="" /></td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input type="text" name="price" id="" /></td>
				</tr>
				<tr>
					<th>할인율</th>
					<td><input type="text" name="dcratio" id="" /></td>
				</tr>
				<tr>
					<th>상세설명</th>
					<td><textarea name="prodesc" id="" cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" class="btn btn-primary" value="등록" />
						<input type="reset" class="btn btn-warning" value="RESET" />
						<a href="main.jsp" class="btn btn-success">MAIN</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>