package in.BankAppFXMaven.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
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
	 * @param newUser all info needed to create a user in db
	 * @return true if created
	 */
	public boolean createUser(NewUser newUser) {
		//create user + login + bank account + statement on the database
		
		//user saves only email from sign up page, when first logs in, app asks name & surname
		//login saves user_id & password_hash
		//bank_account saves only user_id
		//statement saves only bank_account_id
		
		
		//on the signUp page, utilize the hashingUtility to save up the password in hashed form before calling createUser.
		
		//insert user.email, login.user_id & login.password_hash
		String userQuery = "INSERT INTO user (email) VALUES('" + newUser.getUser().getEmail() + "');";
		executeUpdateRS(userQuery);
		
		//now get user_id since it was created using the email above
		User user = getUser(newUser.getUser().getEmail());
		
		//insert login.user_id & login.password_hash
		String loginQuery = "INSERT INTO login (user_id, password_hash) VALUES (" + user.getId() + ", " + newUser.getLogin().getPasswordHash() + ");";
		executeUpdateRS(loginQuery);
		
		//set user_id in bank_account table (auto incremented) creating a bank_acc_id
		
		//IMPORTANTE, ALTERAR A QUERY E COLOCAR +1 INSERT (accountNumberGenerator) pra bank_account_number 
		String bankAccountQuery = "INSERT INTO bank_account (user_id) VALUES (" + user.getId() + ");";
		executeUpdateRS(bankAccountQuery);
		
		//get bank_acc_id from bank_account table (auto incremented) to save into statement
		String getBankAccIdQuery = "SELECT * FROM bank_account WHERE user_id = " + user.getId() + ";";
		BankAccount ba = getBankAccountReusableMethod(getBankAccIdQuery);		
		
		
		//get bank_acc_id
		
		
		//insert bank_acc_id on statement
		String statementQuery = "INSERT INTO statement (bank_acc_id) VALUES(" + newUser.getBankAccount().getBankAccID() + ");";
		
		
		
		return false;
	}
	
	public void setLastLogin(Date date, User user) {
		String query = "UPDATE login SET last_login = '" + date + "' WHERE user_id = " + user.getId() + ";";
		executeUpdateRS(query);
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
