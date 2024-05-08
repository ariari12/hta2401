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
		<form action="result2.jsp" method="post" enctype="multipart/form-data">
			<table class="table tabl-hover">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="" value="대한민국" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id="" value="홍길동" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="contents" id="" cols="30" rows="10">우리나라만세</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="filename1" id="" /></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="filename2" id="" />
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="filename3" id="" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input class="btn btn-primary" type="submit" value="전송" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>