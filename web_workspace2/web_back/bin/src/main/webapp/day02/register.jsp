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
	
	<form action="result.jsp">
		<span>ID:</span> 
		<input type="text" name="id" id="" /> <br>
		
		<span>NAME:</span> 
		<input type="text" name="name" id="" /> <br>
		
		<span>주민등록번호:</span> 
		<input type="text" name="identify1" id="" />
		- <input type="text" name="identify2" id="" /> <br>
					 
		<span>비밀번호:</span> 
		<input type="password" name="pw" id="" /> <br>
		
		<span>전화번호:</span> 
		<input type="text" name="tel1" id="" /> 
		- <input type="text" name="tel2" id="" />
		- <input type="text" name="tel3" id="" /> <br>
				 
		<span>주소:</span> 
		<input type="text" name="address" id="" /> <br>
		
		<span>EMAIL:</span> 
		<input type="text" name="email1" id="" />
		@ <input type="text" name="email2" id="email2" />
		<select name="emailFmt" id="emailFmt" onChange="email(this);">
			<option value="직접입력">직접입력</option>
		  	<option value="gmail.com">gmail.com</option>
		  	<option value="naver.com">naver.com</option>
		  	<option value="daum.net">daum.net</option>
		</select> <br>
			  
		<span>성별:</span> 
		<input type="radio" name="gender" id="" value="남" />남
		<input type="radio" name="gender" id="" value="여" />여 <br>
			  
		<span>취미:</span> 
		<input type="checkbox" name="hobby" id="" value="음악감상" />음악감상
		<input type="checkbox" name="hobby" id="" value="독서" />독서
		<input type="checkbox" name="hobby" id="" value="운동" />운동 <br>
		
		<div id="button">
			<input type="submit" value="가입하기" />
			<input type="reset" value="취소하기" />
		</div>
	</form>
</body>
</html>