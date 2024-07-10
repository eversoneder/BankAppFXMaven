package in.BankAppFXMaven.controller;

import java.sql.Date;
import java.util.ArrayList;

import in.BankAppFXMaven.model.BankAccount;
import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.model.Login;
import in.BankAppFXMaven.model.Statement;
import in.BankAppFXMaven.model.Transaction;
import in.BankAppFXMaven.model.User;
import in.BankAppFXMaven.utility.AccountNumberGenerator;

public class DatabaseController {

	private static DatabaseController dbController;
	private static DatabaseDAO db = DatabaseDAO.getInstance();

	public static DatabaseController getInstance() {
		if (dbController == null) {
			dbController = new DatabaseController();
		}
		return dbController;
	}

	private DatabaseController() {
	}

//	public static boolean getAccValidity(String email, String accNum) {
//		String emailTest = "test";
//		String accNumTest = "test";
//
//		if (email.equalsIgnoreCase(emailTest) && accNum.equalsIgnoreCase(accNumTest)) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public static ArrayList<User> getUsers() {
		return db.getUsers();
	}

	public User getUserByEmail(String email) {
		User user = db.getUserByEmail(email);
		return user;
	}

	public User getUserById(int userId) {
		User user = db.getUserById(userId);
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
		Login login = db.getLoginByUserId(userId);
		return login;
	}

	/**
	 * @param email    to check
	 * @param password to check
	 * @return true if account exists
	 */
	public static int checkLoginCredentials(String email, String password) {
		return db.checkLoginCredentials(email, password);
	}

	/**
	 * @param email   to check
	 * @param bankAcc bank account to check
	 * @return true if both match user_id's
	 */
	public static boolean checkEmailAndBankAcc(String email, int bankAcc) {
		return db.checkEmailAndBankAcc(email, bankAcc);
	}

	public static int setNewPassword(String email, String password) {
		return db.setNewPassword(email, password);
	}

	/**
	 * @param user to get the bank acc from db
	 * @return bank account of the parameter user
	 */
	public BankAccount getUserBankAcc(int userID) {
		return db.getUserBankAccByUserID(userID);
	}

	/**
	 * @param bankAccId to get the statement from
	 * @return Statement
	 */
	public Statement getStatement(int bankAccId) {
		return db.getStatement(bankAccId);
	}
	
	/**
	 * @param statement to get all transaction list from statement.getBankAccId()
	 * @return Transaction ArrayList
	 */
	public ArrayList<Transaction> getStatementTransactionList(Statement statement) {
		return db.getStatementTransactionList(statement);
	}

	public boolean accNumberExists(int randomNumber) {
		// TODO Auto-generated method stub
		return db.accNumberExists(randomNumber);
	}

	public void createUser(String email, String password) {
		db.createUser(email, password);
	}

	public java.sql.Timestamp setLastLoginNow(int userId) {
		return db.setLastLoginNow(userId);
	}

	public java.sql.Timestamp getLastLogin(int userId) {
		return db.getLastLogin(userId);
	}

	public boolean emailExists(String email) {
		return db.emailExists(email);
	}
}
