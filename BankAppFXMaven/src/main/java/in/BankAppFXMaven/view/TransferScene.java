package in.BankAppFXMaven.view;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.BankAccount;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class TransferScene extends Application {

	private Stage primaryStage;
	private static TransferScene transferSceneSingletonInstance;
	private static AccountOverviewScene transactionsSceneSingletonInstance = AccountOverviewScene.getInstance();
	private static DatabaseController dbController;
	private static LoggedUser loggedUser;

	private TransferScene() {
	}

	public static TransferScene getInstance() {
		if (transferSceneSingletonInstance == null) {
			transferSceneSingletonInstance = new TransferScene();
		}
		return transferSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

		loggedUser = LoggedUser.getInstance();

		transferSceneViewBuilder();
	}

	private void transferSceneViewBuilder() {
		// TODO Auto-generated method stub

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
				transactionsSceneSingletonInstance.start(primaryStage);
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
		Text transferTxt = new Text("Transfer");
		transferTxt.setFill(Color.web("#f49820"));
		transferTxt.setLayoutX(55.0);
		transferTxt.setLayoutY(51.0);
		transferTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text yourMoneyTxt = new Text("Your money’s journey, just a tap away.");
		yourMoneyTxt.setFill(Color.web("#B3B3B3"));
		yourMoneyTxt.setLayoutX(55.0);
		yourMoneyTxt.setLayoutY(75.0);
		yourMoneyTxt.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(transferTxt, yourMoneyTxt);

		// Create the Background with the ImageView
		ImageView transferIconImageView = new ImageView();
		transferIconImageView.setFitWidth(200.0);
		transferIconImageView.setLayoutX(145.0);
		transferIconImageView.setLayoutY(100.0);
		transferIconImageView.setPickOnBounds(true);
		transferIconImageView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image withdrawIconImage = new Image(getClass().getResource("/img/transfer-icon-grey-lg.png").toExternalForm());
		transferIconImageView.setImage(withdrawIconImage);

		// Add the ImageView to the AnchorPane
		whiteMiddlePane.getChildren().add(transferIconImageView);

		Text toTxt = new Text("To:");
		toTxt.setFill(Color.web("#B3B3B3"));
		toTxt.setLayoutX(55.0);
		toTxt.setLayoutY(270.0);
		toTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		// text field with text clickable that disapears
		TextField toTxtInput = new TextField();
		toTxtInput.setLayoutX(55.0);
		toTxtInput.setLayoutY(280.0);
		toTxtInput.setPrefHeight(30.0);
		toTxtInput.setPrefWidth(380.0);
		toTxtInput.setPromptText("Enter the recipient's email.");
		toTxtInput.setFont(Font.font("Roboto Regular", 16.0));

		Text transferAmountTxt = new Text("Transfer Amount");
		transferAmountTxt.setFill(Color.web("#B3B3B3"));
		transferAmountTxt.setLayoutX(55.0);
		transferAmountTxt.setLayoutY(340.0);
		transferAmountTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		// text field with text clickable that disapears
		TextField transferAmountTxtInput = new TextField();
		transferAmountTxtInput.setLayoutX(55.0);
		transferAmountTxtInput.setLayoutY(350.0);
		transferAmountTxtInput.setPrefHeight(30.0);
		transferAmountTxtInput.setPrefWidth(380.0);
		transferAmountTxtInput.setPromptText("How much would you like to transfer?");
		transferAmountTxtInput.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(toTxt, toTxtInput, transferAmountTxt, transferAmountTxtInput);

		Button confirmBtn = new Button("Confirm");
		confirmBtn.setLayoutX(255.0);
		confirmBtn.setLayoutY(400.0);
		confirmBtn.setPrefHeight(30.0);
		confirmBtn.setPrefWidth(140.0);
		confirmBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		confirmBtn.setTextAlignment(TextAlignment.CENTER);
		confirmBtn.setTextFill(Color.web("#ffffff"));
		confirmBtn.setFont(Font.font("Roboto Bold", 16.0));
		confirmBtn.setOnAction(e -> {
			try {
				// CHECK IF AMOUNT IS AVAILABLE IN BALANCE ON DB

				double currentBalance = loggedUser.getBankAccount().getBankAccBalance();

				double transferAmountInput = Double.parseDouble(transferAmountTxtInput.getText());

				// have money
				if (transferAmountInput < currentBalance) {

					// get recipient and check if valid email
					String recipientEmail = toTxtInput.getText();

					// get user by email
					User recipientUser = dbController.getUserByEmail(recipientEmail);

					if (recipientUser != null) {

						// get bank of this user and transfer to his balance
						BankAccount RecipientBankAcc = dbController.getUserBankAcc(recipientUser.getId());
						

					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("User error.");
						alert.setHeaderText(null);
						alert.setContentText("User with this email does not exist. Try again please.");
						alert.showAndWait();
					}

				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Insufficient Funds.");
					alert.setHeaderText(null);
					alert.setContentText("Insufficient Funds, not enough balance.");
					alert.showAndWait();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setLayoutX(90.0);
		cancelBtn.setLayoutY(400.0);
		cancelBtn.setPrefHeight(30.0);
		cancelBtn.setPrefWidth(140.0);
		cancelBtn.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 8px; -fx-cursor: hand;");
		cancelBtn.setTextAlignment(TextAlignment.CENTER);
		cancelBtn.setTextFill(Color.web("#ffffff"));
		cancelBtn.setFont(Font.font("Roboto Bold", 16.0));
		cancelBtn.setOnAction(e -> {
			try {
				transactionsSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(cancelBtn, confirmBtn);

		anchorPane.getChildren().add(whiteMiddlePane);

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
