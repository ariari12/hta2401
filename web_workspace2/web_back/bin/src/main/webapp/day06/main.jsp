<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link rel="stylesheet" href="../css/main_2024_04_23.css" />
</head>
<body>
	<div id="container">
		<div id="header">
			<img src="../images/naver.png" alt="" />
		</div>
		
		<div id="nav">
			<a href="news.jsp">뉴스</a>
			<a href="enter.jsp">연예</a>
			<a href="shopping.jsp">쇼핑</a>
			<a href="movie.jsp">영화</a>
			<a href="cook.jsp">요리</a>
		</div>
		<div id="leftContents">
			<h1>뉴스</h1>
			<ol>
				<li>로또 1등 당첨자 1000만명</li>
				<li>1년에 300일 휴일</li>
				<li>세컨드 라이프는 화성에서</li>
			</ol>
		</div>
		<div id="sidebar">
			<div id="login">
				<form action="loginOk.jsp">
					<table>
						<tr>
							<td>ID</td>
							<td><input type="text" name="id" id="" /></td>
						</tr>
						<tr>
							<td>PW</td>
							<td><input type="text" name="pw" id="" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="로그인" />
								<input type="button" value="회원가입" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div id="footer">
			<span>짝퉁네이버</span> 없는거 빼고 다 있음 copyright &copy; <br/>
			1990-2024 all right reserved.
		</div>
	</div>
</body>
</html>