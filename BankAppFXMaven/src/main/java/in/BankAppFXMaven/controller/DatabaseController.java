package in.BankAppFXMaven.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import in.BankAppFXMaven.model.BankAccount;
import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.model.Login;
import in.BankAppFXMaven.model.Statement;
import in.BankAppFXMaven.model.Transaction;
import in.BankAppFXMaven.model.Transfer;
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
		return db.getUserByEmail(email);
	}

	public User getUserById(int userId) {
		return db.getUserById(userId);
	}

	public void showNameSurnameDialogAndSave() {
		db.showNameSurnameDialogAndSave();
	}

	/**
	 * This method retrieves login information. If last_login is null, it sets the
	 * current time; otherwise, it updates last_login but only after the next login.
	 * 
	 * @param userId to get login
	 * @return login
	 */
	public Login getLoginByUserId(int userId) {
		return db.getLoginByUserId(userId);
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

	/**
	 * @param email    to get user Id to update user's password
	 * @param newPassword new password to set
	 * @return 1 if new password was set, 0 if not set
	 */
	public int setNewPassword(String email, String newPassword) {
		return db.setNewPassword(email, newPassword);
	}
	
	/**
	 * @param userId of user who will get new name
	 * @param userNewName to replace old name
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int setNewName(int userId, String userNewName) {
		return db.setNewName(userId, userNewName);
	}
	
	/**
	 * @param userId      of user who will get new surname
	 * @param userNewSurname to replace old surname
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int setNewSurname(int userId, String userNewSurname) {
		return db.setNewSurname(userId, userNewSurname);
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

	/**
	 * @param userId to set TimeStamp to the brand new account
	 * @return the TimeStamp that was set in the db
	 */
	public java.sql.Timestamp insertNewLastLogin(int userId) {
		return db.insertNewLastLogin(userId);
	}

	/**
	 * @param userId of user to set last_login
	 * @return TimeStamp updated
	 */
	public java.sql.Timestamp updateLastLoginNow(int userId) {
		return db.updateLastLoginNow(userId);
	}

	/**
	 * @param userId to get "last_login" from db
	 * @return TimeStamp of "last_login"
	 */
	public java.sql.Timestamp getLastLogin(int userId) {
		return db.getLastLogin(userId);
	}

	public boolean emailExists(String email) {
		return db.emailExists(email);
	}

	/**
	 * You SENT cash and want to get history of who received at this specific
	 * date.(get to_bank_acc_id)
	 * 
	 * @param userId        of receiving bank holder
	 * @param timeStampDate to get specific transfer 'to_bank_acc_id'
	 * @return specific dated transfer from userId
	 */
	public Transfer getSpecificReceiverTransfer(int userId, Timestamp timeStampDate) {
		return db.getSpecificReceiverTransfer(userId, timeStampDate);
	}

	/**
	 * You RECEIVED cash and want to know who sent you at this specific date.(get
	 * from_bank_acc_id)
	 * 
	 * @param userId        of sent bank holder
	 * @param timeStampDate to get specific transfer 'to_bank_acc_id'
	 * @return specific dated transfer from userId
	 */
	public Transfer getSpecificSenderTransfer(int userId, Timestamp timeStampDate) {
		return db.getSpecificSenderTransfer(userId, timeStampDate);
	}

	/**
	 * @param userId from bank account who sent
	 * @return ArrayList of transfers from UserId
	 */
	public ArrayList<Transfer> getTransfersSent(int userId) {
		return db.getTransfersSent(userId);
	}

	/**
	 * @param userId from bank account who received
	 * @return ArrayList of transfers from UserId
	 */
	public ArrayList<Transfer> getTransfersReceived(int userId) {
		return db.getTransfersSent(userId);
	}

	/**
	 * @param amount to withdraw
	 * @return 1 if successful, 0 if unsuccessful
	 */
	public int updateAccountBalance(double amount) {
		return db.updateAccountBalance(amount);
	}
	
	public int addNewTransaction(Transaction transaction) {
		return db.addNewTransaction(transaction);
	}
	
	
}
