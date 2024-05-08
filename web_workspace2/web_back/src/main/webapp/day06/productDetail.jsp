<%@page import="vo.ProductVO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productDetail.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<%
		// http://localhost:8080/web/day06/productDetail.jsp&pno=25
	
		// pno라는 파라미터 값 가져오기
		String p = request.getParameter("pno");
	
		// out.println("pno: " + p);
		// 파라미터 값이 null이 아니라면
		if (p != null) {
			// productDAO 객체 생성하기
			ProductDAO dao = new ProductDAO();
			// p라는 문자열 변수의 값을 숫자로 형변환
			int pno = Integer.parseInt(p);
			// db에서 해당 상품정보 가져오기
			ProductVO vo = dao.getOne(pno);
			
			// 잘 가져온 것인지 확인
			// out.println("vo: " + vo.getPname());
	%>
		<div class="container">
			<table class="table table-hover">
				<tr>
					<th>상품번호</th>
					<td><%= vo.getPno() %></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><%= vo.getPname() %></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><%= vo.getPrice() %></td>
				</tr>
				<tr>
					<th>할인율</th>
					<td><%= vo.getDcratio() %></td>
				</tr>
				<tr>
					<th>상품 설명</th>
					<td><%= vo.getProdesc() %></td>
				</tr>
				<tr>
					<th>재고</th>
					<td><%= vo.getQty() %></td>
				</tr>
				<tr>
					<th>이미지 파일1</th>
					<td> <img src="<%= vo.getImgfile() %>" alt="" /> </td>
				</tr>
				<tr>
					<th>이미지 파일2</th>
					<td> <img src="<%= vo.getBigfile() %>" alt="" /> </td>
				</tr>
			</table>
			
			<div id="btn">결제하기</div>
		</div>
	<%
		}
	%>
</body>
</html>