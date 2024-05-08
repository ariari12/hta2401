<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
<style>
	* {
		margin: 0;
		padding: 0;
	}
	span {
		display: inline-block;
		width: 150px;
	}
	div#container {
		margin-left: 50px;
	}
	div#button {
		text-align: center;
	}
</style>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String identify1 = request.getParameter("identify1");
		String identify2 = request.getParameter("identify2");
		String pw = request.getParameter("pw");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String address = request.getParameter("address");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		// String emailFmt = request.getParameter("emailFmt");
		String gender = request.getParameter("gender");
		String[] hobby = request.getParameterValues("hobby");
	%>
	<div id="container">
		<span>ID</span> 
		: <%= id %> <br>
		
		<span>NAME</span> 
		: <%= name %> <br>
		
		<span>주민등록번호</span> 
		: <%= identify1 %>-<%= identify2 %> <br>
					 
		<span>비밀번호</span> 
		: <%= pw %> <br>
		
		<span>전화번호</span> 
		: <%= tel1 %>-<%= tel2 %>-<%= tel3 %> <br>
				 
		<span>주소</span> 
		: <%= address %> <br>
		
		<span>EMAIL</span> 
		: <%= email1 %>@<%= email2 %> <br>
			  
		<span>성별</span> 
		: <%= gender %> <br>
			  
		<span>취미</span> 
		: <%
			for(int i = 0; i < hobby.length; i++) {
				out.print(hobby[i]);
				if (i != hobby.length - 1) {
					out.print(", ");
				}
			}
		%> <br>
	</div>
</body>
</html>