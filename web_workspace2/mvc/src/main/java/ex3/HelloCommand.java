package ex3;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloCommand {

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("msg", "이건 모델에서 전달해주는 데이터");
		// 리턴값은 찾아가야할 JSP의 위치를 반환
		return "ex3/hello.jsp";
	}

}
