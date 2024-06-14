package in.BankAppFXMaven.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseDAO {
	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;

	private String url;
	private String user;
	private String password;

	private static DatabaseDAO db = null;
	private DbUserSection dbUser = DbUserSection.getInstance();

	public static DatabaseDAO getInstance() {
		if (db == null) {
			db = new DatabaseDAO();
		}
		return db;
	}

	private DatabaseDAO() {
		connect();
	}

	private void connect() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream("in/BankAppFXMaven/config.properties");
			prop.load(input);

			url = prop.getProperty("db.url");
			user = prop.getProperty("db.user");
			password = prop.getProperty("db.password");
			System.out.println("URL: " + url + ". User: " + user + ". Password: " + password + ".");

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Database Connected.");
			}

			st = connection.createStatement();

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {
		return this.connection;
	}

	/**
	 * @return ArrayList of User
	 */
	public ArrayList<User> getUsers() {

		ArrayList<User> userList = new ArrayList<User>();
		try {
			rs = st.executeQuery("SELECT * FROM user;");
			rs.next();

			userList = dbUser.getUsers(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return userList;
	}
	
	public User getUser(int ID) {

		User user = new User();
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE user_id = " + ID + ";");
			rs.next();

			user = dbUser.getUser(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	/**
	 * @param sqle exception messages to execute if error occurs(breaking down code)
	 */
	protected static void exceptionMessages(SQLException sqle) {
		while (sqle != null) {
			System.out.println("State: " + sqle.getSQLState());
			System.out.println("Message: " + sqle.getMessage());
			System.out.println("Error: " + sqle.getErrorCode());
			sqle = sqle.getNextException();
		}
	}

	public boolean checkLoginCredentials(String email, String password) {
		return dbUser.checkLoginCredentials(email, password);
	}

}
