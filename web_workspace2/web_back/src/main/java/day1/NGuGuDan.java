package day1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/web/gugudan2.do

@WebServlet("/gugudan2.do")
public class NGuGuDan extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 ===> 서버 요청 : req
		String dan = req.getParameter("dan");
		System.out.println("dan : " + dan);
		
		// 문자를 숫자로 형변환
		int d = Integer.parseInt(dan);
		
		// 출력객체를 얻어오기
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title> 구구단 " + d + " 단 </title>");
		out.println("</head>");
		out.println("<body>");
		for (int i = 1; i <= 9; i++) {
			out.println("<h3>" + d + " x " + i + " = " + (d * i) + "</h3>");
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 ===> 서버 요청 : req
				String dan = req.getParameter("dan");
				System.out.println("dan : " + dan);
				
				// 문자를 숫자로 형변환
				int d = Integer.parseInt(dan);
				
				// 출력객체를 얻어오기
				PrintWriter out = resp.getWriter();
				
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("<title> 구구단 " + d + " 단 </title>");
				out.println("</head>");
				out.println("<body>");
				for (int i = 1; i <= 9; i++) {
					out.println("<h3>" + d + " x " + i + " = " + (d * i) + "</h3>");
				}
				out.println("</body>");
				out.println("</html>");
	}
}
