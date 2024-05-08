<%@page import="java.util.ArrayList"%>
<%@page import="vo.ProductVO"%>
<%@page import="dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pname = request.getParameter("txt");	

	if (pname != null) {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductVO> list = dao.findByName(pname);
		
		if(list != null) {
			// 여러개 출력
			/* for(ProductVO vo : list) {
				out.println("<img src=' "+ vo.getImgfile() +"' alt='" + vo.getPname() 
							+ " width='200px', height='200px' />");
				out.println("<br>");
				// out.println(vo.getImgfile());
			} */
			
			// 상위 1개만 출력(html 태그)
			out.println("<img src=' "+ list.get(0).getImgfile() 
							+"' alt='" + list.get(0).getPname() 
							+ " width='200px', height='200px' />");
			
			// 상위 1개만 출력(img 경로만)
			// out.println(list.get(0).getImgfile());
		}
	}
%>