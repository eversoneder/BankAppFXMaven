package in.BankAppFXMaven.view;

import java.util.Optional;
import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.Statement;
import in.BankAppFXMaven.utility.EmailValidator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SignInScene extends Application {

	private Stage primaryStage;

	private static SignInScene signInSceneSingletonInstance;
	private static DatabaseController dbController;
	private LoggedUser loggedUser;

	private SignInScene() {
	}

	public static SignInScene getInstance() {
		if (signInSceneSingletonInstance == null) {
			signInSceneSingletonInstance = new SignInScene();
		}
		return signInSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		signInSceneViewBuilder();
	}

	private void signInSceneViewBuilder() {

// ANCHOR PANE ITEMS----------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------
// Create the AnchorPane
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setPrefHeight(720.0);
		anchorPane.setPrefWidth(500.0);

		// BACKGROUND IMAGE
		ImageView backgroundImageView = new ImageView();
		backgroundImageView.setFitHeight(720.0);
		backgroundImageView.setFitWidth(500.0);
		backgroundImageView.setPickOnBounds(true);

		Image backgroundImage = new Image(getClass().getResource("/img/background.png").toExternalForm());
		backgroundImageView.setImage(backgroundImage);

		// Add the ImageView directly to the AnchorPane1
		anchorPane.getChildren().add(backgroundImageView);
		////////////////////////////// END OF AnchorPane Settings & bg
		////////////////////////////// image//////////////////////////////

		// Create the ImageView
		ImageView backArrowView = new ImageView();
		backArrowView.setFitHeight(30.0);
		backArrowView.setFitWidth(30.0);
		backArrowView.setPickOnBounds(true);
		backArrowView.setPreserveRatio(true);

		// Load the image and setImage on the ImageArrowView
		Image BackArrowImage = new Image(getClass().getResourceAsStream("/img/Back-Arrow.png"));
		backArrowView.setImage(BackArrowImage);

		Button backButton = new Button();
		backButton.setLayoutX(40.0);
		backButton.setLayoutY(28.0);
		backButton.setGraphic(backArrowView);
		backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

		backButton.setOnAction(e -> {
			try {
				MainScene.getInstance().start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		anchorPane.getChildren().add(backButton);

		Text signInTxt = new Text("Sign in");
		signInTxt.setFill(Color.WHITE);
		signInTxt.setLayoutX(48.0);
		signInTxt.setLayoutY(100.0);
		signInTxt.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 34.0));

		anchorPane.getChildren().add(signInTxt);

		// Create the ImageView
		ImageView logoView = new ImageView();
		logoView.setFitHeight(141.0);
		logoView.setFitWidth(440.0);
		logoView.setLayoutX(30.0);
		logoView.setLayoutY(134.0);
		logoView.setPickOnBounds(true);
		logoView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image logoImage = new Image(getClass().getResourceAsStream("/img/EconoBank-Logo.png"));
		logoView.setImage(logoImage);

		anchorPane.getChildren().add(logoView);

		// ---------------------------------------------------------------------------------------------------
		// end of ANCHORPANE
		// ITEMS----------------------------------------------------------------------------

		// WHITE PANE
		// ITEMS==================================================================================
		// ===================================================================================================
		// White round edges pane
		Pane whiteMiddlePane = new Pane();
		whiteMiddlePane.setLayoutX(0);
		whiteMiddlePane.setLayoutY(290.0);
		whiteMiddlePane.setPrefHeight(430.0);
		whiteMiddlePane.setPrefWidth(500.0);
		whiteMiddlePane.setStyle("-fx-background-color: white; -fx-background-radius: 20 20 0 0;");

		// Create the Text nodes
		Text welcomeTxt = new Text("Welcome back!");
		welcomeTxt.setFill(Color.web("#f49820"));
		welcomeTxt.setLayoutX(55.0);
		welcomeTxt.setLayoutY(51.0);
		welcomeTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text signInToContinueTxt = new Text("Sign in to continue.");
		signInToContinueTxt.setFill(Color.web("#B3B3B3"));
		signInToContinueTxt.setLayoutX(55.0);
		signInToContinueTxt.setLayoutY(75.0);
		signInToContinueTxt.setFont(Font.font("Roboto Regular", 16.0));

		Text emailTxt = new Text("Email or Account Number");
		emailTxt.setFill(Color.web("#B3B3B3"));
		emailTxt.setLayoutX(55.0);
		emailTxt.setLayoutY(110.0);
		emailTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		// text field with text clickable that disapears
		TextField emailInput = new TextField();
		emailInput.setLayoutX(55.0);
		emailInput.setLayoutY(120.0);
		emailInput.setPrefHeight(30.0);
		emailInput.setPrefWidth(380.0);
		emailInput.setPromptText("Enter your email or account number");
		emailInput.setFont(Font.font("Roboto Regular", 16.0));

		Text passwordTxt = new Text("Password");
		passwordTxt.setFill(Color.web("#B3B3B3"));
		passwordTxt.setLayoutX(55.0);
		passwordTxt.setLayoutY(180.0);
		passwordTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		PasswordField passwordInput = new PasswordField();
		passwordInput.setLayoutX(55.0);
		passwordInput.setLayoutY(190.0);
		passwordInput.setPrefHeight(30.0);
		passwordInput.setPrefWidth(380.0);
		passwordInput.setPromptText("Enter your password");
		passwordInput.setFont(Font.font("Roboto Regular", 16.0));

		// Create Sign in Button
		Button forgotPassBtn = new Button("Forgot password?");
		forgotPassBtn.setLayoutX(45.0);
		forgotPassBtn.setLayoutY(225.0);
		forgotPassBtn.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
		forgotPassBtn.setTextFill(Color.web("#F28E1F"));
		forgotPassBtn.setFont(Font.font("Roboto Regular", 16.0));
		forgotPassBtn.setOnAction(e -> {
			try {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Password Reset");
				dialog.setHeaderText("Enter your EMAIL to reset your password:");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {

					String email = result.get();

					TextInputDialog dialog2 = new TextInputDialog();
					dialog2.setTitle("Password Reset");
					dialog2.setHeaderText("Enter your ACCOUNT NUMBER to reset your password:");
					Optional<String> result2 = dialog2.showAndWait();

					if (result2.isPresent()) {

						// fetch database and see if there's a account that has this email AND
						// account number, if so, change the user password to 'temporarypassword'.

						String accNum = result2.get();

						int accNumInt = 0;
						try {
							accNumInt = Integer.parseInt(accNum);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Wrong Bank Account Format");
							alert.setHeaderText(null);
							alert.setContentText("Your bank account is numbers only. 8 digits and it starts by 950.");
							alert.showAndWait();
						}

						// 'forgot password' gets email and bank account number to check and match.
						// by email it gets the user.user_id, and by bank account number it gets
						// bank_account.user_id from the db, if both user.user_id and
						// bank_account.user_id matches then user seems to be the owner of the
						// account, then provided correctly the 2 information, kind of making
						// it a small "2 factor authentication"

						boolean loginCredentials = DatabaseController.checkEmailAndBankAcc(email, accNumInt);// wrong

						if (loginCredentials) {
							// Display dialog for user to enter new password
							TextInputDialog dialogSetPass = new TextInputDialog();
							dialogSetPass.setTitle("Password Reset");
							dialogSetPass.setHeaderText("Set your new password:");
							Optional<String> resultNewPass = dialogSetPass.showAndWait();

							if (resultNewPass.isPresent()) {
								String newPass = resultNewPass.get();

								// Prompt user to re-enter the new password
								TextInputDialog dialogConfirmPass = new TextInputDialog();
								dialogConfirmPass.setTitle("Confirm Password");
								dialogConfirmPass.setHeaderText("Re-enter your new password:");
								Optional<String> resultConfirmPass = dialogConfirmPass.showAndWait();

								if (resultConfirmPass.isPresent()) {
									String confirmPass = resultConfirmPass.get();

									if (newPass.equals(confirmPass)) {
										// Passwords match, set the new password
										int setNewPass = DatabaseController.setNewPassword(email, newPass);

										if (setNewPass == 1) {
											Alert alert = new Alert(AlertType.INFORMATION);
											alert.setTitle("Password Reset Succeeded");
											alert.setHeaderText(null);
											alert.setContentText(
													"Done! Your new password is set. You can sign in now.");
											alert.showAndWait();
										} else {
											Alert alert = new Alert(AlertType.ERROR);
											alert.setTitle("Password Reset Failure");
											alert.setHeaderText(null);
											alert.setContentText("Couldn't set new password, please try again.");
											alert.showAndWait();
										}
									} else {
										// Passwords don't match
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Password Mismatch");
										alert.setHeaderText(null);
										alert.setContentText("The entered passwords do not match. Please try again.");
										alert.showAndWait();
									}
								}
							}
						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Password Reset Failure");
							alert.setHeaderText(null);
							alert.setContentText(
									"Incorrect, the information provided does not match our database information.");
							alert.showAndWait();
						}

					}

				}
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});

		Button signInBtn = new Button("Sign in");
		signInBtn.setLayoutX(180.0);
		signInBtn.setLayoutY(280.0);
		signInBtn.setPrefHeight(30.0);
		signInBtn.setPrefWidth(140.0);
		signInBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		signInBtn.setTextAlignment(TextAlignment.CENTER);
		signInBtn.setTextFill(Color.web("#ffffff"));
		signInBtn.setFont(Font.font("Roboto Bold", 16.0));
		signInBtn.setOnAction(e -> {
			// make required, when log's in, check db if name and surname exist, if not, ask
			// via dialog and UPDATE user_id in db
			try {

				// Sign-in Steps & checks:
				// 1 - get email from user input, check email format
				// 2 - check email & pass match in db
				// 3 - pre-load all entities to "LoggedUser" (singleton class)
				// 4 - if new user, set last_login date and get/set name&surname, else update it

				String email = emailInput.getText().trim();
				String pass = passwordInput.getText();

				// email format check
				boolean emailCheck = EmailValidator.validate(email);

				if (!emailCheck) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Invalid Email.");
					alert.setHeaderText(null);
					alert.setContentText("Please enter a valid email address.");
					alert.showAndWait();
				} else {
					System.out.println("Valid email.");

					// check credentials in database
					int userId = DatabaseController.checkLoginCredentials(email, pass);
					if (userId != 0) {

						loggedUser = LoggedUser.getInstance();
						dbController = DatabaseController.getInstance();

						loggedUser.setUser(dbController.getUserById(userId));
						loggedUser.setLogin(dbController.getLoginByUserId(userId));
						loggedUser.setBankAccount(dbController.getUserBankAcc(userId));

						Statement statement = dbController.getStatement(loggedUser.getBankAccount().getBankAccID());

						// set all transaction list (including transfers) as ArrayList<Transaction>
						statement.setTransactionList(dbController.getStatementTransactionList(statement));
						loggedUser.setStatement(statement);

						// get last login date from db
						java.sql.Timestamp ts = dbController.getLastLogin(userId);

						// new account and have never logged in
						if (ts == null) {

							// new users have never given their name and surnames, this piece of code force
							// them to set their name and surname before getting into their Account Overview

							dbController.showNameSurnameDialogAndSave();
							AccountOverviewScene.getInstance().start(primaryStage);

							// not a new user (there's a TimeStamp in database)
						} else {

							// set new TimeStamp to database
							dbController.updateLastLoginNow(userId);
							// set old last_login so that user can see their previous visit
							loggedUser.getLogin().setLastLogin(ts);

							AccountOverviewScene.getInstance().start(primaryStage);
						}

					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Wrong Email or Password.");
						alert.setHeaderText(null);
						alert.setContentText("Incorrect email or password, try again please.");
						alert.showAndWait();
					}
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		Text alreadyHaveAccTxt = new Text("Don't have an account yet?");
		alreadyHaveAccTxt.setFill(Color.web("#B3B3B3"));
		alreadyHaveAccTxt.setLayoutX(100.0);
		alreadyHaveAccTxt.setLayoutY(375.0);
		alreadyHaveAccTxt.setFont(Font.font("Roboto Regular", 16.0));

//Create Sign in Button
		Button signUpBtn = new Button("Sign Up!");
		signUpBtn.setLayoutX(285.0);
		signUpBtn.setLayoutY(352.0);
		signUpBtn.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
		signUpBtn.setTextFill(Color.web("#F28E1F"));
		signUpBtn.setFont(Font.font("Roboto Bold", FontWeight.BOLD, 16.0));
		signUpBtn.setOnAction(e -> {
			try {
				SignUpScene.getInstance().start(primaryStage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(welcomeTxt, signInToContinueTxt, emailTxt, emailInput, passwordTxt,
				passwordInput, forgotPassBtn, signInBtn, alreadyHaveAccTxt, signUpBtn);
		anchorPane.getChildren().add(whiteMiddlePane);
//////////////////////////////white box end//////////////////////////////

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void setLoggedUser(LoggedUser user) {
		this.loggedUser = user;
	}

}
