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
	
	public static boolean getAccValidity(String email, String accNum) {
		String emailTest = "test";
		String accNumTest = "test";

		if (email.equalsIgnoreCase(emailTest) && accNum.equalsIgnoreCase(accNumTest)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<User> getUsers(){
		return db.getUsers();
	}
	
	public User getUser(String email){
		User user = db.getUser(email);
		return user;
	}
	
	/**
	 * @param email to check in database
	 * @param password to check in database
	 * @return true if email & password matches
	 */
	public boolean checkLoginCredentials(String email, String password) {
		return db.checkLoginCredentials(email, password);
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
	
	public void setLastLogin(Date date, User user) {
		db.setLastLogin(date, user);
	}
	
	public boolean emailExists(String email) {
		return db.emailExists(email);
	}
}
