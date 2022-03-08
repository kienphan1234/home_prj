package home.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String description;
	private int role;
	private int active;

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User(int id, String username, String password, String description, int role, int active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.description = description;
		this.role = role;
		this.active = active;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
