<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>receiveMsg.jsp</title>
</head>
<body>
	<%
		// 모든 코드가 _jspServie() <--
		// _jspService()
		// 내부적으로 컴파일되는 위치
		// : [워크스페이스]\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\web\org\apache\jsp\day02
		String msg = request.getParameter("msg");
		
		// 브라우저에 h2 태그로 출력
		out.println("<h2>" + msg + "</h2>");
	%>
</body>
</html>