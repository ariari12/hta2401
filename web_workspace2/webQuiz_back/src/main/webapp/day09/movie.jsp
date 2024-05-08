<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>movie.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(() => {
		$("#btn").on("click", searchMovie);
	})
	
	function searchMovie() {
		let name = $("#movie").val().trim();
		console.log(name);
		
		$.ajax({
			url : "searchMovie.jsp",
			dataType : "html",
			data : {"name" : name},
			success : function(response, status, request) {
				console.log(response);
				$("img").attr("src", response);
			}
		})
	}
</script>
</head>
<body>
	<h2>영화소개</h2>
	
	<!--
		1. 영화명 검색
		
		searchMovie.jsp?name=듄
		2. 비동기 방식으로 영화이미지 경로를 얻어오기
		3. 현재 화면 img 태그의 소스로 지정 
	 -->
	 
	 <input type="text" name="movie" id="movie" />
	 <input type="button" value="검색" id="btn" />
	 <div><img src="../images/movie_image1.jpg" alt="" /></div>
</body>
</html>