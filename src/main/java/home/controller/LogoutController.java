package home.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogoutController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("userId");
			req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
		}
	}
}
