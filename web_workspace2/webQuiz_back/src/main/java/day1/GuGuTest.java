package day1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/printGuGuDan.do")
public class GuGuTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = 'UTF-8'>");
		out.println("<title> </title>");
		out.println("</head>");
		out.println("<body>");
		for (int i = 2; i <= 9; i++) {
			out.print("<h3> ");
			for (int j = 1; j <= 9; j++) {
				out.print(i + " x " + j + " = " + i * j + "&nbsp&nbsp&nbsp&nbsp");
			}
			out.println(" </h3>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
