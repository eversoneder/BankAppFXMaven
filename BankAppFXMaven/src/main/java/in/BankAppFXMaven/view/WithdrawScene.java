package in.BankAppFXMaven.view;

import java.sql.Timestamp;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.Statement;
import in.BankAppFXMaven.model.Transaction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

public class WithdrawScene extends Application {

	private Stage primaryStage;
	private static WithdrawScene withdrawSceneSingletonInstance;
	private static AccountOverviewScene accOverviewSingletonInstance = AccountOverviewScene.getInstance();

	private WithdrawScene() {
	}

	public static WithdrawScene getInstance() {
		if (withdrawSceneSingletonInstance == null) {
			withdrawSceneSingletonInstance = new WithdrawScene();
		}
		return withdrawSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

		withdrawSceneSingletonInstance();
	}

	private void withdrawSceneSingletonInstance() {

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
				accOverviewSingletonInstance = AccountOverviewScene.getInstance();
				accOverviewSingletonInstance.start(primaryStage);
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
		Text transactionsTxt = new Text("Withdraw");
		transactionsTxt.setFill(Color.web("#f49820"));
		transactionsTxt.setLayoutX(55.0);
		transactionsTxt.setLayoutY(51.0);
		transactionsTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text depositAndWithdrawTxt = new Text("Deposit and withdraw at any time 24/7.");
		depositAndWithdrawTxt.setFill(Color.web("#B3B3B3"));
		depositAndWithdrawTxt.setLayoutX(55.0);
		depositAndWithdrawTxt.setLayoutY(75.0);
		depositAndWithdrawTxt.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(transactionsTxt, depositAndWithdrawTxt);

		// Create the Background with the ImageView
		ImageView withdrawIconImageView = new ImageView();
		withdrawIconImageView.setFitWidth(250.0);
		withdrawIconImageView.setLayoutX(125.0);
		withdrawIconImageView.setLayoutY(110.0);
		withdrawIconImageView.setPickOnBounds(true);
		withdrawIconImageView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image withdrawIconImage = new Image(getClass().getResource("/img/withdraw-icon-grey-lg.png").toExternalForm());
		withdrawIconImageView.setImage(withdrawIconImage);

		// Add the ImageView to the AnchorPane
		whiteMiddlePane.getChildren().add(withdrawIconImageView);

		Text withdrawAmountTxt = new Text("Withdraw Amount");
		withdrawAmountTxt.setFill(Color.web("#B3B3B3"));
		withdrawAmountTxt.setLayoutX(55.0);
		withdrawAmountTxt.setLayoutY(310.0);
		withdrawAmountTxt.setFont(Font.font("Roboto Regular", FontWeight.BOLD, 16.0));

		// text field with text clickable that disapears
		TextField withdrawTxtInput = new TextField();
		withdrawTxtInput.setLayoutX(55.0);
		withdrawTxtInput.setLayoutY(320.0);
		withdrawTxtInput.setPrefHeight(30.0);
		withdrawTxtInput.setPrefWidth(380.0);
		withdrawTxtInput.setPromptText("How much would you like to withdraw?");
		withdrawTxtInput.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(withdrawAmountTxt, withdrawTxtInput);

		Button backBtn = new Button("Back");
		backBtn.setLayoutX(90.0);
		backBtn.setLayoutY(370.0);
		backBtn.setPrefHeight(30.0);
		backBtn.setPrefWidth(140.0);
		backBtn.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 8px; -fx-cursor: hand;");
		backBtn.setTextAlignment(TextAlignment.CENTER);
		backBtn.setTextFill(Color.web("#ffffff"));
		backBtn.setFont(Font.font("Roboto Bold", 16.0));
		backBtn.setOnAction(e -> {
			try {
				accOverviewSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button confirmBtn = new Button("Confirm");
		confirmBtn.setLayoutX(255.0);
		confirmBtn.setLayoutY(370.0);
		confirmBtn.setPrefHeight(30.0);
		confirmBtn.setPrefWidth(140.0);
		confirmBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		confirmBtn.setTextAlignment(TextAlignment.CENTER);
		confirmBtn.setTextFill(Color.web("#ffffff"));
		confirmBtn.setFont(Font.font("Roboto Bold", 16.0));
		confirmBtn.setOnAction(e -> {
			try {

				LoggedUser loggedUser = LoggedUser.getInstance();
				double currentBalance = loggedUser.getBankAccount().getBankAccBalance();

				// number format validation
				try {
					// get input amount
					double withdrawInput = Double.parseDouble(withdrawTxtInput.getText());

					// if user have enough to withdraw
					if (withdrawInput <= currentBalance) {

						DatabaseController dbController = DatabaseController.getInstance();

						// make the withdraw and subtract from user's balance
						int withdrawalResponse = dbController.updateAccountBalance(-withdrawInput);

						// successful withdrawal case
						if (withdrawalResponse == 1) {
							// message display success withdrawal
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Withdraw Succeeded");
							alert.setHeaderText(null);
							alert.setContentText("You withdrew €" + withdrawInput + " from your bank account.");
							alert.showAndWait();

							// now also add the transaction database entry
							Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis());

							Transaction newWithdrawalTransaction = new Transaction(
									loggedUser.getBankAccount().getBankAccID(), "withdraw", timeStamp, -withdrawInput);

							// upload transaction to database
							int transactionResponse = dbController.addNewTransaction(newWithdrawalTransaction);

							if (transactionResponse == 0) {
								System.out.println("Couldn't add transaction entry to database.");
							}

							// load again the updated data from database to local, both account balance and
							// transaction list

							// download bank account after withdrawal
							loggedUser.setBankAccount(dbController.getUserBankAcc(loggedUser.getUser().getId()));

							// download transaction after withdrawal
							Statement statement = loggedUser.getStatement();
							statement.setTransactionList(dbController.getStatementTransactionList(statement));
							loggedUser.setStatement(statement);

							withdrawTxtInput.clear();

						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Couldn't withdraw.");
							alert.setHeaderText(null);
							alert.setContentText("Error while withdrawing.");
							alert.showAndWait();
						}
						// load again bank account and update loggedUser's balance

					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Not enough funds");
						alert.setHeaderText(null);
						alert.setContentText("Balance is lower than the withdraw amount, enter a lower amount.");
						alert.showAndWait();
					}
				} catch (Exception e1) {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Amount format error.");
					alert.setHeaderText(null);
					alert.setContentText("Enter amount as number format like '80' or '80.80'.");
					alert.showAndWait();
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(backBtn, confirmBtn);

		anchorPane.getChildren().add(whiteMiddlePane);

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
