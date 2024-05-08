<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax02.jsp</title>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<script type="text/javascript">
	function helloToServer() {
		console.log("test");
		
		let txt = document.querySelector("#txt").value;
		
		let params = "txt="+txt; // txt=aaa
		
		sendRequest("hello.jsp", params, callback, "GET");
		// sendRequest(URL, 파라미터, 콜백함수명, 통신방식)
		
		// 통신객체가 hello.jsp?txt=aaa 요청을 한다.
	}
	
	function callback() {
		// console.log("콜백함수 호출중");
		if(xhr.readyState == 4) {
			if(xhr.status == 200) {
				document.querySelector("#div1").innerHTML = xhr.responseText;
			}
		}
		
	}
</script>
</head>
<body>
	<!--
		입력된 파일명을 탐색해서 내용을 화면에 출력 
	 -->
	 
	 <input type="text" name="txt" id="txt" />
	 
	 <input type="button" value="입력" onclick="helloToServer()" />
	 
	 <!-- id가 aaa인 사용자의 이름을 아래 div에 출력 -->
	 
	 <div id="div1"></div>
</body>
</html>