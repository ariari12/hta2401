<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery02.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(() => {
		// 1. 버튼을 클릭하면 콘솔에 메세지 출력
		$("input").on("click", function() {
			console.log("버튼 눌림");
			
			// 2. server02.jsp에 사원이름 출력 SCOTT, BLACK, KING
			
			$.ajax({
				url : "server02.jsp",
				// 3. 버튼을 클릭하면 server02.jsp의 내용을 콘솔에 출력
				success : function(data) {
					console.log(data);
					
					// 4. 버튼을 클릭하면 server02.jsp의 내용을 배열로 만들어 콘솔에 출력
					let data2 = data.trim().split(",");
					
					// 5. 배열에서 한개씩 꺼내 <li>SCOTT</li> 형태로 완성
					for(let i = 0; i < data2.length; i++) {
						let m = data2[i];
						let txt = "<li>" + m + "</li>";
						console.log(txt);
						// 6. ul 태그 뒤에 붙인다.
						$("ul").append(txt);
					}
				}
			});
		});
	})
	
</script>
</head>
<body>
	<h2>사원목록</h2>
	<input type="button" value="출력" />
	<!-- div>ul>li>h2{사원} -->
	<div>
		<ul>
			<li>
				<h2>사원</h2>
			</li>
		</ul>
	</div>
</body>
</html>