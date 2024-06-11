package in.BankAppFXMaven.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import in.BankAppFXMaven.model.DbUserSection;

public class DatabaseController {

	
	private static DatabaseController db = null;
	private static DbUserSection dbUserSection = null;
	
	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;

	public static DatabaseController getInstance() {
		if (db == null) {
			db = new DatabaseController();
		}
		return db;
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
	
	
}
