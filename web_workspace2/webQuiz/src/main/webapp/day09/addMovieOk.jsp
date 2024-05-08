<%@page import="vo.MovieVO"%>
<%@page import="dao.MovieDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMovie.jsp</title>
</head>
<body>
	<%
		String saveDir = application.getRealPath("/upload");
		out.println(saveDir);
		
		int maxFileSize = 1024*1024*30; // 30MB
				
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxFileSize,
									"UTF-8", new DefaultFileRenamePolicy());
		
		String name = mr.getParameter("name");
		String filename = mr.getFilesystemName("filename");
		String src = "../upload/" + filename;
		
		MovieVO vo = new MovieVO(0, name, src);		
		MovieDAO dao = new MovieDAO();
		dao.addOne(vo);
	%>
</body>
</html>