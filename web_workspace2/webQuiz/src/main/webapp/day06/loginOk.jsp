<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout.jsp</title>
<script type="text/javascript">
	<%
		Object obj = session.getAttribute("MemberVO");
		MemberVO vo = (MemberVO)obj;
		
		// 로그인 검증
		if (vo == null) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDAO dao = new MemberDAO();
			vo = dao.searchUser(id, pw);
			if (vo != null) {
				session.setAttribute("MemberVO", vo);
			} else {
			}
		} else { // 로그아웃
			session.invalidate();
		}
		
		
		response.sendRedirect("layout.jsp");
	%>
</script>
<link rel="stylesheet" href="../css/main_2024_04_23.css" />
</head>
<body>

</body>
</html>