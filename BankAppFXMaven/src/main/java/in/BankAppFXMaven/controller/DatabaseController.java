package in.BankAppFXMaven.controller;

import java.util.ArrayList;

import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.model.User;

public class DatabaseController {

	
	private static DatabaseController dbController = DatabaseController.getInstance();
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
}
