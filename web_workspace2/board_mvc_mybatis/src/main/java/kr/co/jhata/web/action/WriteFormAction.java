package kr.co.jhata.web.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFormAction implements Action{
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "views/writeForm.jsp";
	}

}
