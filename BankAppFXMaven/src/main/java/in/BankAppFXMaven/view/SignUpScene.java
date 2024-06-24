package in.BankAppFXMaven.view;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.User;
import in.BankAppFXMaven.utility.EmailValidator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

public class SignUpScene extends Application {

	private Stage primaryStage;
	private static SignUpScene signUpSceneSingletonInstance;

	private SignUpScene() {
	}

	public static SignUpScene getInstance() {
		if (signUpSceneSingletonInstance == null) {
			signUpSceneSingletonInstance = new SignUpScene();
		}
		return signUpSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		signUpSceneViewBuilder();
	}

	public void signUpSceneViewBuilder() {

//ANCHOR PANE ITEMS----------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------
//Create the AnchorPane
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setPrefHeight(720.0);
		anchorPane.setPrefWidth(500.0);

//BACKGROUND IMAGE
		ImageView backgroundImageView = new ImageView();
		backgroundImageView.setFitHeight(720.0);
		backgroundImageView.setFitWidth(500.0);
		backgroundImageView.setPickOnBounds(true);

		Image backgroundImage = new Image(getClass().getResource("/img/background.png").toExternalForm());
		backgroundImageView.setImage(backgroundImage);

//Add the ImageView directly to the AnchorPane1
		anchorPane.getChildren().add(backgroundImageView);
//////////////////////////////END OF AnchorPane Settings & bg image//////////////////////////////

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

		Text signUpTxt = new Text("Sign up");
		signUpTxt.setFill(Color.WHITE);
		signUpTxt.setLayoutX(48.0);
		signUpTxt.setLayoutY(100.0);
		signUpTxt.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 34.0));

		anchorPane.getChildren().add(signUpTxt);

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

//---------------------------------------------------------------------------------------------------
//end of ANCHORPANE ITEMS----------------------------------------------------------------------------

//WHITE PANE ITEMS==================================================================================
//===================================================================================================
//White round edges pane
		Pane whiteMiddlePane = new Pane();
		whiteMiddlePane.setLayoutX(0);
		whiteMiddlePane.setLayoutY(290.0);
		whiteMiddlePane.setPrefHeight(430.0);
		whiteMiddlePane.setPrefWidth(500.0);
		whiteMiddlePane.setStyle("-fx-background-color: white; -fx-background-radius: 20 20 0 0;");

