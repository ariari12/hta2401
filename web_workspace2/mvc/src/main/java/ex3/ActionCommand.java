package ex3;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ActionCommand {
	public String execute(HttpServletRequest req, HttpServletResponse resp);

}
