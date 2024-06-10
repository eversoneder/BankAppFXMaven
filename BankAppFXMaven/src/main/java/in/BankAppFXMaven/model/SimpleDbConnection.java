package in.BankAppFXMaven.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SimpleDbConnection {
	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;

	private String url;
	private String user;
	private String password;

	private static SimpleDbConnection db = null;
	
	public static SimpleDbConnection getInstance() {
		if(db == null) {
			db = new SimpleDbConnection();
		}
		return db;
	}
	private SimpleDbConnection () {
		connect();
	}
	
	private void connect() {
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = getClass().getClassLoader().getResourceAsStream("in/BankAppFXMaven/config.properties");
			prop.load(input);
			
			url = prop.getProperty("db.url");
			user = prop.getProperty("db.user");
			password = prop.getProperty("db.password");
			System.out.println("URL: " + url + ". User: " + user + ". Password: " + password +".");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Database Connected.");
			}

			st = connection.createStatement();
			getUser();

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getUser() {
		
		try {
			rs = st.executeQuery("SELECT * FROM user");
			rs.next();

			try {
				do {
					if (!rs.wasNull()) {
						User newUser = new User();
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
				} while (rs.next());
			} catch (SQLException sqle) {
				exceptionMessages(sqle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (SQLException sqle) {
			exceptionMessages(sqle);
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param sqle exception messages to execute if error occurs(breaking down code)
	 */
	private void exceptionMessages(SQLException sqle) {
		while (sqle != null) {
			System.out.println("State: " + sqle.getSQLState());
			System.out.println("Message: " + sqle.getMessage());
			System.out.println("Error: " + sqle.getErrorCode());
			sqle = sqle.getNextException();
		}
	}

}
