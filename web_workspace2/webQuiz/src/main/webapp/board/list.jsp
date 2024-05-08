<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style type="text/css">
	a {
		text-decoration: none;
		color: black;
	}
</style>
</head>
<body>
	<%
		BoardDAO dao = new BoardDAO();
		String cp = request.getParameter("cp");
		int currentPage = 0;
		
		// 현재 출력할 페이지 번호가 존재하는지?
		if (cp != null) { // 존재할 경우 형변환
			try {
				currentPage = Integer.parseInt(cp);
				
			} catch(IllegalArgumentException e) {
				
			}
		} else { // 존재하지 않을 경우(=최초 접속) 첫페이지로 세팅
			currentPage = 1;
		}
		
		// 페이지 당 출력 건수: 20
		int recordPerPage = 20;
		
		// 총 게시물 수
		int totalCount = dao.getTotalCount();
		
		// 총 페이지 수 계산
		// (총 게시물 건수) / (페이지 당 출력 건수)
		// 나머지가 있을 경우에는 몫 + 1, 나머지가 없을 경우에는 몫 그대로
		int totalPage = (totalCount % recordPerPage == 0) ? 
						 totalCount / recordPerPage : totalCount / recordPerPage + 1;
		
		// 화면에 보여줄 행 번호 범위
		// 페이지가 1일 경우: 1 ~ 20
		// 페이지가 2일 경우: 21 ~ 40
		// 페이지가 5일 경우: 81 ~ 121
		// 페이지가 cp일 경우: (cp-1)+(페이지 당 출력 건수) + 1 ~ cp*(페이지 당 출력 건수)
		
		// 시작 행번호
		int startNo = (currentPage - 1) * recordPerPage + 1;
		// 끝 행번호
		int endNo = currentPage * recordPerPage;
		
		System.out.println("=============== list.jsp ===============");
		System.out.println("cp: " + cp);
		System.out.println("currentPage: " + currentPage);
		System.out.println("recordPerPage: " + recordPerPage);
		System.out.println("totalCount: " + totalCount);
		System.out.println("totalPage: " + totalPage);
		System.out.println("startNo: " + startNo);
		System.out.println("endNo: " + endNo);
		System.out.println("========================================");
	%>
	<div class="container">
		<h2>게시판</h2>
		
		<a href="write.jsp" class="btn btn-primary">글쓰기</a>
		
		<table class="table table-hover">
			<tr>
				<th>게시물번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
				
		<% 
			
			ArrayList<BoardVO> list = dao.selectAll(startNo, endNo);
			for (BoardVO vo : list) {
		%>				
				<tr>
					<td><%= vo.getBno() %></td>
					<td><a href="detail.jsp?bno=<%= vo.getBno() %>"><%= vo.getTitle() %></a></td>
					<td><%= vo.getWriter() %></td>
					<td><%= vo.getHits() %></td>
				</tr>
		<%
			}
			
			// 페이징 버튼 생성
			// 페이징에 표시할 버튼 수: 5개
			// ( 이전 2개 - 현재 - 이후 2개 )
			// => 홀수일 경우 (n-1)/2 만큼 앞뒤 페이지 버튼 표시
			int startPage = 1;
			int endPage = totalPage;
			
			// 현재 페이지가 3 이하일 경우(=이전 2개가 아닐 경우) (n-1)/2+1
			//   전체 페이지가 5 이상일 경우(n)
			//   : 뒤를 5까지 출력 n
			//   전체 페이지가 5 미만인 경우 n
			//   : 뒤를 전체 페이지까지 출력
			
			
			// 현재 페이지가 (마지막 페이지 - 3) 이상인 경우 마지막 페이지 - ((n-1)/2+1)
			//   마지막 페이지까
		%>
			<tr>
				<td colspan="4">
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>