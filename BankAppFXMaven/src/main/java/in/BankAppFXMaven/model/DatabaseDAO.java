package in.BankAppFXMaven.model;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.utility.AccountNumberGenerator;
import in.BankAppFXMaven.utility.HashingUtility;

public class DatabaseDAO {
	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;

	private String url;
	private String user;
	private String password;

	private static DatabaseDAO db;
	private DbUserSection dbUser = DbUserSection.getInstance();
	private DatabaseController dbController = DatabaseController.getInstance();

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
			input = getClass().getClassLoader().getResourceAsStream("in/BankAppFXMaven/model/config.properties");
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

	public User getUser(String email) {

		User user = new User();
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE email = '" + email + "';");
			rs.next();

			user = dbUser.getUser(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	public boolean checkLoginCredentials(String email, String password) {
		String query = "SELECT * FROM user JOIN login ON user.user_id = login.user_id " + "WHERE user.email = '" + email
				+ "' " + "AND login.password_hash = '" + password + "';";

		try {
			rs = st.executeQuery(query);
			rs.next();

			// if query had 1 result, it means the email + password exists
			if (!rs.wasNull()) {
				System.out.println("User: " + user.getClass().getName() + " exists.");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// if no result, it means no email + password exists
		return false;
	}

	/**
	 * @param user to get the bank account from
	 * @return bank account from user
	 */
	public BankAccount getUserBankAccByUserID(int userID) {
		String query = "SELECT * FROM bankappfx.bank_account WHERE user_id = " + userID + ";";
		return getBankAccountReusableMethod(query);
	}

	/**
	 * @param query to get the bank account
	 * @return the bank account
	 */
	public BankAccount getBankAccountReusableMethod(String query) {

		BankAccount ba = null;

		try {
			rs = st.executeQuery(query);
			rs.next();

			// number already exist (was not null, it was true that accNumberExists)
			if (!rs.wasNull()) {

				ba = new BankAccount();
				ba.setUserID(rs.getInt("user_id"));
				ba.setBankAccNum(rs.getInt("bank_acc_number"));
				ba.setBankAccBalance(rs.getDouble("bank_acc_balance"));
				ba.setBankAccID(rs.getInt("bank_acc_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return ba;
	}

	/**
	 * Creates the db entry for user, login, bank_account and statement on mySql db
	 * 
	 * @param email    user's email
	 * @param password user's password
	 * @return true if created
	 */
	public boolean createUser(String email, String password) {
		// gets email & password from sign up page
		// login saves user_id & password_hash
		// bank_account saves user_id and bank_account from AccountNumberGenerator class
		// statement saves only bank_account_id

		//user entity insert: email
		//login entity insert: user.id, login.password_hash
		//bank_account entity insert: user.id, bank_acc_number
		//statement entity insert: bank_acc_id
		
		try {
			String userQuery = "INSERT INTO user (email) VALUES('" + email + "');";
			this.getConnection();
			executeUpdateRS(userQuery);

			// now get user_id since it was created using the email above
			User user = getUser(email);

			try {
				password = HashingUtility.hashPassword(password);

				String loginQuery = "INSERT INTO login (user_id, password_hash) VALUES (" + user.getId() + ", '" + password
						+ "');";
				executeUpdateRS(loginQuery);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// insert login.user_id & login.password_hash

			// set user_id in bank_account table (auto incremented) creating a bank_acc_id

			String bankAccountQuery = "INSERT INTO bank_account (user_id, bank_acc_number) VALUES (" + user.getId() + ", "
					+ AccountNumberGenerator.generateRandomNumber(dbController) + ");";
			executeUpdateRS(bankAccountQuery);

			// get bank_acc_id from bank_account table (auto incremented) to save into
			// statement
			String getBankAccIdQuery = "SELECT * FROM bank_account WHERE user_id = " + user.getId() + ";";
			BankAccount ba = getBankAccountReusableMethod(getBankAccIdQuery);

			String statementQuery = "INSERT INTO statement (bank_acc_id) VALUES (" + ba.getBankAccID() + ");";
			executeUpdateRS(statementQuery);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void setLastLogin(Date date, User user) {
		String query = "UPDATE login SET last_login = '" + date + "' WHERE user_id = " + user.getId() + ";";
		executeUpdateRS(query);
	}

	public boolean accNumberExists(int randomNumber) {

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
		System.out.println("Bank account number: "+ randomNumber + " does not exist.");
		// number do not exist (was null, 0 records in database, it was false that
		// accNumberExists)
		return false;
	}

	/**
	 * @param email email to check if exists in database
	 * @return true if exists, false if email doesn't exist in databse
	 */
	public boolean emailExists(String email) {
		return dbUser.emailExists(email);
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
	protected void exceptionMessages(SQLException sqle) {
		while (sqle != null) {
			System.out.println("State: " + sqle.getSQLState());
			System.out.println("Message: " + sqle.getMessage());
			System.out.println("Error: " + sqle.getErrorCode());
			sqle = sqle.getNextException();
		}
	}
}
