package in.BankAppFXMaven.view;

import java.util.Optional;

import in.BankAppFXMaven.controller.DatabaseController;
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
	private static MainScene mainSceneSingletonInstance = MainScene.getInstance();
	private static SignUpScene signUpSceneSingletonInstance = SignUpScene.getInstance();
	private static TransactionScene transactionsSceneSingletonInstance = TransactionScene.getInstance();
	private static SignInScene signInSceneSingletonInstance;

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

		Image backgroundImage = new Image(getClass().getResource("../img/background.png").toExternalForm());
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
		Image BackArrowImage = new Image(getClass().getResourceAsStream("../img/Back-Arrow.png"));
		backArrowView.setImage(BackArrowImage);

		Button backButton = new Button();
		backButton.setLayoutX(40.0);
		backButton.setLayoutY(28.0);
		backButton.setGraphic(backArrowView);
		backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

		backButton.setOnAction(e -> {
			try {
				mainSceneSingletonInstance.start(primaryStage);
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
		Image logoImage = new Image(getClass().getResourceAsStream("../img/EconoBank-Logo.png"));
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
		TextField emailTxtInput = new TextField();
		emailTxtInput.setLayoutX(55.0);
		emailTxtInput.setLayoutY(120.0);
		emailTxtInput.setPrefHeight(30.0);
		emailTxtInput.setPrefWidth(380.0);
		emailTxtInput.setPromptText("Enter your email or account number");
		emailTxtInput.setFont(Font.font("Roboto Regular", 16.0));

		Text newPasswordTxt = new Text("Password");
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
				if (result.isPresent()){
					
				    String email = result.get();
				    
				    TextInputDialog dialog2 = new TextInputDialog();
					dialog2.setTitle("Password Reset");
					dialog2.setHeaderText("Enter your ACCOUNT NUMBER to reset your password:");
					Optional<String> result2 = dialog2.showAndWait();
					
					String accNum = result2.get();
					
					//fetch database and see if there's a account that has this email AND account number, if so, change the user password to 10.
					
					boolean accValidity = DatabaseController.getAccValidity(email, accNum);
					
					if(accValidity) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
					    alert.setTitle("Password Reset Succeded");
					    alert.setHeaderText(null);
					    alert.setContentText("Correct, your temporary new password is: 10. Sign in and proceed to imediately change the password please.");
					    alert.showAndWait();
					    
					} else {
						Alert alert = new Alert(AlertType.ERROR);
					    alert.setTitle("Password Reset Failure");
					    alert.setHeaderText(null);
					    alert.setContentText("Incorrect, the information provided does not match in our database information.");
					    alert.showAndWait();
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
			try {
				// CHECK AND CREATE USER ON DB
				
				transactionsSceneSingletonInstance.start(primaryStage);
//				boolean emailCheck = EmailValidator.validate(emailTxtInput.getText());
//
//				if (!emailCheck) {
//					Alert alert = new Alert(Alert.AlertType.ERROR);
//					alert.setTitle("Invalid Email.");
//					alert.setHeaderText(null);
//					alert.setContentText("Please enter a valid email address.");
//					alert.showAndWait();
//				} else {
//					System.out.println("Valid email.");
//				}
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
				signUpSceneSingletonInstance.start(primaryStage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(welcomeTxt, signInToContinueTxt, emailTxt, emailTxtInput, newPasswordTxt, passwordField, forgotPassBtn, signInBtn, alreadyHaveAccTxt, signUpBtn);
		anchorPane.getChildren().add(whiteMiddlePane);
//////////////////////////////white box end//////////////////////////////
		
		Scene scene = new Scene(anchorPane);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
