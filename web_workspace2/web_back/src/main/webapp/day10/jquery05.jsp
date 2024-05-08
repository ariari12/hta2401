<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery05.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("input").on("click", function() {
			$.ajax({
				url : "getDeptJSON.jsp",
				type : "get",
				success : function(data) {
					console.log(data);
					let obj = JSON.parse(data);
					console.log(obj);
					for (let i = 0; i < obj.length; i++) {
						// let ele = obj[i];
						let txt = "<li>" + obj[i].deptno + " : " + obj[i].dname + " : " + obj[i].loc + "</li>";
						$("ul").append(txt);
					}
				}
			});
		})
	})
</script>
</head>
<body>
	<h2>AJAX JSON</h2>
	<input type="button" value="출력" />
	
	<ul>
		<li>목록</li>
	</ul>
</body>
</html>