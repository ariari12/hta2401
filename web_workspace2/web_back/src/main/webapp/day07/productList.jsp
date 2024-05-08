<%@page import="vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productList.jsp</title>
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	#container {
		width: 1000px;
		margin: auto;
	}
	img {
		width: 200px;
		height: 200px;
	}
	p {
		text-align: center;
	}
	
	.wrap {
		width: 200px;
		border: 1px solid red;
		float: left;
		padding: 20px;
		margin: 2px;
	}
	.del {
		text-decoration: line-through;
	}
</style>
</head>
<body>
	<div id="container">
		<%
			ProductDAO dao = new ProductDAO();
			ArrayList<ProductVO> list = dao.selectAll();
			
			for(ProductVO vo : list) {
				// 1. 상품명 구하기
				System.out.println("상품명: " + vo.getPname());
				// 2. 상품명의 길이
				System.out.println("상품길이: " + vo.getPname().length());
 				// 3. 이 길이가 15자 이상이면 15자까지만 출력하게
 				System.out.println(
				(vo.getPname().length() >= 12) ? vo.getPname().subSequence(0, 12) + "..." :
					vo.getPname());
 				vo.setPname((vo.getPname().length() >= 12) ? vo.getPname().subSequence(0, 12) + "..." :
					vo.getPname());
 				
 				// 4. 상품명의 길이가 15자까지
		%>
				<div class="wrap">
					<a href="productDetail.jsp?pno=<%= vo.getPno() %>">
						<img src="<%= vo.getImgfile() %>" alt="" />
					</a>
					<p><%= vo.getPname() %></p>
					<p class="del"><%= vo.getPrice() %></p>
					<p>대박할인</p>
					<p><%= vo.getDcratio() %>%</p>
					<p><%= Math.round(vo.getPrice() * (1 - vo.getDcratio() * 0.01)) %>원</p>
					<p>
						<input type="button" value="즉시구매" />
						<input type="button" value="장바구니" />
					</p>
				</div>
		<%
			}
		%>
	</div>
</body>
</html>