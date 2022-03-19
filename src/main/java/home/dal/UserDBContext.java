package home.dao;

import home.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {

	public User login(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		User user = null;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE username = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String loginName = rs.getString("username");
				String pass = rs.getString("password");
				String description = rs.getString("description");
				int role = rs.getInt("role");
				int active = rs.getInt("active");
				user = new User(id, loginName, pass, description, role, active);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return user;
	}

	public List<User> findAll() {
		Connection connection = null;
		PreparedStatement ps = null;
		List<User> users = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE role = 1 ORDER BY id ASC");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginName = rs.getString("username");
				String pass = rs.getString("password");
				String description = rs.getString("description");
				int role = rs.getInt("role");
				int active = rs.getInt("active");
				User user = new User(id, loginName, pass, description, role, active);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return users;
	}
}
