<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery04.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// dept.xml 파일에서 부서명과 부서위치를 리스트로 출력
	$(() => {
		$("input").on("click", function() {
			$.ajax({
				url : "dept.xml",
				dataType : "xml",
				success : function(data) {
					let dept = $(data).find("dept");
					console.log(dept);
					dept.each(function() {
						let deptno = $(this).find("deptno").text();
						let dname = $(this).find("dname").text();
						let loc = $(this).find("loc").text();
						// 10 : ACCOUNTING : NEW YORK
						let txt = "<li>" + deptno + " : " + dname + " : " + loc + "</li>";
						
						console.log(txt);
						$("ul").append(txt);
					});
				}
			});		
		});
	})
	
</script>
</head>
<body>
	<h3>부서리스트</h3>
	<input type="button" value="부서가져오기" />
	<div>
		<ul>
			<li>
				<h2>부서리스트</h2>
			</li>
		</ul>
	</div>
</body>
</html>