package kr.co.jhata.web.control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhata.web.action.Action;
import kr.co.jhata.web.action.DeleteAction;
import kr.co.jhata.web.action.DetailAction;
import kr.co.jhata.web.action.ListAction;
import kr.co.jhata.web.action.ModifyAction;
import kr.co.jhata.web.action.ModifyFormAction;
import kr.co.jhata.web.action.WriteAction;
import kr.co.jhata.web.action.WriteFormAction;

// localhost:8080/board_mvc/board.do?cmd=list 목록
@WebServlet("/board.do")
public class Mycontroller extends HttpServlet{
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// cmd 파라미터 값 가져오기
		String cmd =req.getParameter("cmd");
		String url="";
		Action action = null;
		if(cmd == null || cmd.equals("list")) {
			action = new ListAction();			
		}else if(cmd.equals("write")) {
			action = new WriteFormAction();
		}else if(cmd.equals("writeOk")) {
			action = new WriteAction();
		}else if(cmd.equals("detail")) {
			action = new DetailAction();
		}else if(cmd.equals("modify")) {
			action = new ModifyFormAction();
		}else if(cmd.equals("modifyOk")) {
			action = new ModifyAction();
		}else if(cmd.equals("delete")) {
			action = new DeleteAction();
		}
		
		
		url = action.execute(req, resp);
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
}
