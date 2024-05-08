<%@page import="dao.ProductDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productRegOk.jsp</title>
</head>
<body>
	<%
		String saveDir = application.getRealPath("/upload");
		out.println(saveDir);
		
		int maxFilSize = 1024*1024*100; // 100MB
	
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxFilSize,
								"UTF-8", new DefaultFileRenamePolicy());
		
		String pname = request.getParameter("pname");
		// 패러미터 받아오기(bigfile은 아직 없음)
		String pri;
		String dcrt;
		String prodesc;
		String qt;
		String imgfile;
		String bigfile;
		
		ProductDAO dao = new ProductDAO();
	%>
</body>
</html>