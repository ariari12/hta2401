package ex1;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿 작성 방법
// 1. HttpServlet 상속
// 2. doGet(), doPost() override
// 3. @WebServlet

// localhost:8080/mvc/MyController?cmd=hello 요청 == hello.jsp 파일로 이동
@WebServlet("/MyController")
public class MyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 2. 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		
		System.out.println("cmd: " + cmd);
		
		// 찾아가야할 페이지의 url을 갖는 변수
		String url = "";
		
		// 전달데이터를 담을 변수
		String msg = "";
		
		// if (T || F)
		// cmd가 널이거나 cmd가 hello라는 문자열과 동일한 값을 갖는다면
		if(cmd == null || cmd.equals("hello")) {
			msg = "안녕하세요";
			// 요청 객체 속성으로 담기
			// req.setAttribute(이름, 값);
			req.setAttribute("msg", msg);
			url = "hello.jsp";
		}
		
		// forward 방식으로 hello.jsp로 이동
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
}
