<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	#container {
		margin: 0px 50px;
	}
	div#button {
		text-align: center;
	}
	
</style>
<script>

	window.onload = function() {
		let searchAddrs = document.getElementById("searchAddrs");
		let join = document.getElementById("join");
		let result = document.getElementById("result");
		console.dir(result);
		<%
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String zipcode = request.getParameter("zipcode");
			String addrs = request.getParameter("addrs");
		%>
			
		console.dir(id);
		console.dir(pw);
			
		<%
			if (id != null && pw != null && name != null) {
				MemberDAO dao = new MemberDAO();
				MemberVO vo = new MemberVO(0, id, pw, name, gender, null, zipcode, addrs);
				
				if (dao.insertMember(vo)) {
		%>
					result.innerHTML = "<h2> 회원가입이 완료되었습니다. </h2>";
					result.innerHTML += "<h2> 로그인 화면으로 이동합니다... </h2>";
					console.dir(result);
					
					window.setTimeout(() => {
						document.frm.action="login.jsp"
						document.frm.submit();						
					}, 2000);
		<%
				}
			}
		%>
		
		console.dir(searchAddrs);
		console.dir(join);
		
		searchAddrs.onclick = openKakaoPostCode;
		join.onclick = nullcheck;
		
	}
	
	function nullcheck() {
		let id = document.getElementById("id");
		let pw = document.getElementById("pw");
		let name = document.getElementById("name");
		
		console.dir(id);
		console.dir(pw);
		console.dir(name);
		
		if (id.value == null || id.value == '') {
			alert("아이디를 입력해주세요");
			id.focus();
			return;
		} else if (name.value == null || name.value == '') {
			alert("이름을 입력해주세요");
			name.focus();
			return;
		} else if (pw.value == null || pw.value == '') {
			alert("비밀번호를 입력해주세요");
			pw.focus();
			return;
		}
		
		let frm = document.frm;
		
		frm.action = "register.jsp";
		frm.method = "get";
		frm.submit();
	}
	
	function email(x) {
		let email2 = document.getElementById("email2");
		email2.value = (x.value == "직접입력") ? "" : x.value;
	}
	
	function openKakaoPostCode() {
		console.log("클릭");
		new daum.Postcode({
	        oncomplete: function(data) {
	        	// 팝업에서 검색 결과 항목을 클릭했을때
	        	// 실행할 코드를 작성하는 부분
	        	console.log("팝업에서 검색 버튼 눌림");
	        	console.dir(data);
	        	
	        	document.getElementById("zipcode").value = data.zonecode;
	        	document.getElementById("addrs").value = data.address;
	        }
	    }).open();
	}
</script>
</head>
<body>
	

	<h1>가입신청서</h1>
	
	<div id="container">
		<form action="" name="frm">
			<span>ID:</span> 
			<input type="text" name="id" id="id" /> <br>
			
			<span>NAME:</span> 
			<input type="text" name="name" id="name" /> <br>
			
			<span>주민등록번호:</span> 
			<input type="text" name="identify1" id="" />
			- <input type="text" name="identify2" id="" /> <br>
						 
			<span>비밀번호:</span> 
			<input type="password" name="pw" id="pw" /> <br>
			
			<span>전화번호:</span> 
			<input type="text" name="tel1" id="" /> 
			- <input type="text" name="tel2" id="" />
			- <input type="text" name="tel3" id="" /> <br>
					 
			<span>우편번호:</span> 
			<input type="text" name="zipcode" id="zipcode" size="10" /> 
			<input type="button" id="searchAddrs" value="주소검색" /> <br>
			<span>주소:</span>
			<input type="text" name="addrs" id="addrs" size="50" /> <br>
			
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
				<input type="button" id="join" value="가입하기" />
				<input type="reset" value="취소하기" />
			</div>
		</form>
		
		<div id="result"></div>
	</div>
	
</body>
</html>