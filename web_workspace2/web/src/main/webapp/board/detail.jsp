<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="../js/summernote-ko-KR.js"></script>
<script type="text/javascript">
	$(() => {
		$(".summernote").summernote({
			// 에디터 높이 
			  height: 150, 
			  // 에디터 한글 설정 
			  lang: "ko-KR", 
			  focus : true, 
			  toolbar: [ 
				    // 글꼴 설정 
				    ['fontname', ['fontname']], 
				    // 글자 크기 설정 
				    ['fontsize', ['fontsize']], 
				    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기 
				    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']], 
				    // 글자색 
				    ['color', ['forecolor','color']], 
				    // 표만들기 
				    ['table', ['table']], 
				    // 글머리 기호, 번호매기기, 문단정렬 
				    ['para', ['ul', 'ol', 'paragraph']], 
				    // 줄간격 
				    ['height', ['height']], 
				    // 그림첨부, 링크만들기, 동영상첨부 
				    ['insert',['picture','link','video']], 
				    // 코드보기, 확대해서보기, 도움말 
				    ['view', ['codeview','fullscreen', 'help']] 
				  ], 
				  // 추가한 글꼴 
				fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'], 
				 // 추가한 폰트사이즈 
				fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'] 
		});
	})
</script>
</head>
<body>
	<%
		// 1. 전달 받은 파라미터의 값 가져오기
		String b = request.getParameter("bno");
	
		// 2. b null이 아니면
		if (b != null) {
			// 3. 숫자로 형변환
			try {
				int bno = Integer.parseInt(b);
				
				// 4. dao 객체
				BoardDAO dao = new BoardDAO();
				dao.raiseHits(bno); // hits 값을 1증가
				
				// 5. dao를 통해서 지정한 게시물을 가져오기
				// dao = new BoardDAO();
				BoardVO vo = dao.getOne(bno); 
				
				if (vo == null) { throw new NumberFormatException(); }
	%>
	<div class="container">
	<h2>상세보기</h2>
	
	<!-- table>tr>(th+td)*2 -->
	<table class="table table-hover">
		<tr>
			<th>작성자</th>
			<td><%= vo.getWriter() %></td>
			<th>조회수</th>
			<td><%= vo.getHits() %></td>
			<th>작성일시</th>
			<td><%= vo.getRegdate() %></td>
		</tr>
		<tr>
			<th colspan="2">제목</th>
			<td colspan="4"><%= vo.getTitle() %></td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
			<td colspan="4">
				<textarea class="summernote" name="" id="" cols="30" rows="10"><%= vo.getContents() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<a class="btn btn-primary" href="list.jsp">목록</a>
				<a class="btn btn-success" href="modify.jsp?bno=<%= bno %>">수정</a>
				<a class="btn btn-danger" href="deleteOk.jsp?bno=<%= bno %>">삭제</a>
			</td>
		</tr>
	</table>
	</div>
	<%
			} catch (NumberFormatException e) {
	%>
				<form action="" name="frm"></form>
				<script type="text/javascript">
					alert("유효한 게시물 번호가 아닙니다. 목록 화면으로 돌아갑니다.");
					document.frm.action="list.jsp";
					document.frm.submit();
				</script>
	<%
			}
		}
	%>
</body>
</html>