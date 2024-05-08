<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
<style>
	* {
		margin: 0;
		padding: 0;
	}
	h1 {
		text-align: center;
		margin: 20px;
	}
	span {
		display: inline-block;
		width: 150px;
	}
	form {
		margin-left: 50px;
	}
	div#button {
		text-align: center;
	}
	
</style>
<script>
	window.onload = function() {
		console.log("테스트");
	}
	
	function email(x) {
		let email2 = document.getElementById("email2");
		email2.value = (x.value == "직접입력") ? "" : x.value;
	}
</script>
</head>
<body>
	<h1>가입신청서</h1>
	
	<form action="registerOk.jsp">
		<span>ID:</span> 
		<input type="text" name="id" id="" /> <br>
		
		<span>NAME:</span> 
		<input type="text" name="name" id="" /> <br>
		
		<span>비밀번호:</span> 
		<input type="password" name="pw" id="" /> <br>
			  
		<span>성별:</span> 
		<input type="radio" name="gender" id="" value="남" />남
		<input type="radio" name="gender" id="" value="여" />여 <br>
		
		<div id="button">
			<input type="submit" value="가입하기" />
			<input type="reset" value="취소하기" />
		</div>
	</form>
</body>
</html>