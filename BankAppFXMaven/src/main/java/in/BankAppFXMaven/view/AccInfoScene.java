package in.BankAppFXMaven.view;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.User;
import in.BankAppFXMaven.utility.HashingUtility;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AccInfoScene extends Application {

	private static AccountOverviewScene accOverviewSceneSingletonInstance = AccountOverviewScene.getInstance();
	private static AccInfoScene AccInfoSceneSingletonInstance;
	private static DatabaseController dbController = DatabaseController.getInstance();
	private Stage primaryStage;
	private TextField nameField;
	private TextField surnameField;
	private TextField passwordField;
	private static LoggedUser loggedUser;

	private AccInfoScene() {
	}

	public static AccInfoScene getInstance() {
		if (AccInfoSceneSingletonInstance == null) {
			AccInfoSceneSingletonInstance = new AccInfoScene();
		}
		return AccInfoSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		loggedUser = LoggedUser.getInstance();
		
		AccInfoSceneViewBuilder();
	}

	private void AccInfoSceneViewBuilder() {

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setPrefHeight(720.0);
		anchorPane.setPrefWidth(500.0);

		// Create the Background with the ImageView
		ImageView backgroundImageView = new ImageView();
		backgroundImageView.setFitHeight(720.0);
		backgroundImageView.setFitWidth(500.0);
		backgroundImageView.setPickOnBounds(true);
		backgroundImageView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image backgroundImage = new Image(getClass().getResource("/img/background.png").toExternalForm());
		backgroundImageView.setImage(backgroundImage);

		// Add the ImageView to the AnchorPane
		anchorPane.getChildren().add(backgroundImageView);

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
				accOverviewSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		anchorPane.getChildren().add(backButton);

		// Create the ImageView
		ImageView logoView = new ImageView();
		logoView.setFitHeight(141.0);
		logoView.setFitWidth(440.0);
		logoView.setLayoutX(30.0);
		logoView.setLayoutY(60.0);
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

		Pane whiteMiddlePane = new Pane();
		whiteMiddlePane.setLayoutX(0);
		whiteMiddlePane.setLayoutY(240.0);
		whiteMiddlePane.setPrefHeight(480.0);
		whiteMiddlePane.setPrefWidth(500.0);
		whiteMiddlePane.setStyle("-fx-background-color: white; -fx-background-radius: 20 20 0 0;");

		// Create the Text nodes
		Text accInfoTxt = new Text("Account Information");
		accInfoTxt.setFill(Color.web("#f49820"));
		accInfoTxt.setLayoutX(55.0);
		accInfoTxt.setLayoutY(51.0);
		accInfoTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text letsUpdateTxt = new Text("Let's update your account information...");
		letsUpdateTxt.setFill(Color.web("#B3B3B3"));
		letsUpdateTxt.setLayoutX(55.0);
		letsUpdateTxt.setLayoutY(75.0);
		letsUpdateTxt.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(accInfoTxt, letsUpdateTxt);

		Text emailTxt = new Text("Email");
		emailTxt.setFill(Color.web("#B3B3B3"));
		emailTxt.setLayoutX(55.0);
		emailTxt.setLayoutY(110.0);
		emailTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		TextField emailTxtField = new TextField();
		emailTxtField.setLayoutX(55.0);
		emailTxtField.setLayoutY(120.0);
		emailTxtField.setPrefHeight(30.0);
		emailTxtField.setPrefWidth(380.0);
		emailTxtField.setText(loggedUser.getUser().getEmail());
		emailTxtField.setFont(Font.font("Roboto Regular", 16.0));
		emailTxtField.setEditable(false); // Making the TextField non-editable
		emailTxtField.setMouseTransparent(true); // Making the TextField to not respond to mouse clicks
		emailTxtField.setFocusTraversable(false); // Prevent the TextField from being focused
		emailTxtField.setStyle(
				"-fx-text-inner-color: grey; -fx-background-color: #f0f0f0; -fx-border-color: #D6D6D6; -fx-border-radius: 3;");

		Text accNumTxt = new Text("Account Number");
		accNumTxt.setFill(Color.web("#B3B3B3"));
		accNumTxt.setLayoutX(55.0);
		accNumTxt.setLayoutY(180.0);
		accNumTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		TextField accNumTxtField = new TextField();
		accNumTxtField.setLayoutX(55.0);
		accNumTxtField.setLayoutY(190.0);
		accNumTxtField.setPrefHeight(30.0);
		accNumTxtField.setPrefWidth(380.0);
		accNumTxtField.setText(Integer.toString(loggedUser.getBankAccount().getBankAccNum()));
		accNumTxtField.setFont(Font.font("Roboto Regular", 16.0));
		accNumTxtField.setEditable(false); // Making the TextField non-editable
		accNumTxtField.setMouseTransparent(true); // Making the TextField to not respond to mouse clicks
		accNumTxtField.setFocusTraversable(false); // Prevent the TextField from being focused
		accNumTxtField.setStyle(
				"-fx-text-inner-color: grey; -fx-background-color: #f0f0f0; -fx-border-color: #D6D6D6; -fx-border-radius: 3;");

		Text nameTxt = new Text("Name");
		nameTxt.setFill(Color.web("#B3B3B3"));
		nameTxt.setLayoutX(55.0);
		nameTxt.setLayoutY(250.0);
		nameTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		nameField = new TextField();
		nameField.setLayoutX(55.0);
		nameField.setLayoutY(260.0);
		nameField.setPrefHeight(30.0);
		nameField.setPrefWidth(180.0);
		nameField.setText(loggedUser.getUser().getName());
		nameField.setFont(Font.font("Roboto Regular", 16.0));
		nameField.setEditable(false); // Making the TextField non-editable
		nameField.setMouseTransparent(true); // Making the TextField to not respond to mouse clicks
		nameField.setFocusTraversable(false); // Prevent the TextField from being focused
		nameField.setStyle(
				"-fx-text-inner-color: grey; -fx-background-color: #ffffff; -fx-border-color: #D6D6D6; -fx-border-radius: 3;");

		// Create the ImageView
		ImageView nameEditView = new ImageView();
		nameEditView.setFitHeight(30.0);
		nameEditView.setFitWidth(30.0);
		nameEditView.setPickOnBounds(true);
		nameEditView.setPreserveRatio(true);

		// Load the image and setImage on the ImageArrowView
		Image nameEditImage = new Image(getClass().getResourceAsStream("/img/edit.png"));
		nameEditView.setImage(nameEditImage);

		Button nameEditButton = new Button();
		nameEditButton.setLayoutX(198.0);
		nameEditButton.setLayoutY(235.0);
		nameEditButton.setGraphic(nameEditView);
		nameEditButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

		nameEditButton.setOnAction(e -> {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edit Name");
				alert.setHeaderText(null);
				alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.APPLY);

				TextField nameEditingField = new TextField();
				nameEditingField.setPromptText(nameField.getText());
				nameEditingField.setFont(Font.font("Roboto Regular", 16.0));

				VBox vbox = new VBox();
				vbox.getChildren().add(new Label("Edit name to:"));
				vbox.getChildren().add(nameEditingField);
				alert.getDialogPane().setContent(vbox);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == ButtonType.APPLY) {

					nameField.setText(nameEditingField.getText());

//					 save name locally
					loggedUser.getUser().setName(nameEditingField.getText());

					// save user's name change to database
					dbController.setNewName(loggedUser.getUser().getId(), nameEditingField.getText());

					Alert nameChangedAlert = new Alert(AlertType.INFORMATION);
					nameChangedAlert.setTitle("Name Changed.");
					nameChangedAlert.setHeaderText(null);
					nameChangedAlert.setContentText("Name updated!");
					nameChangedAlert.showAndWait();
				} else {
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		whiteMiddlePane.getChildren().add(nameEditButton);

		Text surnameTxt = new Text("Surname");
		surnameTxt.setFill(Color.web("#B3B3B3"));
		surnameTxt.setLayoutX(255.0);
		surnameTxt.setLayoutY(250.0);
		surnameTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		surnameField = new TextField();
		surnameField.setLayoutX(255.0);
		surnameField.setLayoutY(260.0);
		surnameField.setPrefHeight(30.0);
		surnameField.setPrefWidth(180.0);
		surnameField.setText(loggedUser.getUser().getSurname());
		surnameField.setFont(Font.font("Roboto Regular", 16.0));
		surnameField.setEditable(false); // Making the TextField non-editable
		surnameField.setMouseTransparent(true); // Making the TextField to not respond to mouse clicks
		surnameField.setFocusTraversable(false); // Prevent the TextField from being focused
		surnameField.setStyle(
				"-fx-text-inner-color: grey; -fx-background-color: #ffffff; -fx-border-color: #D6D6D6; -fx-border-radius: 3;");

		// Create the ImageView
		ImageView surnameEditView = new ImageView();
		surnameEditView.setFitHeight(30.0);
		surnameEditView.setFitWidth(30.0);
		surnameEditView.setPickOnBounds(true);
		surnameEditView.setPreserveRatio(true);

		// Load the image and setImage on the ImageArrowView
		Image surnameEditImage = new Image(getClass().getResourceAsStream("/img/edit.png"));
		surnameEditView.setImage(surnameEditImage);

		Button surnameEditButton = new Button();
		surnameEditButton.setLayoutX(398.0);
		surnameEditButton.setLayoutY(235.0);
		surnameEditButton.setGraphic(surnameEditView);
		surnameEditButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

		surnameEditButton.setOnAction(e -> {
			try {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edit Surname");
				alert.setHeaderText(null);
				alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.APPLY);

				TextField surnameEditingField = new TextField();
				surnameEditingField.setPromptText(surnameField.getText());
				surnameEditingField.setFont(Font.font("Roboto Regular", 16.0));

				VBox vbox = new VBox();
				vbox.getChildren().add(new Label("Edit surname to:"));
				vbox.getChildren().add(surnameEditingField);
				alert.getDialogPane().setContent(vbox);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == ButtonType.APPLY) {

					surnameField.setText(surnameEditingField.getText());

//					 save surname locally
					loggedUser.getUser().setSurname(surnameEditingField.getText());

					// save user's surname change to database
					dbController.setNewSurname(loggedUser.getUser().getId(), surnameEditingField.getText());

					Alert surnameChangedAlert = new Alert(AlertType.INFORMATION);
					surnameChangedAlert.setTitle("Surname Changed.");
					surnameChangedAlert.setHeaderText(null);
					surnameChangedAlert.setContentText("Surname updated!");
					surnameChangedAlert.showAndWait();
				} else {
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		whiteMiddlePane.getChildren().add(surnameEditButton);

		Text passwordTxt = new Text("Password");
		passwordTxt.setFill(Color.web("#B3B3B3"));
		passwordTxt.setLayoutX(55.0);
		passwordTxt.setLayoutY(320.0);
		passwordTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		passwordField = new PasswordField();
		passwordField.setLayoutX(55.0);
		passwordField.setLayoutY(330.0);
		passwordField.setPrefHeight(30.0);
		passwordField.setPrefWidth(380.0);
		passwordField.setPromptText("**********");
		passwordField.setFont(Font.font("Roboto Regular", 16.0));
		passwordField.setEditable(false);
		passwordField.setMouseTransparent(true); // Making the TextField to not respond to mouse clicks
		passwordField.setFocusTraversable(false); // Prevent the TextField from being focused
		passwordField.setStyle(
				"-fx-text-inner-color: grey; -fx-background-color: #ffffff; -fx-border-color: #D6D6D6; -fx-border-radius: 3;");

		// Create the ImageView
		ImageView passwordEditView = new ImageView();
		passwordEditView.setFitHeight(30.0);
		passwordEditView.setFitWidth(30.0);
		passwordEditView.setPickOnBounds(true);
		passwordEditView.setPreserveRatio(true);

		// Load the image and setImage on the ImageArrowView
		Image passwordEditImage = new Image(getClass().getResourceAsStream("/img/edit.png"));
		passwordEditView.setImage(passwordEditImage);

		Button passwordEditButton = new Button();
		passwordEditButton.setLayoutX(398.0);
		passwordEditButton.setLayoutY(305.0);
		passwordEditButton.setGraphic(passwordEditView);
		passwordEditButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

		passwordEditButton.setOnAction(e -> {
			try {

				String currentPass = loggedUser.getLogin().getPasswordHash();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edit Password");
				alert.setHeaderText(null);
				alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.APPLY);
				alert.getDialogPane().setPrefWidth(350);

				// --------------------------------------------------------------------------
				Text currentPassTxt = new Text("Current Password");
				currentPassTxt.setFill(Color.web("#B3B3B3"));
				currentPassTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 14.0));

				PasswordField passwordCurrentEditingField = new PasswordField();
				passwordCurrentEditingField.setPromptText("Enter your current password");
				passwordCurrentEditingField.setFont(Font.font("Roboto Regular", 14.0));
				// --------------------------------------------------------------------------

				// --------------------------------------------------------------------------
				Text newPassTxt = new Text("New Password");
				newPassTxt.setFill(Color.web("#B3B3B3"));
				newPassTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

				PasswordField passwordNewEditingField = new PasswordField();
				passwordNewEditingField.setPromptText("Enter your new password");
				passwordNewEditingField.setFont(Font.font("Roboto Regular", 14.0));
				// --------------------------------------------------------------------------

				// --------------------------------------------------------------------------
				Text confirmNewPassTxt = new Text("Confirm New Password");
				confirmNewPassTxt.setFill(Color.web("#B3B3B3"));
				confirmNewPassTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

				PasswordField passwordNewConfirmEditingField = new PasswordField();
				passwordNewConfirmEditingField.setPromptText("Confirm your new password");
				passwordNewConfirmEditingField.setFont(Font.font("Roboto Regular", 16.0));
				// --------------------------------------------------------------------------

				VBox vbox = new VBox();
				vbox.getChildren().addAll(currentPassTxt, passwordCurrentEditingField, newPassTxt,
						passwordNewEditingField, confirmNewPassTxt, passwordNewConfirmEditingField);
				alert.getDialogPane().setContent(vbox);
				vbox.setSpacing(10);

				Optional<ButtonType> result;
				boolean correctPassword = false;

				do {
					result = alert.showAndWait();

					// check if user has entered something before hitting apply
					if (result.isPresent() && result.get() == ButtonType.APPLY) {
						
						//hash inputed current pass, to be able to compare to currentPass from db
						String hashedPasswordCurrentEditingField = passwordCurrentEditingField.getText();
						
						try {
							hashedPasswordCurrentEditingField = HashingUtility.hashPassword(hashedPasswordCurrentEditingField);
						} catch (NoSuchAlgorithmException e5) {
							// TODO Auto-generated catch block
							e5.printStackTrace();
						}
						
						if (hashedPasswordCurrentEditingField.equals(currentPass)) {

							// Check if the new password and confirm password fields match
							if (passwordNewEditingField.getText().equals(passwordNewConfirmEditingField.getText())) {
								correctPassword = true;
								// Save the new password here
								String newPassword = passwordNewEditingField.getText();

								System.out.println("New password: " + newPassword + ". Without hashing.");

								try {
									newPassword = HashingUtility.hashPassword(newPassword);
								} catch (NoSuchAlgorithmException e3) {
									// TODO Auto-generated catch block
									e3.printStackTrace();
								}
								// local hashed pass
								loggedUser.getLogin().setPasswordHash(newPassword);

								System.out.println(
										"New password: " + loggedUser.getLogin().getPasswordHash() + ". hashed.\n");

								// save new password to database
								dbController.setNewPassword(loggedUser.getUser().getEmail(), passwordNewEditingField.getText());

								Alert passChangedAlert = new Alert(AlertType.INFORMATION);
								passChangedAlert.setTitle("Password Changed.");
								passChangedAlert.setHeaderText(null);
								passChangedAlert.setContentText("Password updated!");
								passChangedAlert.showAndWait();

							} else {
								Alert errorAlert = new Alert(AlertType.ERROR);
								errorAlert.setTitle("Error");
								errorAlert.setHeaderText(null);
								errorAlert.setContentText("New passwords do not match.");
								errorAlert.showAndWait();
							}

							// password does not match to the current password (first field)
						} else {
							Alert errorAlert = new Alert(AlertType.ERROR);
							errorAlert.setTitle("Error");
							errorAlert.setHeaderText(null);
							errorAlert.setContentText("The current password does not match.");
							errorAlert.showAndWait();
						}
						//testing
						System.out.println("Current pass 'db': " + currentPass + " \nCurrent pass input: " + passwordCurrentEditingField.getText() + " \nCurrent pass input hashed: " + hashedPasswordCurrentEditingField + " \nPassword field 1: " + passwordNewEditingField.getText() + " \nPassword field 2: "+passwordNewConfirmEditingField.getText()+".\n");
					}
				} while (result.isPresent() && result.get() == ButtonType.APPLY && !correctPassword);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		whiteMiddlePane.getChildren().add(passwordEditButton);

		whiteMiddlePane.getChildren().addAll(emailTxt, emailTxtField, accNumTxt, accNumTxtField, nameTxt, nameField,
				surnameTxt, surnameField, passwordTxt, passwordField);

		Button backBtn = new Button("Back");
		backBtn.setLayoutX(172.0);
		backBtn.setLayoutY(385.0);
		backBtn.setPrefHeight(30.0);
		backBtn.setPrefWidth(140.0);
		backBtn.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 8px; -fx-cursor: hand;");
		backBtn.setTextAlignment(TextAlignment.CENTER);
		backBtn.setTextFill(Color.web("#ffffff"));
		backBtn.setFont(Font.font("Roboto Bold", 16.0));
		backBtn.setOnAction(e -> {
			try {
				accOverviewSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button saveBtn = new Button("Save");
		saveBtn.setLayoutX(255.0);
		saveBtn.setLayoutY(385.0);
		saveBtn.setPrefHeight(30.0);
		saveBtn.setPrefWidth(140.0);
		saveBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		saveBtn.setTextAlignment(TextAlignment.CENTER);
		saveBtn.setTextFill(Color.web("#ffffff"));
		saveBtn.setFont(Font.font("Roboto Bold", 16.0));
		saveBtn.setOnAction(e -> {
			try {

				Alert passChangedAlert = new Alert(AlertType.INFORMATION);
				passChangedAlert.setTitle("Password Changed.");
				passChangedAlert.setHeaderText(null);
				passChangedAlert.setContentText("New password saved to the database!");
				passChangedAlert.showAndWait();
				// Save info on database

//				boolean amountCheck = db.checkBalanceAvailability(depositTxtInput.getText());
//				
//				if (!amountCheck) {
//				    Alert alert = new Alert(Alert.AlertType.ERROR);
//				    alert.setTitle("Insufficient Funds.");
//				    alert.setHeaderText(null);
//				    alert.setContentText("Not enough balance, please enter a lower amount.");
//					alert.showAndWait();
//				} else {
//					System.out.println("Valid withdrawal.");
//				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(backBtn);

		anchorPane.getChildren().add(whiteMiddlePane);

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
