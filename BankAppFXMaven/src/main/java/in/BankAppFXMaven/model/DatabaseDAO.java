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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.utility.AccountNumberGenerator;
import in.BankAppFXMaven.utility.HashingUtility;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

public class DatabaseDAO {
	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;

	private static String url;
	private static String user;
	private static String password;

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
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream("in/BankAppFXMaven/model/config.properties");
			prop.load(input);

			url = prop.getProperty("db.url");
			user = prop.getProperty("db.user");
			password = prop.getProperty("db.password");

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			st = connection.createStatement();

			if (connection != null) {
				System.out.println("Database Connected.");
			}
			System.out.println("URL: " + url + ". User: " + user + ". Password: " + password + ".");

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return database connection
	 */
	protected Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * Creates the db entry for user, login, bank_account and statement on mySql db
	 * 
	 * @param email    user's email
	 * @param password user's password
	 * @return true if created
	 */
	public boolean createUser(String email, String password) {
		return dbUser.createUser(email, password);
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

	/**
	 * @param email to get user
	 * @return user
	 */
	public User getUserByEmail(String email) {

		User user = null;
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE email = '" + email + "';");
			rs.next();

			user = dbUser.getUser(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	/**
	 * @param userId to get user
	 * @return user
	 */
	public User getUserById(int userId) {

		User user = null;
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE user_id = " + userId + ";");
			rs.next();

			user = dbUser.getUser(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	/**
	 * Shows a dialog to user to enter name and surname to save to database
	 */
	public void showNameSurnameDialogAndSave() {
		dbUser.showNameSurnameDialogAndSave();
	}
	
	/**
	 * This method retrieves login information. If last_login is null, it sets the
	 * current time; otherwise, it updates last_login but only after the next login.
	 * 
	 * @param userId to get login
	 * @return login
	 */
	public Login getLoginByUserId(int userId) {

		Login login = new Login();

		try {
			rs = st.executeQuery("SELECT * FROM login WHERE user_id = " + userId + ";");
			rs.next();

			if (!rs.wasNull()) {
				login.setLoginId(rs.getInt("login_id"));
				login.setUserId(rs.getInt("user_id"));
				login.setPasswordHash(rs.getString("password_hash"));
				login.setLastLogin(rs.getTimestamp("last_login"));

				System.out.println("login_id: " + login.getLoginId());
				System.out.println("user_id: " + login.getUserId());
				System.out.println("password_hash: " + login.getPasswordHash());
				System.out.println("last_login: " + login.getLastLogin());
				System.out.println();
			}

		} catch (SQLException e) {
			db.exceptionMessages(e);
			System.out.println("User with this email does not exist.");
		}
		return login;
	}

	/**
	 * @param userId to set TimeStamp to the brand new account
	 * @return the TimeStamp that was set in the db
	 */
	public java.sql.Timestamp insertNewLastLogin(int userId) {

		java.sql.Timestamp newTimeStamp = new java.sql.Timestamp(System.currentTimeMillis());
		executeUpdateRS("UPDATE login SET last_login = '" + newTimeStamp + "' WHERE user_id = " + userId + ";");

		return newTimeStamp;
	}

	/**
	 * @param userId to get "last_login" from db
	 * @return TimeStamp of "last_login"
	 */
	public java.sql.Timestamp getLastLogin(int userId) {
		String query = "SELECT * FROM login WHERE user_id = " + userId + ";";

		Object lastLoginTimeStamp = selectAndGetColumnValue(query, "timestamp", "last_login");
		java.sql.Timestamp timeStamp = (Timestamp) lastLoginTimeStamp;

		return timeStamp;
	}

	/**
	 * @param userId of user to set last_login
	 * @return TimeStamp updated
	 */
	public java.sql.Timestamp updateLastLoginNow(int userId) {
		Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis());
		String query = "UPDATE login SET last_login = '" + timeStamp + "' WHERE user_id = " + userId + ";";
		executeUpdateRS(query);
		return timeStamp;
	}

	/**
	 * @param email    to check
	 * @param password to check
	 * @return user's Id if account exists, 0 if doesn't exist
	 */
	public int checkLoginCredentials(String email, String password) {

		try {
			password = HashingUtility.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int userId = 0;

		String query = "SELECT * FROM user JOIN login ON user.user_id = login.user_id WHERE user.email = '" + email
				+ "' " + "AND login.password_hash = '" + password + "';";

		try {
			rs = st.executeQuery(query);
			rs.next();

			// if query had 1 result, it means the email + password exists
			if (!rs.wasNull()) {
				System.out.println("User exists.");

				// then get user's Id to get all data to load up to sign in
				userId = rs.getInt("user_id");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userId;
		// if no result, it means no email + password exists
	}

	/**
	 * @param email   to check
	 * @param bankAcc bank account to check
	 * @return true if both match user_id's
	 */
	public boolean checkEmailAndBankAcc(String email, int bankAcc) {

		String query1 = "SELECT * FROM user WHERE email = '" + email + "';";

		Object result1 = selectAndGetColumnValue(query1, "int", "user_id");
		int intEmailResult = (int) result1;

		String query2 = "SELECT * FROM bank_account WHERE bank_acc_number = " + bankAcc + ";";
		Object result2 = selectAndGetColumnValue(query2, "int", "user_id");
		int intBankAccResult = (int) result2;

		// if user.user_id is the same as bank_account.user_id, return true
		if (intEmailResult == intBankAccResult) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param email       to get user Id to update user's password
	 * @param newPassword new password to set
	 * @return 1 if new password was set, 0 if not set
	 */
	public int setNewPassword(String email, String newPassword) {

		String queryGetId = "SELECT * FROM user WHERE email = '" + email + "';";

		int userId = getUserId(queryGetId);

		if (userId == 0) {
			System.out.println("Failed to get user's id. Password not edited.");
		} else {
			try {
				newPassword = HashingUtility.hashPassword(newPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String query = "UPDATE login SET password_hash = '" + newPassword + "' WHERE user_id = " + userId + ";";
			return executeUpdateRS(query);
		}
		return 0;
	}

	/**
	 * @param userId      of user who will get new name
	 * @param userNewName to replace old name
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int setNewName(int userId, String userNewName) {

		String query = "UPDATE user SET name = '" + userNewName + "' WHERE user_id = " + userId + ";";
		return executeUpdateRS(query);
	}

	/**
	 * @param userId         of user who will get new surname
	 * @param userNewSurname to replace old surname
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int setNewSurname(int userId, String userNewSurname) {

		String query = "UPDATE user SET surname = '" + userNewSurname + "' WHERE user_id = " + userId + ";";
		return executeUpdateRS(query);
	}

	public int getUserId(String query) {
		int userId = 0;
		try {
			rs = st.executeQuery(query);
			rs.next();

			if (!rs.wasNull()) {
				userId = rs.getInt("user_id");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userId;
	}

	/**
	 * @param user to get the bank account from
	 * @return bank account from user
	 */
	public BankAccount getBankAccByUserID(int userID) {
		String query = "SELECT * FROM bankappfx.bank_account WHERE user_id = " + userID + ";";
		return getBankAccountReusableMethod(query);
	}

	/**
	 * @param bankAccId to get the statement from
	 * @return Statement
	 */
	public in.BankAppFXMaven.model.Statement getStatement(int bankAccId) {

		in.BankAppFXMaven.model.Statement statement = new in.BankAppFXMaven.model.Statement();
		try {
			rs = st.executeQuery("SELECT * FROM statement WHERE bank_acc_id = " + bankAccId + ";");
			rs.next();

			if (!rs.wasNull()) {
				statement.setStatementID(rs.getInt("statement_id"));
				statement.setBankAccID(rs.getInt("bank_acc_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return statement;
	}

	/**
	 * @param statement to get all transaction list from statement.getBankAccId()
	 * @return Transaction ArrayList
	 */
	public ArrayList<Transaction> getStatementTransactionList(in.BankAppFXMaven.model.Statement statement) {

		int bankId = statement.getBankAccID();

		ArrayList<Transaction> statementList = new ArrayList<Transaction>();

		rs = executeQueryRS("SELECT * FROM transaction WHERE bank_acc_id = " + bankId + ";");

		try {
			do {
				if (!rs.wasNull()) {

					Transaction transaction = new Transaction();
					transaction.setTransactionID(rs.getInt("transaction_id"));
					transaction.setBankAccID(rs.getInt("bank_acc_id"));
					transaction.setTransactionType(rs.getString("transaction_type"));
					transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
					transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

					statementList.add(transaction);
				}
			} while (rs.next());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return statementList;
	}
	
	/**
	 * get specific transaction through it's transaction id
	 * 
	 * @param transactionId of transaction
	 * @return specific transaction
	 */
	public Transaction getTransaction(int transactionId) {

		String query = "SELECT * FROM transaction WHERE transaction_id = " + transactionId + ";";
		ResultSet rs = executeQueryRS(query);

		Transaction transaction = null;
		try {
			if (!rs.wasNull()) {
				transaction = new Transaction();
				transaction.setTransactionID(rs.getInt("transaction_id"));
				transaction.setBankAccID(rs.getInt("bank_acc_id"));
				transaction.setTransactionType(rs.getString("transaction_type"));
				transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
				transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return transaction;
	}

	/**
	 * @param fromBankAccId to get transaction from
	 * @param timeStampDate to get specific transaction
	 * @return specific transaction
	 */
	public Transaction getTransaction(int fromBankAccId, Timestamp timeStampDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(timeStampDate);

		Transaction transaction = null;
		String query = "SELECT * FROM transaction WHERE bank_acc_id = " + fromBankAccId + " AND transaction_date = '"
				+ formattedDate + "';";

		rs = executeQueryRS(query);

		try {
			if (!rs.wasNull()) {
				transaction = new Transaction();
				transaction.setTransactionID(rs.getInt("transaction_id"));
				transaction.setBankAccID(rs.getInt("bank_acc_id"));
				transaction.setTransactionType(rs.getString("transaction_type"));
				transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
				transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return transaction;
	}
	
	/**
	 * @param transaction to be added
	 * @return 1 if successful, 0 if not successful
	 */
	public int addNewTransaction(Transaction transaction) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(transaction.getTransactionDate());

		int updateResponse = db.executeUpdateRS(
				"INSERT INTO transaction (bank_acc_id, transaction_type, transaction_amount, transaction_date) VALUES ("
						+ transaction.getBankAccID() + ", '" + transaction.getTransactionType() + "', "
						+ transaction.getTransactionAmount() + ", '" + formattedDate + "');");

		if (updateResponse == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * get specific transaction through it's transaction id
	 * 
	 * @param transferId of transaction
	 * @return specific transaction
	 */
	public Transfer getTransferByTransactionId(int transactionId) {

		String query = "SELECT * FROM transfer WHERE transaction_id = " + transactionId + ";";
		ResultSet rs = executeQueryRS(query);

		Transfer transfer = null;
		try {
			if (!rs.wasNull()) {
				transfer = new Transfer();
				transfer.setTransferID(rs.getInt("transfer_id"));
				transfer.setTransactionID(rs.getInt("transaction_id"));
				transfer.setFromBankAcc(rs.getInt("from_bank_acc_id"));
				transfer.setToBankAcc(rs.getInt("to_bank_acc_id"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return transfer;
	}

	/**
	 * @param newTransfer to insert to database
	 * @return 1 if successful, 0 if not successful
	 */
	public int addNewTransfer(Transfer newTransfer) {

		String query = "INSERT INTO transfer (transaction_id, from_bank_acc_id, to_bank_acc_id) VALUES ("
				+ newTransfer.getTransactionID() + ", " + newTransfer.getFromBankAcc() + ", "
				+ newTransfer.getToBankAcc() + ");";

		int response = executeUpdateRS(query);

		return response;
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
				ba.setBankAccID(rs.getInt("bank_acc_id"));
				ba.setUserID(rs.getInt("user_id"));
				ba.setBankAccNum(rs.getInt("bank_acc_number"));
				ba.setBankAccBalance(rs.getDouble("bank_acc_balance"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return ba;
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
		System.out.println("Bank account number: " + randomNumber + " does not exist.");
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
	 * @param query      to execute
	 * @param rsGetType  enter "string" for rs.getString, "int" for rs.getInt,
	 *                   "double" for rs.getDouble or "timestamp" for
	 *                   rs.getTimestamp
	 * @param columnName column name of database table from query
	 * @throws SQLException
	 */
	public Object selectAndGetColumnValue(String query, String rsGetType, String columnName) {

		ResultSet rs = executeQueryRS(query);

		switch (rsGetType) {
		case "string":
			try {
				return rs.getString(columnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "int":
			try {
				return rs.getInt(columnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "double":
			try {
				return rs.getDouble(columnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "timestamp":
			try {
				return rs.getTimestamp(columnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			throw new IllegalArgumentException("Invalid resultSetType: " + rsGetType);
		}
	}

	/**
	 * Reusable method to get transfer table depending on ResultSet/Query
	 * 
	 * @param rs ready executed query on ResultSet
	 * @return ArrayList of Transfer
	 */
	public ArrayList<Transfer> reusableTransferDAO(ResultSet rs) {

		ArrayList<Transfer> transfers = new ArrayList<Transfer>();
		try {
			if (!rs.wasNull()) {

				Transfer transfer = new Transfer();
				transfer.setTransferID(rs.getInt("transfer_id"));
				transfer.setTransactionID(rs.getInt("transaction_id"));
				transfer.setFromBankAcc(rs.getInt("from_bank_acc_id"));
				transfer.setToBankAcc(rs.getInt("to_bank_acc_id"));
				transfers.add(transfer);
			}

		} catch (SQLException sqle) {
			db.exceptionMessages(sqle);
			sqle.printStackTrace();
			System.out.println("Transfer couldn't be retrieved from db.");
		}
		return transfers;
	}

	/**
	 * @param amount to withdraw
	 * @param user   to get it's balance updated
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int updateAccountBalance(double amount, User user) {

		BankAccount userBankAcc = getBankAccByUserID(user.getId());
		double userBalance = userBankAcc.getBankAccBalance();

		double newBalance = userBalance + amount;

		int updateResponse = db.executeUpdateRS(
				"UPDATE bank_account SET bank_acc_balance = " + newBalance + " WHERE user_id = " + user.getId() + ";");

		if (updateResponse == 1) {
			return 1;
		} else {
			return 0;
		}
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
