<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jqueryAjax01.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(() => {
		$("#img").on("click", () => {
			console.log("뽀로로 클릭함");
			
			console.log($("#img").attr("src"));
			// $("#img").attr("src", "../images/after.png");
			
			$.ajax({
				// url : "getJSON1.jsp", // 존재하지 않는 파일이므로 error의 함수 실행됨
				url : "getJSON.jsp",
				type : "get",
				dataType : "html", // 전송받을 데이터 html, json, xml
				data : {"id" : "aaa"}, // getJSON.jsp?id=aaa 이렇게 요청
				success : function(response, status, request) {
					console.log("성공시 메세지 출력");
					console.log(response);
					$("img").attr("src", response);
				},
				error : function(response, status, request) {
					console.log(response);
					$("img").attr("src", "https://cdn.pixabay.com/photo/2017/02/12/21/29/false-2061131_640.png");
				},
				complete : function() {
					console.log("AJAX 통신 끝");
					$("img").fadeIn(2000);
				},
				beforeSend : function() {
					console.log("요청 보내기전에 호출됨");
					$("img").fadeOut(2000);
				}
			})
		})
	})
</script>
</head>
<body>
	<!-- jquery를 사용해서 이미지를 클릭하면 콘솔에 메세지 출력 -->
	<img src="../images/before.png" alt="" id="img" />
</body>
</html>