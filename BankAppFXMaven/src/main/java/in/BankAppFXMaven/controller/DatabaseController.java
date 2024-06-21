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
	
	public User getUser(int ID){
		User user = db.getUser(ID);
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
	public BankAccount getUserBankAcc(User user) {
		return db.getUserBankAcc(user);
	}
	
	public boolean accNumberExists(String randomNumber) {
		// TODO Auto-generated method stub
		return db.accNumberExists(randomNumber);
	}
	
	public void createUser(String email, String password) {
		
		//user email (upload & get user_id) 
		
		
		
		//get unique random 8 digit number to upload along with user_id into bankappfx.bank_account table
		String randomNum = AccountNumberGenerator.generateRandomNumber(this);
		//
		
		//login password_hash
	}
	
	public void setLastLogin(Date date, User user) {
		db.setLastLogin(date, user);
	}
	
	public boolean emailExists(String email) {
		return db.emailExists(email);
	}
}
