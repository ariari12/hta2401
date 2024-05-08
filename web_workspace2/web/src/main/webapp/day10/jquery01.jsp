<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery01.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// 1. 버튼을 클릭하면 server01.jsp의 내용을 비동기화 통신을 통해서 가져온후 결과를 콘솔에 출력
	$(() => {
		$("input[type='button']").on("click", () => {
			console.log("버튼 눌림");
			
			$.ajax({
				url : "server01.jsp",
				success : function(data) {
					// console.log(data);
					let data2 = data.trim(); // 공백제거
					let vList = data.split(",");
					for (let i = 0; i < vList.length; i++) {
						let m = vList[i];
						let txt = "<li>" + m + "</li>";
						// console.log(txt);
						$("ul").append(txt);
					}
				}
			})
			
		});
		
	})
</script>
</head>
<body>
	<h2>부서목록</h2>
	<input type="button" value="출력" />
	<!-- div>ul>li>h2{부서} -->
	<div>
		<ul>
			<li>
				<h2>부서</h2>
			</li>
			<!-- <li>SALES</li>
			<li>OPERATION</li> -->
		</ul>
	</div>
</body>
</html>