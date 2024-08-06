package in.BankAppFXMaven.model;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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

public class DbUserSection {

	private static DatabaseDAO db;
	private static DbUserSection dbUserSection;

	private DbUserSection() {
	}

	public static DbUserSection getInstance() {
		if (dbUserSection == null) {
			dbUserSection = new DbUserSection();
			db = DatabaseDAO.getInstance();
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

		User newUser = null;

		try {
			if (!rs.wasNull()) {
				newUser = new User();
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
			System.out.println("User with this email does not exist.");
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

	public int checkLoginCredentials(String email, String password) {
		return db.checkLoginCredentials(email, password);
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
		if (i == 1) {
			return true;
		} else {
			return false;
		}
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
		        String name = nameTextField.getText().trim();
		        String surname = surnameTextField.getText().trim();
		        
		        // Capitalize the first letter of name and surname
		        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
		        
		        return new Pair<>(name, surname);
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

//					User user = new User(); //this 2 commented lines are for junit testing purpose only
//					loggedUser.setUser(user);

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

	public boolean createUser(String email, String password) {
		try {
			String userQuery = "INSERT INTO user (email) VALUES('" + email + "');";
			db.getConnection();
			db.executeUpdateRS(userQuery);

			// now get user_id since it was created using the email above
			User user = db.getUserByEmail(email);

			try {
				password = HashingUtility.hashPassword(password);

				String loginQuery = "INSERT INTO login (user_id, password_hash) VALUES (" + user.getId() + ", '"
						+ password + "');";
				db.executeUpdateRS(loginQuery);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// insert login.user_id & login.password_hash

			// set user_id in bank_account table (auto incremented) creating a bank_acc_id

			String bankAccountQuery = "INSERT INTO bank_account (user_id, bank_acc_number) VALUES (" + user.getId()
					+ ", " + AccountNumberGenerator.generateRandomNumber(DatabaseController.getInstance()) + ");";
			db.executeUpdateRS(bankAccountQuery);

			// get bank_acc_id from bank_account table (auto incremented) to save into
			// statement
			String getBankAccIdQuery = "SELECT * FROM bank_account WHERE user_id = " + user.getId() + ";";
			BankAccount ba = db.getBankAccountReusableMethod(getBankAccIdQuery);

			String statementQuery = "INSERT INTO statement (bank_acc_id) VALUES (" + ba.getBankAccID() + ");";
			db.executeUpdateRS(statementQuery);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}