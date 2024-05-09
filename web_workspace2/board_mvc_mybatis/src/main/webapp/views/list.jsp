<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<div class="container">	
		<h2>�Խ���</h2>
		<h2>list.jsp</h2>
		
		<%-- <h5>�ѰԽù��� : ${totalCount }</h5>
		<h5>��������Խù��� : ${recordPerPage }</h5>
		<h5>���������� : ${currentPage }</h5>
		<h5>���۹�ȣ : ${startNo }</h5>
		<h5>����ȣ : ${endNo }</h5>
		<h5>������������ȣ : ${startPage }</h5>
		<h5>�������� : ${endPage}</h5>
		<h5>���� �������� ������? : ${isPrev}</h5>
		<h5>���� �������� ������? : ${isNext}</h5> --%>
		
		
		<a href="board.do?cmd=write" class="btn btn-primary">�۾���</a>
		<table class="table table-striped">
			<tr>
				<th>�Խù���ȣ</th>
				<th>�ۼ���</th>
				<th>����</th>
				<th>��ȸ��</th>
			</tr>
			<c:forEach var="dto" items="${list}" >
				<tr>
					<td>${dto.bno}</td>
					<td>${dto.writer}</td>
					<td><a href="board.do?cmd=detail&bno=${dto.bno}">${dto.title}</a></td>
					<td>${dto.hits}</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4">
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
					  	<c:if test="${isPrev}">
					  		<li class="page-item"><a class="page-link" href="board.do?cmd=list&cp=${currentPage-1}">Previous</a></li>
					  	</c:if>
					    					   
					    <c:forEach var="i" begin="${startPage}" end="${endPage}">
							<li class="page-item"><a class="page-link" 
								href="board.do?cmd=list&cp=${i}">[${i}]</a></li>
						</c:forEach>
						
						<c:if test="${isNext}">
					  		<li class="page-item"><a class="page-link" href="board.do?cmd=list&cp=${currentPage+1}">Next</a></li>
					  	</c:if>
					    
					  </ul>
					</nav>
					<%-- <c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="board.do?cmd=list&cp=${i}">[${i}]</a>
					</c:forEach> --%>
				</td>
				
			</tr>
		</table>
	</div>
	
	
	
	
</body>
</html>