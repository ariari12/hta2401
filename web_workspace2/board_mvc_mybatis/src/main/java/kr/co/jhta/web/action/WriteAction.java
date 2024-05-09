package kr.co.jhta.web.action;

import java.io.UnsupportedEncodingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhta.web.dao.BoardDAO;
import kr.co.jhta.web.vo.BoardVO;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			
			String write = req.getParameter("writer");
			String title = req.getParameter("title");
			String contents = req.getParameter("contents");
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO();
			vo.setWriter(write);
			vo.setTitle(title);
			vo.setContents(contents);
			dao.addOne(vo);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "board.do?cmd=list";
	}

}
