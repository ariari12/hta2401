package ex2;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/mvc/MyControl ==> ex2/korea.jsp : 안녕하세요 
// localhost:8080/mvc/MyControl?cmd=kr ==> ex2/korea.jsp : 안녕하세요
// localhost:8080/mvc/MyControl?cmd=cn ==> ex2/cn.jsp : 니하오
// localhost:8080/mvc/MyControl?cmd=jp ==> ex2/jp.jsp : 곰방와

@WebServlet("/MyControl")
public class MyController2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 2. 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";
		String msg = "";
		
		// 3. 파라미터 값이 무엇인지에 따라
		if (cmd == null || cmd.equals("kr")) {
			// 4. 이동할 url과 값을 결정, 전달 메세지 속성에 담기
			url = "ex2/korea.jsp";
			msg = "안녕하세요";
			req.setAttribute("msg", msg);
		} else if (cmd.equals("cn")) {
			url = "ex2/cn.jsp";
			msg = "니하오";
			req.setAttribute("msg", msg);
		} else if (cmd.equals("jp")) {
			url = "ex2/jp.jsp";
			msg = "곰방와";
			req.setAttribute("msg", msg);
		}
		
		
		// 5. forward
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
}
