<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery06.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// 1. getMemberJSON.jsp ==> 회원정보(member)를 JSON형태로 출력(with json-simple)
	// 2. 버튼 클릭시 회원 id, pw, name을 ul태그 자손으로 출력
	$(function() {
		$("input").on("click", function() {
			$.ajax({
				url : "getMemberJSON.jsp",
				success : function(data) {
					let obj = JSON.parse(data);
					console.log(obj);
					for (let i = 0; i < obj.length; i++) {
						let txt = "<li>" + obj[i].id + " : " + obj[i].pw + " : " + obj[i].name + "</li>";
						$("ul").append(txt);
					}
				}
			})
		});
	})
</script>
</head>
<body>
	<h1>ajax member JSON</h1>
	<input type="button" value="회원정보출력" />
	<ul>
		<li>목록</li>
	</ul>
</body>
</html>