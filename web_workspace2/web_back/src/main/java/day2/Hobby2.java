package day2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hobby2.do")
public class Hobby2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. 파라미터값 가져오기
		String[] hList = req.getParameterValues("hobby");

		// 3. 쓰기 객체
		PrintWriter out = resp.getWriter();

		// 4. 출력
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = 'UTF-8'>");
		out.println("<title> </title>");
		out.println("</head>");
		out.println("<body>");
		for(int i = 0; i < hList.length; i++) {
			out.println("<h3> 당신의 취미는 : " + hList[i] + " 입니다. </h3>");
		}
		// 당신의 취미는 : XXX 입니다.
		out.println("------------------------------------------------");
		for (String hobby : hList) {
			out.println("<h3> 당신의 취미는 : " + hobby + " 입니다. </h3>");
		}
		out.println("------------------------------------------------");
		Arrays.asList(hList).forEach(hobby -> out.println("<h3> 당신의 취미는 : " + hobby + " 입니다. </h3>"));
//		Arrays.stream(hList).forEach(hobby -> out.println("<h3> 당신의 취미는 : " + hobby + " 입니다. </h3>"));
		out.println("</body>");
		out.println("</html>");

	}
}
