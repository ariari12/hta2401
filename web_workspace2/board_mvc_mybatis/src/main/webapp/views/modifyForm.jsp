<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script>
	$(function() {
		$(".summernote").summernote({
			height : 150,
			lang : "ko-KR"
		})
	})
</script>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<form action="board.do">
			<table class="table table-striped">

				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id="" value="${vo.writer}" /></td>
					<th>조회수</th>
					<td>${vo.hits}</td>
					<th>작성일수</th>
					<td>
						${vo.regdate}
						<input type="hidden" name="bno" value="${vo.bno }" />
						<input type="hidden" name="cmd" value="modifyOk" />
					</td>
					
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><input type="text" name="title" value="${vo.title}" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="summernote" name="contents" id=""
							cols="30" rows="10">${vo.contents}</textarea></td>
				</tr>
				<tr>
					<td>
						<a href="board.do" class="btn btn-success">목록</a>
						<input type="submit" class="btn btn-primary" value="수정" />
						<input type="reset" class="btn btn-danger" value="다시쓰기" />
					</td>					
				</tr>
			</table>
		</form>
	</div>

</body>
</html>