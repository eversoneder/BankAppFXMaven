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

	private static DatabaseDAO db;
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

	public boolean checkLoginCredentials(String email, String password) {
		String query = "SELECT * FROM user JOIN login ON user.user_id = login.user_id "
				+ "WHERE user.email = '" + email + "' "
				+ "AND login.password_hash = '" + password + "';";
		
		try {
			rs = st.executeQuery(query);
			rs.next();

			//if query had 1 result, it means the email + password exists
			if (!rs.wasNull()) {
				System.out.println("User: " + user.getClass().getName() + " exists.");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//if no result, it means no email + password exists
		return false;
	}
	
	public BankAccount getUserAcc(User user) {
		
		String query = "";
		BankAccount ba = new BankAccount();
		
		return null;
	}
	
	public boolean createUser() {
		//create user + login + bank account + statement on the database
		
		
		return false;
	}

	public boolean accNumberExists(String randomNumber) {

		String query = "SELECT * FROM bankappfx.bank_account WHERE bank_acc_number = " + randomNumber + ";";

		try {
			rs = st.executeQuery(query);
			rs.next();

			// number already exist (was not null, it was true that accNumberExists)
			if (!rs.wasNull()) {
				System.out.println(randomNumber + " already exists.");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(randomNumber + " does not exist.");
		// number do not exist (was null, 0 records in database, it was false that
		// accNumberExists)
		return false;
	}

	/**
	 * @param query the query to execute (reusable method for SELECT queries)
	 * @return ResultSet of query given
	 * 
	 */
	protected ResultSet executeQueryRS(String query) {

		ResultSet rs = null;
		try {
			rs = st.executeQuery(query);
			rs.next();
		} catch (SQLException sqle) {
			exceptionMessages(sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * @param query query to execute update (reusable method for INSERT, UPDATE &
	 *              DELETE queries)
	 * @return int 1 if succeed, 0 if fail
	 * 
	 *         this method has default access modifier, only classes within the same
	 *         package can access
	 */
	int executeUpdateRS(String query) {

		int rsi = 0;
		try {
			st.executeUpdate(query);
			rsi = 1;
		} catch (SQLException sqle) {
			exceptionMessages(sqle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rsi;
	}

	/**
	 * @param sqle exception messages to execute if error occurs(breaking down code)
	 * 
	 */
	protected static void exceptionMessages(SQLException sqle) {
		while (sqle != null) {
			System.out.println("State: " + sqle.getSQLState());
			System.out.println("Message: " + sqle.getMessage());
			System.out.println("Error: " + sqle.getErrorCode());
			sqle = sqle.getNextException();
		}
	}

	public boolean emailExists(String email) {
		return dbUser.emailExists(email);
	}

}