//Create the Text nodes
		Text welcomeTxt = new Text("Welcome!");
		welcomeTxt.setFill(Color.web("#f49820"));
		welcomeTxt.setLayoutX(55.0);
		welcomeTxt.setLayoutY(51.0);
		welcomeTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text createTxt = new Text("create your account and start economizing!");
		createTxt.setFill(Color.web("#B3B3B3"));
		createTxt.setLayoutX(55.0);
		createTxt.setLayoutY(75.0);
		createTxt.setFont(Font.font("Roboto Regular", 16.0));

		Text emailTxt = new Text("Email");
		emailTxt.setFill(Color.web("#B3B3B3"));
		emailTxt.setLayoutX(55.0);
		emailTxt.setLayoutY(110.0);
		emailTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		// text field with text clickable that disapears
		TextField emailTxtInput = new TextField();
		emailTxtInput.setLayoutX(55.0);
		emailTxtInput.setLayoutY(120.0);
		emailTxtInput.setPrefHeight(30.0);
		emailTxtInput.setPrefWidth(380.0);
		emailTxtInput.setPromptText("Enter your email");
		emailTxtInput.setFont(Font.font("Roboto Regular", 16.0));

		Text newPasswordTxt = new Text("New password");
		newPasswordTxt.setFill(Color.web("#B3B3B3"));
		newPasswordTxt.setLayoutX(55.0);
		newPasswordTxt.setLayoutY(180.0);
		newPasswordTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		PasswordField passwordField = new PasswordField();
		passwordField.setLayoutX(55.0);
		passwordField.setLayoutY(190.0);
		passwordField.setPrefHeight(30.0);
		passwordField.setPrefWidth(380.0);
		passwordField.setPromptText("Enter your password");
		passwordField.setFont(Font.font("Roboto Regular", 16.0));

		Text confirmPassTxt = new Text("Confirm password");
		confirmPassTxt.setFill(Color.web("#B3B3B3"));
		confirmPassTxt.setLayoutX(55.0);
		confirmPassTxt.setLayoutY(250.0);
		confirmPassTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		PasswordField passwordConfirm = new PasswordField();
		passwordConfirm.setLayoutX(55.0);
		passwordConfirm.setLayoutY(260.0);
		passwordConfirm.setPrefHeight(30.0);
		passwordConfirm.setPrefWidth(380.0);
		passwordConfirm.setPromptText("Re-enter your password");
		passwordConfirm.setFont(Font.font("Roboto Regular", 16.0));

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setLayoutX(90.0);
		cancelBtn.setLayoutY(310.0);
		cancelBtn.setPrefHeight(30.0);
		cancelBtn.setPrefWidth(140.0);
		cancelBtn.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 8px; -fx-cursor: hand;");
		cancelBtn.setTextAlignment(TextAlignment.CENTER);
		cancelBtn.setTextFill(Color.web("#ffffff"));
		cancelBtn.setFont(Font.font("Roboto Bold", 16.0));
		cancelBtn.setOnAction(e -> {
			try {
				MainScene.getInstance().start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button signUpBtn = new Button("Sign up");
		signUpBtn.setLayoutX(255.0);
		signUpBtn.setLayoutY(310.0);
		signUpBtn.setPrefHeight(30.0);
		signUpBtn.setPrefWidth(140.0);
		signUpBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		signUpBtn.setTextAlignment(TextAlignment.CENTER);
		signUpBtn.setTextFill(Color.web("#ffffff"));
		signUpBtn.setFont(Font.font("Roboto Bold", 16.0));
		signUpBtn.setOnAction(e -> {
			try {
				// CHECK AND CREATE USER ON DB

				boolean emailCheck = EmailValidator.validate(emailTxtInput.getText());

				if (!emailCheck) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Invalid Email.");
					alert.setHeaderText(null);
					alert.setContentText("Please enter a valid email address.");
					alert.showAndWait();
				} else {
					System.out.println("Valid email.");

					// both password input field does not match
					if (!passwordField.equals(passwordConfirm)) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Password does not match.");
						alert.setHeaderText(null);
						alert.setContentText("The password and password confirmation does not match.");
						alert.showAndWait();
					} else {
						
						// check email existence in db, if so display dialog "User already exists."
						DatabaseController db = DatabaseController.getInstance();

						User user = db.getUser(emailTxtInput.getText());

						if (user != null) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("User already exists.");
							alert.setHeaderText(null);
							alert.setContentText("This email is already being used.");
							alert.showAndWait();
							
							//if null (user does not exist, then create user.
						} else {

							db.createUser(emailTxtInput.getText(), passwordField.getText());

							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Account created.");
							alert.setHeaderText(null);
							alert.setContentText("Welcome! Your account has been created! You can now Sign-in.");
							alert.showAndWait();
						}
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		Text alreadyHaveAccTxt = new Text("Already have an account?");
		alreadyHaveAccTxt.setFill(Color.web("#B3B3B3"));
		alreadyHaveAccTxt.setLayoutX(120.0);
		alreadyHaveAccTxt.setLayoutY(385.0);
		alreadyHaveAccTxt.setFont(Font.font("Roboto Regular", 16.0));

//Create Sign in Button
		Button signInBtn = new Button("Sign in!");
		signInBtn.setLayoutX(295.0);
		signInBtn.setLayoutY(362.0);
		signInBtn.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
		signInBtn.setTextFill(Color.web("#F28E1F"));
		signInBtn.setFont(Font.font("Roboto Bold", FontWeight.BOLD, 16.0));
		signInBtn.setOnAction(e -> {
			try {
				SignInScene.getInstance().start(primaryStage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});

//Add the Text nodes and Button to the Pane
//		pane.getChildren().addAll(welcomeTxt, text2, emailTxt, signInBtn);
		whiteMiddlePane.getChildren().addAll(welcomeTxt, createTxt, emailTxt, emailTxtInput, newPasswordTxt,
				passwordField, confirmPassTxt, passwordConfirm, cancelBtn, signUpBtn, alreadyHaveAccTxt, signInBtn);
		anchorPane.getChildren().add(whiteMiddlePane);
//////////////////////////////white box end//////////////////////////////

//====================================================================================================
//END OF "WHITE PANE ITEMS============================================================================

		Scene scene = new Scene(anchorPane);
//MainSceneController controller = loader.getController();
//controller.start(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public User getUser(String query) {
		// db search query

		return new User();
	}

	public void checkExistingUser(User user) {
	}
}