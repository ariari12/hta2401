package kr.co.jhata.web.action;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhta.web.dao.BoardDAO;
import kr.co.jhta.web.vo.BoardVO;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectAll(1, 20);
		
		req.setAttribute("list", list);
		return "views/list.jsp";
	}

}
