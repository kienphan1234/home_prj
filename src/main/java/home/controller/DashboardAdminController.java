package home.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import home.common.Constants;
import home.common.Utils;
import home.dao.PriceDao;
import home.dao.UserDao;
import home.model.HomePrice;
import home.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "admin_dashboard", value = "/admin/dashboard")
public class DashboardAdminController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			PriceDao priceDao = new PriceDao();
			String type = request.getParameter("type");
			System.out.println("Type: " + type);
			int currentPage;
			List<HomePrice> homePriceList;
			if ("0".equals(type)) {
				UserDao userDao = new UserDao();
				List<User> userList = userDao.findAll();
				request.setAttribute("roomList", userList);

				currentPage = 1;
				homePriceList = priceDao.searchAllPrice(0, 1, Constants.LIMIT_PAGE, "", "", "");
				System.out.println("Search home price list, size: " + homePriceList.size());

				int size = priceDao.getTotalRecord(0, "", "", "");
				System.out.println("Total record: " + size);
				int totalPage = Utils.getTotalPage(size);
				request.setAttribute("homePriceList", homePriceList);
				System.out.println("Total page: " + totalPage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("/pages/admin_dashboard.jsp").forward(request, response);
			} else {
				int userId = Integer.parseInt(request.getParameter("room"));
				int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String status = request.getParameter("status");
				currentPage = pageNumber;
				int end = pageNumber * Constants.LIMIT_PAGE;
				int start = end - Constants.LIMIT_PAGE + 1;
				homePriceList = priceDao.searchAllPrice(userId, start, end, year, month, status);
				System.out.println("Search home price list, size: " + homePriceList.size());

				int size = priceDao.getTotalRecord(userId, year, month, status);
				System.out.println("Total record: " + size);

				int totalPage = Utils.getTotalPage(size);
				System.out.println("Total page: " + totalPage);

				JsonObject resp = new JsonObject();
				JsonArray jsonArray = new JsonArray();
				for (HomePrice homePrice : homePriceList) {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("id", homePrice.getId());
					jsonObject.addProperty("roomPrice", homePrice.getRoomPrice());
					jsonObject.addProperty("electricPrice", homePrice.getElectricPrice());
					jsonObject.addProperty("waterPrice", homePrice.getWaterPrice());
					jsonObject.addProperty("internetPrice", homePrice.getInternetPrice());
					jsonObject.addProperty("garbagePrice", homePrice.getGarbagePrice());
					jsonObject.addProperty("livingPrice", homePrice.getLivingPrice());
					jsonObject.addProperty("total", homePrice.getTotal());
					jsonObject.addProperty("note", homePrice.getNote());
					jsonObject.addProperty("status", homePrice.getStatus());
					jsonObject.addProperty("createdAt", homePrice.getCreatedAt());
					jsonObject.addProperty("depositedAt", homePrice.getDepositedAt());
					jsonObject.addProperty("roomDescription", homePrice.getUser().getDescription());
					jsonArray.add(jsonObject);
				}
				resp.add("homePriceList", jsonArray);
				resp.addProperty("totalPage", totalPage);
				resp.addProperty("currentPage", currentPage);
				PrintWriter output = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				output.write(resp.toString());
				output.flush();
			}
		} catch (Exception ex) {
			System.out.println("err: " + ex);
			log(ex.getMessage());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			PriceDao priceDao = new PriceDao();
			boolean result = priceDao.updateStatus(id);
			String code;
			JsonObject jsonObject = new JsonObject();
			if (result) {
				code = "200";
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				jsonObject.addProperty("depositedDate", format.format(new Date()));
			} else {
				code = "500";
			}
			jsonObject.addProperty("code", code);

			PrintWriter output = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			output.write(jsonObject.toString());
			output.flush();
		} catch (Exception ex) {
			System.out.println("err: " + ex);
			log(ex.getMessage());
		}
	}
}
