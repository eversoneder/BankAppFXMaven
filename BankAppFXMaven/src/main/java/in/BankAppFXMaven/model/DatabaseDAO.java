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

	public void showNameSurnameDialogAndSave() {

		// Create a boolean flag to track if the save button was clicked
		final boolean[] saveClicked = { false };

		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.initModality(Modality.APPLICATION_MODAL); // Set the dialog to be modal so user can't drag it and use the
															// other page without giving their name
		dialog.setTitle("Required");
		dialog.setHeaderText("Please enter your Name and Surname.");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

		// Create the name and surname labels and fields.
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField nameTextField = new TextField();
		nameTextField.setPromptText("Name");
		TextField surnameTextField = new TextField();
		surnameTextField.setPromptText("Surname");

		grid.add(new Label("Name:"), 0, 0);
		grid.add(nameTextField, 1, 0);
		grid.add(new Label("Surname:"), 0, 1);
		grid.add(surnameTextField, 1, 1);

		// Enable/Disable save button depending on whether a name and surname were
		// entered.
		Node saveButton = dialog.getDialogPane().lookupButton(loginButtonType);
		saveButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		// disable the save button if user haven't input anything or input only blank
		// space on name field or on surname field
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			saveButton.setDisable(newValue.trim().isEmpty() || surnameTextField.getText().trim().isEmpty());
		});
		surnameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			saveButton.setDisable(newValue.trim().isEmpty() || nameTextField.getText().trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the name field by default.
		Platform.runLater(() -> nameTextField.requestFocus());

		// Convert the result to a name-surname pair when the save button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				saveClicked[0] = true;
				return new Pair<>(nameTextField.getText(), surnameTextField.getText());
			}
			return null;
		});

		// Add an event filter to consume the close request when the 'X' button is
		// clicked
		dialog.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
			if (!saveClicked[0]) {
				event.consume();
			}
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(nameSurname -> {

			LoggedUser loggedUser = LoggedUser.getInstance();

//			User user = new User(); //this 2 commented lines are for junit testing purpose only
//			loggedUser.setUser(user);

			loggedUser.getUser().setName(nameSurname.getKey().trim());
			loggedUser.getUser().setSurname(nameSurname.getValue().trim());

			db.executeUpdateRS("UPDATE user SET name = '" + loggedUser.getUser().getName() + "', surname = '"
					+ loggedUser.getUser().getSurname() + "' WHERE user_id = " + loggedUser.getUser().getId() + ";");

			Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
			confirmationAlert.setTitle("Confirmation");
			confirmationAlert.setHeaderText(null);
			confirmationAlert.setContentText("Name and Surname have been set.\n\nWelcome to Econo Bank "
					+ nameSurname.getKey() + " " + nameSurname.getValue()
					+ "! Your new bank account is ready for use! \nTake advantage of the many Econo Bank features!");
			confirmationAlert.showAndWait();
		});
	}

	public User getUserByEmail(String email) {

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
			
			try {
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

			} catch (SQLException sqle) {
				db.exceptionMessages(sqle);
				sqle.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("User with this email does not exist.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	 * @param email    to get user Id to update user's password
	 * @param password new password to set
	 * @return 1 if new password was set, 0 if not set
	 */
	public int setNewPassword(String email, String password) {
		String queryGetId = "SELECT * FROM user WHERE email = '" + email + "';";
		int userId = getUserId(queryGetId);

		try {
			password = HashingUtility.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = "UPDATE login SET password_hash = '" + password + "' WHERE user_id = " + userId + ";";
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
	public BankAccount getUserBankAccByUserID(int userID) {
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

		try {
			rs = st.executeQuery("SELECT * FROM transaction WHERE bank_acc_id = " + bankId + ";");
			rs.next();

			if (!rs.wasNull()) {

				Transaction transaction = new Transaction();

				transaction.setTransactionID(rs.getInt("transaction_id"));
				transaction.setBankAccID(rs.getInt("bank_acc_id"));
				transaction.setTransactionType(rs.getString("transaction_type"));
				transaction.setTransactionAmount(rs.getDouble("transaction_amount"));
				transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

				statementList.add(transaction);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return statementList;
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

		// user entity insert: email
		// login entity insert: user.id, login.password_hash
		// bank_account entity insert: user.id, bank_acc_number
		// statement entity insert: bank_acc_id

		try {
			String userQuery = "INSERT INTO user (email) VALUES('" + email + "');";
			this.getConnection();
			executeUpdateRS(userQuery);

			// now get user_id since it was created using the email above
			User user = getUserByEmail(email);

			try {
				password = HashingUtility.hashPassword(password);

				String loginQuery = "INSERT INTO login (user_id, password_hash) VALUES (" + user.getId() + ", '"
						+ password + "');";
				executeUpdateRS(loginQuery);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// insert login.user_id & login.password_hash

			// set user_id in bank_account table (auto incremented) creating a bank_acc_id

			String bankAccountQuery = "INSERT INTO bank_account (user_id, bank_acc_number) VALUES (" + user.getId()
					+ ", " + AccountNumberGenerator.generateRandomNumber(dbController) + ");";
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
