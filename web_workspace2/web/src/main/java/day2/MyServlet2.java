package day2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/myServlet2.do")
public class MyServlet2 extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출중");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 호출중");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출중");
	}
	
	

}
