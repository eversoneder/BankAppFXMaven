package in.BankAppFXMaven.controller;

import java.sql.Date;
import java.util.ArrayList;

import in.BankAppFXMaven.model.BankAccount;
import in.BankAppFXMaven.model.DatabaseDAO;
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
	
	public static ArrayList<User> getUsers(){
		return db.getUsers();
	}
	
	public User getUserByEmail(String email){
		User user = db.getUserByEmail(email);
		return user;
	}
	
	/**
	 * @param email to check 
	 * @param password to check 
	 * @return true if account exists
	 */
	public static int checkLoginCredentials(String email, String password) {
		return db.checkLoginCredentials(email, password);
	}
	
	/**
	 * @param email to check 
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
	
	public boolean accNumberExists(int randomNumber) {
		// TODO Auto-generated method stub
		return db.accNumberExists(randomNumber);
	}
	
	public void createUser(String email, String password) {
		db.createUser(email, password);
	}
	
	public void setLastLogin(int userId) {
		db.setLastLoginNow(userId);
	}
	
	public java.sql.Timestamp getLastLogin(int userId) {
		return db.getLastLogin(userId);
	}
	
	public boolean emailExists(String email) {
		return db.emailExists(email);
	}
}
