package ex3;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/mvc/mc			==> ex3/hello.jsp (안녕하세요)
// localhost:8080/mvc/mc?type=hello ==> ex3/hello.jsp
// localhost:8080/mvc/mc?type=ip 	==> ex3/ip.jsp (접속IP: XXX.XXX.XXX.XXX)
// localhost:8080/mvc/mc?type=now 	==> ex3/now.jsp 
// 						 NowCommand  execute()	(msg : 오늘 현재 시간 출력)
// localhost:8080/mvc/mc?type=dept 	==> ex3/dept.jsp 
// 						 DeptCommand  execute()	(list : 부서의 목록)
// JSTL, EL ==> 표만들기

@WebServlet("/mc")
public class MyController3 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String type = req.getParameter("type");
		String url = "";
		String msg = "";
		
		if (type == null || type.equals("hello")) {
//			url = "ex3/hello.jsp";
//			msg = "안녕하세요";
//			req.setAttribute("msg", msg);
			
			HelloCommand hc = new HelloCommand();
			url = hc.execute(req, resp);
			
		} else if (type.equals("ip")) {
//			url = "ex3/ip.jsp";
//			msg = "접속IP : " + req.getRemoteAddr();
//			req.setAttribute("msg", msg);
			
			IPCommand ic = new IPCommand();
			url = ic.execute(req, resp);
		} else if (type.equals("now")) {
			NowCommand nc = new NowCommand();
			url = nc.execute(req, resp);
		} else if (type.equals("dept")) {
			DeptCommand dc = new DeptCommand();
			url = dc.execute(req, resp);
		}
		
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
}
