package home.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin_dashboard", value = "/admin/dashboard")
public class AdminController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/pages/admin_dashboard.jsp").forward(request, response);
		} catch (Exception ex) {
			log(ex.getMessage());
		}
	}
}
