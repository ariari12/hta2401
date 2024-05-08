<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="dao.FileInfoDAO"%>
<%@page import="dao.FileDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result2.jsp</title>
</head>
<body>
	<%
		// upload 디렉토리의 실제 경로 얻어오기
		String saveDir = application.getRealPath("/upload");
	
		out.println(saveDir);
		
		// 30MB
		int maxFileSize = 1024 * 1024 * 30;
		// cos.jar: MultipartRequest
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, 
				maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Enumeration params = mr.getParameterNames();
		
		while(params.hasMoreElements()) {
			String name = (String)params.nextElement();
			System.out.println("name: " + name);
			
			String value = mr.getParameter(name);
			out.println("<h2>" + name + " : " + value + "</h2>");
		}
		
		out.println("<hr>");
		
		FileDAO dao = new FileDAO();
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String contents = mr.getParameter("contents");
		int fileNumber = dao.getNumber();
		
		dao.addOne(fileNumber, title, writer, contents);
		
		Enumeration files = mr.getFileNames();
		
		FileInfoDAO dao2 = new FileInfoDAO();
		
		while(files.hasMoreElements()) {
			String fname = (String) files.nextElement();
			String filename = mr.getFilesystemName(fname);
			String original = mr.getOriginalFileName(fname);
			String type = mr.getContentType(fname);
			
			File file = mr.getFile(fname);
			
			out.println("<h2> 저장 파일명: " + filename + "</h2>");
			out.println("<h2> 실제 파일명: " + original + "</h2>");
			out.println("<h2> 컨텐츠 타입: " + type + "</h2>");
			
			dao2.addFileOne("../upload/" + filename, type, fileNumber);
			
		}
		
	%>
		<a href="view.jsp">이미지 보기</a>
		<a href="dirView.jsp">저장디렉토리의 파일 목록보기</a>
</body>
</html>