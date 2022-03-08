package home.controller;

import home.common.Utils;
import home.dao.UserDao;
import home.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		} catch (Exception ex) {
			log(ex.getMessage());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("Login with user: " + username);
			if (username.isEmpty() || password.isEmpty()) {
				req.setAttribute("errorLogin", "Vui lòng nhập tên đăng nhập hoặc mật khẩu");
				req.getRequestDispatcher("/page/login.jsp").forward(req, resp);
				return;
			}
			UserDao userDao = new UserDao();
			User user = userDao.login(username);
			if (user == null) {
				req.setAttribute("errorLogin", "Tên đăng nhập hoặc mật khẩu không đúng");
				req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
				return;
			}
			String requestHash = Utils.getMD5(username + password);

			boolean result = requestHash.equals(user.getPassword());
			if (result) {
				System.out.println("Login success");
				HttpSession session = req.getSession(false);
				session.setAttribute("userId", user.getId());
				if (user.getRole() == 0) {
					resp.sendRedirect(req.getContextPath().concat("/admin/dashboard").concat("?type=0"));
				} else {
					resp.sendRedirect(req.getContextPath().concat("/client/dashboard").concat("?type=0"));
				}
			} else {
				System.out.println("Login fail");
				req.setAttribute("errorLogin", "Tên đăng nhập hoặc mật khẩu không đúng");
				req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
			}
		} catch (Exception ex) {
			log(ex.getMessage());
		}
	}
}
