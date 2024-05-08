<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery03.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("input").on("click", function() {
			$.ajax({
				url : "server03.xml",
				dataType : "xml",
				success : function(data) {
					console.log(data);
					// 넘겨 받은 데이터에서 book 이라는 요소를 검색
					let data2 = $(data).find("book");
					console.log(data2);
					data2.each(function() {
						// 각각의 요소$(this) 마다 타이틀 엘리먼트 객체에 접근
						console.log($(this));
						let title = $(this).find("title").text();
						let price = $(this).find("price").text();
						let txt = "<li>" + title + " : " + price + "</li>";
						console.log(txt);
						$("ul").append(txt);
					})
					
					let title = data2.find("title");
					console.log(title);
					// 그 대상의 글자는 text();
					console.log(title.text());
					// 태그 모양으로
					let txt = "<li>" + title.text() + " : " + "</li>";
					console.log(txt);
				}
			});
		})
	})
</script>
</head>
<body>
	<h3>도서관</h3>
	<input type="button" value="도서목록" />
	
	<!-- div>ul>li>h2{신간목록} -->
	<div>
		<ul>
			<li>
				<h2>신간목록</h2>
			</li>
		</ul>
	</div>
</body>
</html>