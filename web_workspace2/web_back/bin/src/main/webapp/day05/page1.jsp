<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page1.jsp</title>
</head>
<body>
	<h1>page1.jsp</h1>
	
	<%
		// jsp ==> _jspService() 안쪽으로 코드가 들어가게 된다.
		
	 	// _jspService의 지역변수: 내장객체
	 	// 내부적으로 선언되어 있어 따로 선언하지 않아도 되는 객체
	 	
	 	// out, request, response
	 	
	 	// pageContext: 현재 JSP 페이지의 컨텍스트를 나타내는 객체
	 	// request: 사용자의 요청을 객체화 시켜 놓은것
	 	// session: 웹 브라우저의 정보를 유지하기 위한 세션정보를 저장하고 있는 객체
	 	// application: 웹 application context의 정보를 저장하고 있는 객체
	 	
	 	// 내장객체 속성, 값 지정
	 	
	 	pageContext.setAttribute("id1", "aaa");
		request.setAttribute("id2", "bbb");
		session.setAttribute("id3", "ccc");
		application.setAttribute("id4", "ddd");
		
		// 부모 참조변수 = 자식의 참조값
		// 자기가 알고 있는 메서드만 호출
		Object obj1 = pageContext.getAttribute("id1");
		
		out.println("obj1: " + obj1);
		// 한글자만 출력
		String id1 = (String)obj1;
		/* out.println("<br>");
		out.println(str1.substring(0, 1)); */
		
		Object obj2 = request.getAttribute("id2");
		String id2 = (String)obj2;
		
		Object obj3 = session.getAttribute("id3");
		String id3 = (String)obj3;
		
		Object obj4 = application.getAttribute("id4");
		String id4 = (String)obj4;
		
		out.println("<h3> pageContext: " + id1 + "</h3>");
		out.println("<h3> request: " + id2 + "</h3>");
		out.println("<h3> session: " + id3 + "</h3>");
		out.println("<h3> application: " + id4 + "</h3>");
		
		// forward 방식으로 page2.jsp 이동
		RequestDispatcher rd = request.getRequestDispatcher("page2.jsp");
		// rd.forward(request, response);
		
		response.sendRedirect("page2.jsp");
		
		
	%>
</body>
</html>