package day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8080/web/today.do

// 2024년 4월 17일  9시 30분 20초
@WebServlet("/today.do")
public class Today extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = 'UTF-8'>");
		out.println("<title> 지금 시간은 </title>");
		out.println("</head>");
		out.println("<body>");
		// 자바의 문법
		Date d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 dd일 hh시 mm분 ss초");
		String time = sdf.format(d);
		
		out.println("<h3>" + time + "</h3>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
