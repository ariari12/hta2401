<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>address1.jsp</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	window.onload = function() {
		let btn = document.getElementById("btn");
		btn.onclick = openKakaoPostCode; // openKakaoPostCode함수를 실행
	}
	
	function openKakaoPostCode() {
		new daum.Postcode({
	        oncomplete: function(data) {
	        	// 팝업에서 검색 결과 항목을 클릭했을때
	        	// 실행할 코드를 작성하는 부분
	        	console.log("팝업에서 검색 버튼 눌림");
	        	console.dir(data);
	        	
	        	document.getElementById("post1").value = data.zonecode;
	        	document.getElementById("addrs").value = data.roadAddress;
	        	document.getElementById("addrs2").value = data.jibunAddress;
	        }
	    }).open();
	}
</script>
</head>
<body>

	<h1>우편번호</h1>
	<!-- API (Application Programming Interface) -->
	
	<input type="text" name="" id="post1" />
	<input type="button" value="우편번호찾기" id="btn" /> <br/>
	
	도로명 : <input type="text" name="addrs" id="addrs" size="100" /> <br/>
	지번 : <input type="text" name="addrs2" id="addrs2" size="70" />
</body>
</html>