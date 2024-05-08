<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(() => {
		
		$("input[type='button']").on("click", search);
		
		function search() {
			let empno = $("#empno").val().trim();
			console.log(empno);
			
			$.ajax({
				url : "search2.jsp",
				dataType : "html",
				data : {"empno" : empno},
				success : function(response, callback, request) {
					console.log(response);
					$("#info").html(response);
					// let result = $("#info").html();
					// result += response;
				}
			})
		}
	})
</script>
</head>
<body>
	<!-- 
		search2.jsp?empno=7788
	 -->
	 <input type="text" name="empno" id="empno" />
	 <input type="button" value="검색" />
	 
	 <div id="info">
	 	<h6>사원번호</h6>
	 	<h6>사원명</h6>
	 	<h6>급여</h6>
	 	<h6>직무</h6>
	 </div>
</body>
</html>