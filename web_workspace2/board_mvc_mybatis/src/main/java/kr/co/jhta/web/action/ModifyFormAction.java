package kr.co.jhta.web.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhta.web.dao.BoardDAO;
import kr.co.jhta.web.vo.BoardVO;

public class ModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String b =req.getParameter("bno");
		
		if(b!=null) {
			int bno = Integer.parseInt(b);
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);					
			req.setAttribute("vo", vo);
		}
		
		
		
		return "views/modifyForm.jsp";
	}

}
