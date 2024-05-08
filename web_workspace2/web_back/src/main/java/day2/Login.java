package day2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class Login extends HttpServlet {
	// 200: 정상처리
	// 404: 자원을 못 찾음
	// 405: 방식을 지원하지 않음
	// 500: 서버쪽 프로그램 에러
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 2. 파라미터값 가져오기
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		// id와 pw를 가지고 db에 가서 데이터를 검색후 존재하는지 여부를 판단..
		
		// 3. 쓰기 객체
		PrintWriter out = resp.getWriter();
		
		// 4. 브라우저에 출력
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = 'UTF-8'>");
		out.println("<title> </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2> 당신의 ID : " + id + "</h2>");
		out.println("<h2> 당신의 PW : " + pw + "</h2>");
		out.println("</body>");
		out.println("</html>");
	}
}
