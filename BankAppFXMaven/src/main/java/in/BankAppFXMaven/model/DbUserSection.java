package in.BankAppFXMaven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUserSection {

	private DatabaseDAO db;
	private static DbUserSection dbUserSection;

	private DbUserSection() {
	}

	public static DbUserSection getInstance() {
		if (dbUserSection == null) {
			dbUserSection = new DbUserSection();
		}
		return dbUserSection;
	}

	/**
	 * @param rs executed ResultSet
	 * @return ArrayList of User
	 */
	public ArrayList<User> getUsers(ResultSet rs) {

		ArrayList<User> userList = new ArrayList<User>();

		try {
			do {
				if (!rs.wasNull()) {
					User newUser = new User();
					newUser.setId(rs.getInt("user_id"));
					newUser.setName(rs.getString("name"));
					newUser.setSurname(rs.getString("surname"));
					newUser.setEmail(rs.getString("email"));
					userList.add(newUser);

					System.out.println("User ID: " + newUser.getId());
					System.out.println("Name: " + newUser.getName());
					System.out.println("Surname: " + newUser.getSurname());
					System.out.println("Email: " + newUser.getEmail());
					System.out.println(); // Adds an empty line for better readability
				}
			} while (rs.next());

		} catch (SQLException sqle) {
			db.exceptionMessages(sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return userList;
	}

	/**
	 * @param rs executed ResultSet
	 * @return User
	 */
	public User getUser(ResultSet rs) {

		User newUser = new User();

		try {
			if (!rs.wasNull()) {
				newUser.setId(rs.getInt("user_id"));
				newUser.setName(rs.getString("name"));
				newUser.setSurname(rs.getString("surname"));
				newUser.setEmail(rs.getString("email"));

				System.out.println("User ID: " + newUser.getId());
				System.out.println("Name: " + newUser.getName());
				System.out.println("Surname: " + newUser.getSurname());
				System.out.println("Email: " + newUser.getEmail());
				System.out.println(); // Adds an empty line for better readability
			}

		} catch (SQLException sqle) {
			db.exceptionMessages(sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return newUser;
	}

	public User getUserInfo(String userID) {
		String query = "SELECT " + "    user.email," + "    user.name," + "    user.surname,"
				+ "    bank_account.account_number," + "    login.password" + "FROM " + "    user" + "JOIN "
				+ "login ON user.user_id = login.user_id" + "JOIN "
				+ "bank_Aaccount ON user.user_id = bank_account.user_id" + "WHERE " + "user.user_id = " + userID + ";";
		User user = new User();
		return user;
	}

	public boolean checkLoginCredentials(String email, String password) {

		String query = "SELECT * FROM user JOIN login ON user.password_hash = login.password_hash "
				+ "WHERE user.email = '" + email + "' AND login.password_hash = '" + password + "';";

		return false;
	}

	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM bankappfx.user WHERE user.email = '" + email + "';";

		ResultSet rs = db.executeQueryRS(query);
		int i = 0;
		
		try {
			// if there are records in the database(email exists)
			if (!rs.wasNull()) {
				i = 1;
			}
		} catch (SQLException sqle) {
			db.exceptionMessages(sqle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// if there are NO records in the database(email does NOT exists)
		if(i == 1) {
			return true;
		} else {
			return false;
		}
	}
}