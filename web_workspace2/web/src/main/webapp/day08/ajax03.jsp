<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax03.jsp</title>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<script type="text/javascript">
	function prt() {
		console.log("prt() 호출중");
		console.dir(document.querySelector("#myconsole"))
		
		// search.jsp?txt=뽀로로코딩컴퓨터
		// ==> https://shop-phinf.pstatic.net/20210219_97/1613715831382Uyye9_JPEG/14851665105099013_669278903.jpg?type=m510
				
		let txt = document.querySelector("#txt").value;
		let params = "txt="+txt;
		
		sendRequest("search.jsp", params, callback, "GET");
	}
	
	function callback() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			
			
			// let img = document.querySelector("#img");
			// img.src = xhr.responseText;
			 document.querySelector("#myconsole").innerHTML = xhr.responseText;
		}
	}

</script>
</head>
<body>
	<input type="text" name="txt" id="txt" />
	<input type="button" value="출력" onclick="prt()" />
	
	<div id="myconsole">
		<img src="../images/before.png" alt="뽀로로" id="img" />
	</div>
</body>
</html>