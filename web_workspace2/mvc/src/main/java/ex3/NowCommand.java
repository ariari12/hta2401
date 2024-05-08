package ex3;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NowCommand implements ActionCommand{

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String time = sdf.format(new Date());
		
		req.setAttribute("msg", time);
		return "ex3/now.jsp";
	}

}
