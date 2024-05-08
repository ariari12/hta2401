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
		let frm = document.frm;
		
		console.dir(frm);
		
		frm[6].onclick = function() {
			console.log("유효성 검사");
			
			if (frm[0].value == '') {
				alert("아이디를 입력해주세요");
				frm[0].focus();
				return;
			}
			if (frm[1].value == '') {
				alert("이름을 입력해주세요")
				frm[1].focus();
				return;
			}
			if (frm[2].value == '') {
				alert("비밀번호를 입력해주세요")
				frm[2].focus();
				return;
			}
			if (!frm[3].checked && !frm[4].checked) {
				alert("성별을 선택해주세요")
				frm[3].focus();
				return;
			}
			if (frm[5].value == '') {
				alert("가입동기를 작성해주세요")
				frm[5].focus();
				return;
			}
			
			frm.action = "registerOk.jsp";
			frm.method = "get";
			frm.submit();
		}
		
	}
</script>
</head>
<body>
	<h1>가입신청서</h1>
	
	<form action="registerOk.jsp" name="frm">
		<span>ID:</span> 
		<input type="text" name="id" id="" placeholder="id를 입력하세요" /> <br>
		
		<span>NAME:</span> 
		<input type="text" name="name" id="" placeholder="이름을 입력하세요" /> <br>
		
		<span>비밀번호:</span> 
		<input type="password" name="pw" id="" placeholder="패스워드를 입력하세요" /> <br>
			  
		<span>성별:</span> 
		<input type="radio" name="gender" id="" value="남" />남
		<input type="radio" name="gender" id="" value="여" />여 <br>
		
		<span>가입동기</span> 
		<textarea name="motive" id="" cols="80" rows="5" style="resize: none;" 
			placeholder="가입동기를 입력하세요"></textarea>
		
		<div id="button">
			<!-- 가입버튼을 누르면 현재 입력된 값들이 올바른 값이 검사후에 registerOk.jsp로 전달 -->
			<input type="button" value="가입하기" />
			<input type="reset" value="취소하기" />
		</div>
	</form>
</body>
</html>