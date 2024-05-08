package ex3;

import java.util.ArrayList;

import dao.DeptDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.DeptVO;

public class DeptCommand implements ActionCommand{

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list = dao.selectAll();
		
		req.setAttribute("list", list);
		
		return "ex3/dept.jsp";
	}

}
