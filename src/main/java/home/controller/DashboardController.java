package home.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import home.common.Constants;
import home.common.Utils;
import home.dao.PriceDao;
import home.model.HomePrice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "client_dashboard", value = "/client/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			int userId = session != null ? (int) session.getAttribute("userId") : 0;
			PriceDao priceDao = new PriceDao();
			String type = request.getParameter("type");
			System.out.println("Type: " + type);
			int currentPage;
			List<HomePrice> homePriceList;
			if ("0".equals(type)) {
				currentPage = 1;
				homePriceList = priceDao.searchAllPrice(userId, 1, Constants.LIMIT_PAGE, "", "", "");
				System.out.println("Search home price list, size: " + homePriceList.size());

				int size = priceDao.getTotalRecord(userId, "", "", "");
				System.out.println("Total record: " + size);
				int totalPage = Utils.getTotalPage(size);
				request.setAttribute("homePriceList", homePriceList);
				System.out.println("Total page: " + totalPage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("/pages/client_dashboard.jsp").forward(request, response);
			} else {
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
}
