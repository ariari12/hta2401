package day1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cumValue.do")
public class CumValue extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int val = Integer.parseInt(req.getParameter("cumVal"));
		int sum = 0;

		for (int i = 1; i <= val; i++) {
			sum += i;
		}

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = 'UTF-8'>");
		out.println("<title> </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3> 1부터 " + val + "까지의 누적합: " + sum + " </h3>");
		out.println("</body>");
		out.println("</html>");
	}

}
