<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie2.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		// jquery 쿠키 플러그인 사용
		
		// 쿠키 생성 : 세션이 종료될때까지 존재
		Cookies.set("id", "hong");
		// 7일 뒤에 만료되는 쿠키 생성
		Cookies.set('product', 'keyboard', { expires : 7 });
		
		// 쿠키 읽어오기
		let product = Cookies.get('product');
		// 읽어온 쿠키 값 출력
		console.log("product: " + product);
		// product 이름의 쿠키 삭제
		Cookies.remove('product');
	})
</script>
</head>
<body>

</body>
</html>