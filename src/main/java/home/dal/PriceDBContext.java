package home.dao;

import home.model.HomePrice;
import home.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceDao extends BaseDao {
	public List<HomePrice> searchAllPrice(int userId, int start, int end, String year, String month, String status) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<HomePrice> homePriceList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder();
			if (start != 0 && end != 0) {
				sql.append("SELECT * FROM ( ");
			}
			sql.append("SELECT a.id, a.room_price, a.electric_price, a.water_price, a.internet_price, " +
					"a.garbage_price, a.living_price, a.note, a.total, a.status, FORMAT(a.created_at, 'MM-yyyy') as createdAt, " +
					"FORMAT(a.deposited_at, 'dd/MM/yyyy') as depositedAt, " +
					"b.description, " +
					"ROW_NUMBER() OVER(ORDER BY a.created_at DESC) AS rownumber from home_price a ");
			sql.append("INNER JOIN users b ON a.user_id = b.id ");
			if (userId > 0) {
				sql.append("WHERE b.active = 1 AND b.id = ? ");
			} else {
				sql.append("WHERE b.active = 1 ");
			}
			if (!year.isEmpty()) {
				sql.append("AND FORMAT(a.created_at, 'yyyy') = ? ");
			}
			if (!month.isEmpty()) {
				sql.append("AND FORMAT(a.created_at, 'MM') = ? ");
			}
			if (!status.isEmpty()) {
				sql.append("AND a.status = ? ");
			}
			if (start != 0 && end != 0) {
				sql.append(") c WHERE c.rownumber BETWEEN ? AND ?");
			}
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (userId > 0) {
				ps.setInt(index++, userId);
			}
			if (!year.isEmpty()) {
				ps.setString(index++, year);
			}
			if (!month.isEmpty()) {
				ps.setString(index++, month);
			}
			if (!status.isEmpty()) {
				ps.setInt(index++, Integer.parseInt(status));
			}
			if (start != 0 && end != 0) {
				ps.setInt(index++, start);
				ps.setInt(index++, end);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String roomPrice = rs.getString("room_price");
				String electricPrice = rs.getString("electric_price");
				String waterPrice = rs.getString("water_price");
				String internetPrice = rs.getString("internet_price");
				String garbagePrice = rs.getString("garbage_price");
				String livingPrice = rs.getString("living_price");
				String note = rs.getString("note");
				String total = rs.getString("total");
				int stt = rs.getInt("status");
				String createdAt = rs.getString("createdAt");
				String depositedAt = rs.getString("depositedAt");
				HomePrice homePrice = new HomePrice(id, roomPrice, electricPrice, waterPrice, internetPrice, garbagePrice, livingPrice, total, note, stt, createdAt, depositedAt);
				User user = new User();
				user.setDescription(rs.getString("description"));
				homePrice.setUser(user);
				homePriceList.add(homePrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return homePriceList;
	}

	public int getTotalRecord(int userId, String year, String month, String status) {
		Connection connection = null;
		PreparedStatement ps = null;
		int totalPrice = 0;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(a.id) as total from home_price a ");
			sql.append("INNER JOIN users b ON a.user_id = b.id ");
			if (userId > 0) {
				sql.append("WHERE b.active = 1 AND b.id = ? ");
			} else {
				sql.append("WHERE b.active = 1 ");
			}
			if (!year.isEmpty()) {
				sql.append("AND FORMAT(a.created_at, 'yyyy') = ? ");
			}
			if (!month.isEmpty()) {
				sql.append("AND FORMAT(a.created_at, 'MM') = ? ");
			}
			if (!status.isEmpty()) {
				sql.append("AND a.status = ? ");
			}
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (userId > 0) {
				ps.setInt(index++, userId);
			}
			if (!year.isEmpty()) {
				ps.setString(index++, year);
			}
			if (!month.isEmpty()) {
				ps.setString(index++, month);
			}
			if (!status.isEmpty()) {
				ps.setInt(index++, Integer.parseInt(status));
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return totalPrice;
	}

	public boolean updateStatus(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE home_price SET status = ?, deposited_at = ? WHERE id = ? ");
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, 1);
			ps.setDate(index++, new java.sql.Date(new Date().getTime()));
			ps.setInt(index++, id);
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return result;
	}
}
