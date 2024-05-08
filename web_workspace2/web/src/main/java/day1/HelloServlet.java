package day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet
// 웹에서 동작하는 자바 프로그램
// 서블릿 작성 순서
// 1. HttpServlet 상속
// 2. doGet(), doPost() override
// 3. @WebServlet()
// localhost:8080/web/hello.do
@WebServlet("/hello.do")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() method");
		
		// req: 사용자의 요청 객체
		// resp: 서버의 응답을 객체로 만든 것
		
		// 응답 객체로부터 PrintWriter 객체를 얻어오기
		
		PrintWriter out =  resp.getWriter();
		
		// 사용자 브라우저에 이 내용을 전달
		out.println("<html>");
		out.println("<head><title>My Servlet</title></head>");
		out.println("<body>");
		for(int i = 0; i < 100; i++) {
			out.println("<h2>Hello Servlet World</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
