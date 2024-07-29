package in.BankAppFXMaven.view;

import java.time.LocalDate;
import java.util.ArrayList;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.BankAccount;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.Transaction;
import in.BankAppFXMaven.model.Transfer;
import in.BankAppFXMaven.model.User;
import in.BankAppFXMaven.utility.TimeStampToYear;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BalanceScene extends Application {

	private Stage primaryStage;
	private static BalanceScene balanceSceneSingletonInstance;
	private static AccountOverviewScene transactionSceneSingletonInstance;
	private static DatabaseController dbController;
	private LoggedUser loggedUser;

	private BalanceScene() {
	}

	public static BalanceScene getInstance() {
		if (balanceSceneSingletonInstance == null) {
			balanceSceneSingletonInstance = new BalanceScene();
		}
		return balanceSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

		balanceSceneViewBuilder();
	}

	private void balanceSceneViewBuilder() {
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
				transactionSceneSingletonInstance = AccountOverviewScene.getInstance();
				transactionSceneSingletonInstance.start(primaryStage);
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
		Text balanceHeaderTxt = new Text("Balance");
		balanceHeaderTxt.setFill(Color.web("#f49820"));
		balanceHeaderTxt.setLayoutX(55.0);
		balanceHeaderTxt.setLayoutY(51.0);
		balanceHeaderTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text getYourAccTxt = new Text("Get your account balance instantly with a single tap.");
		getYourAccTxt.setFill(Color.web("#B3B3B3"));
		getYourAccTxt.setLayoutX(55.0);
		getYourAccTxt.setLayoutY(75.0);
		getYourAccTxt.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(balanceHeaderTxt, getYourAccTxt);

		// Create the Background with the ImageView
		ImageView transferIconImageView = new ImageView();
		transferIconImageView.setFitWidth(140.0);
		transferIconImageView.setLayoutX(175.0);
		transferIconImageView.setLayoutY(100.0);
		transferIconImageView.setPickOnBounds(true);
		transferIconImageView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image withdrawIconImage = new Image(getClass().getResource("/img/balance-icon-grey-lg.png").toExternalForm());
		transferIconImageView.setImage(withdrawIconImage);

		// Add the ImageView to the AnchorPane
		whiteMiddlePane.getChildren().add(transferIconImageView);

		Pane greyBalanceStrip = new Pane();
		greyBalanceStrip.setLayoutX(0);
		greyBalanceStrip.setLayoutY(240.0);
		greyBalanceStrip.setPrefHeight(55.0);
		greyBalanceStrip.setPrefWidth(500.0);
		greyBalanceStrip.setStyle("-fx-background-color: #E6E6E6;");

		loggedUser = LoggedUser.getInstance();

		// for testing
//		BankAccount ba = new BankAccount();
//		ba.setBankAccBalance(4957.60);
//		loggedUser.setBankAccount(ba);

		double balance = loggedUser.getBankAccount().getBankAccBalance();
		String balanceToString = String.format("%.2f", balance);

		Text balanceTxt = new Text("€" + balanceToString);
		balanceTxt.setFill(Color.web("#666666"));
		balanceTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		double balanceTxtCenterX = (greyBalanceStrip.getPrefWidth() - balanceTxt.getLayoutBounds().getWidth()) / 2;
		double balanceTxtCenterY = (greyBalanceStrip.getPrefHeight() - balanceTxt.getLayoutBounds().getHeight()) / 2;

		balanceTxt.setTranslateX(balanceTxtCenterX);
		balanceTxt.setTranslateY(balanceTxtCenterY + 30);

		greyBalanceStrip.getChildren().add(balanceTxt);

		whiteMiddlePane.getChildren().add(greyBalanceStrip);

//00000000000000000000000000000000000000000000000000000000000000000

		VBox transactionsList = new VBox();
		transactionsList.setPadding(new Insets(10, 10, 10, 10)); // Padding for texts
		transactionsList.setSpacing(5); // Space between each transaction
		
		// TransactionView that will appear in the BalanceScene & dialog
		ArrayList<TransactionView> transactions = new ArrayList<TransactionView>();
		transactions = sortTransactionTypeSenderMessage(transactions);

		int numTransactions = transactions.size();
		int startIndex = Math.max(numTransactions - 3, 0); // Ensure startIndex is non-negative
		
		// BalanceScene structure, show the last 3 transactions
		for (int i = startIndex; i < numTransactions; i++) {

			// GridPane that will contain only last 3 transactions
			GridPane transactionGrid = new GridPane();
			transactionGrid.setHgap(10);

			Text emailText = new Text(transactions.get(i).getEmail());
			emailText.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			emailText.setWrappingWidth(150);
			emailText.setTextAlignment(TextAlignment.LEFT);

			Text amountText = new Text(String.format("%,.2f", transactions.get(i).getAmount()));
			amountText.setFont(Font.font("Roboto", FontWeight.BOLD, 16.0));
			amountText.setWrappingWidth(100);
			amountText.setTextAlignment(TextAlignment.RIGHT);
			
			if (transactions.get(i).getAmount() < 0) {
				amountText.setFill(Color.RED);
			} else {
				amountText.setFill(Color.GREEN);
			}

			Text transactionDate = new Text(transactions.get(i).getDate());
			transactionDate.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			transactionDate.setWrappingWidth(100);
			transactionDate.setTextAlignment(TextAlignment.RIGHT);
			
			transactionGrid.add(emailText, 0 , 0);
			transactionGrid.add(amountText, 1 , 0);
			transactionGrid.add(transactionDate, 2 , 0);
			

			// Add the transaction to the transactions list
			transactionsList.getChildren().add(transactionGrid);
		}

		transactionsList.setLayoutX(55);
		transactionsList.setLayoutY(315.0);

		whiteMiddlePane.getChildren().add(transactionsList);

//00000000000000000000000000000000000000000000000000000000000000000

		Button backBtn = new Button("Back");
		backBtn.setLayoutX(80.0);
		backBtn.setLayoutY(410.0);
		backBtn.setPrefHeight(30.0);
		backBtn.setPrefWidth(140.0);
		backBtn.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 8px; -fx-cursor: hand;");
		backBtn.setTextAlignment(TextAlignment.CENTER);
		backBtn.setTextFill(Color.web("#ffffff"));
		backBtn.setFont(Font.font("Roboto Bold", 16.0));
		backBtn.setOnAction(e -> {
			try {
				transactionSceneSingletonInstance = AccountOverviewScene.getInstance();
				transactionSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button moreTransactionsBtn = new Button("More Transactions");
		moreTransactionsBtn.setLayoutX(245.0);
		moreTransactionsBtn.setLayoutY(backBtn.getLayoutY());
		moreTransactionsBtn.setPrefHeight(30.0);
		moreTransactionsBtn.setPrefWidth(170.0);
		moreTransactionsBtn.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
		moreTransactionsBtn.setTextAlignment(TextAlignment.CENTER);
		moreTransactionsBtn.setTextFill(Color.web("#ffffff"));
		moreTransactionsBtn.setFont(Font.font("Roboto Bold", 16.0));
		moreTransactionsBtn.setOnAction(e -> {
			try {
				// make dialog show when clicked this button
				showStatementDialog();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(backBtn, moreTransactionsBtn);

		anchorPane.getChildren().add(whiteMiddlePane);

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public ArrayList<TransactionView> sortTransactionTypeSenderMessage(ArrayList<TransactionView> transactions) {

		// get data from db to pass to TransactionView
		dbController = DatabaseController.getInstance();
		ArrayList<Transaction> dbTransactions = dbController.getStatementTransactionList(loggedUser.getStatement());

		// setting up the "sender" message, date and date to dialog
		for (int i = 0; i < dbTransactions.size(); i++) {

			double doubleAmount = dbTransactions.get(i).getTransactionAmount();
			String date = TimeStampToYear.timeStampToLocalDateString(dbTransactions.get(i).getTransactionDate());
			String currentSender = "";

			// get transactionType (deposit, withdraw or transfer)
			String transactionType = dbTransactions.get(i).getTransactionType();

			// sender/receiver msg say "you deposited" or "you withdrew"
			switch (transactionType) {
			case "deposit":
				// make this sender's msg be "You deposited"
				currentSender = "You deposited";
				break;
			case "withdraw":
				// make this sender's msg be "You withdrew"
				currentSender = "You withdrew";
				break;

			// get data through db transfer.to_bank_acc_id = loggedUserId & time
			case "transfer":

				// get specific transfer to get sender's email (using id & date)
				int userId = loggedUser.getUser().getId();
				java.sql.Timestamp timeStampDate = dbTransactions.get(i).getTransactionDate();

				// get 'from_bank_acc_id' out of this to discover sender's email
				// if You transferred money to other account = negative transaction
				if (dbTransactions.get(i).getTransactionAmount() < 0) {

					// get data from receiver since I know I sent transfer.from_bank_acc_id = userId
					// & time(get to)
					Transfer transfer = dbController.getSpecificReceiverTransfer(userId, timeStampDate);

					int receiverID = transfer.getToBankAcc();
					User receiverUser = dbController.getUserById(receiverID);
					String receiverEmail = receiverUser.getEmail();

					currentSender = "You transferred to " + receiverEmail + ".";// get recipient's email

				} else {
					// get data from sender since I know I received transfer.to_bank_acc_id = userId
					// & time(get from)
					Transfer transfer = dbController.getSpecificSenderTransfer(userId, timeStampDate);

					int senderID = transfer.getFromBankAcc();
					User senderUser = dbController.getUserById(senderID);
					String senderEmail = senderUser.getEmail();

					currentSender = "Transfer received from" + senderEmail + ".";

					// test
//							dbTransactions.get(i).getBankAccID() == loggedUser.getBankAccount().getBankAccID()
				}
				break;
			}

			TransactionView t = new TransactionView(currentSender, date, doubleAmount);
//					TransactionView t = new TransactionView(sender[i], amounts[i], dates[i]);
			transactions.add(t);
		}

		return transactions;
	}

	/**
	 * Loosely coupled method to show transactions dialog.
	 * 
	 * This method is being called from BalanceScene "More transactions" button and
	 * AccountOverview "Statement" button
	 */
	public void showStatementDialog() {
		// Create a new Dialog
		Dialog<Void> dialog = new Dialog<>();
		dialog.setTitle("All Transactions");

		// Create a VBox to hold all transactions
		VBox allTransactionsList = new VBox();
		allTransactionsList.setPadding(new Insets(10, 10, 10, 10)); // Padding for texts
		allTransactionsList.setSpacing(5); // Space between each transaction

		// sort trasactionType sender messages
		ArrayList<TransactionView> transactions = new ArrayList<TransactionView>();
		transactions = sortTransactionTypeSenderMessage(transactions);

		// Add all transactions to the VBox
		for (int i = 0; i < transactions.size(); i++) {
			GridPane transactionGrid = new GridPane();
			transactionGrid.setHgap(10);

			Text emailText = new Text(transactions.get(i).getEmail());
			emailText.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			emailText.setWrappingWidth(250);

			Text amountText = new Text(String.format("%,.2f", transactions.get(i).getAmount()));
			amountText.setFont(Font.font("Roboto", FontWeight.BOLD, 16.0));
			amountText.setWrappingWidth(100);
//			amountText.setTextAlignment(TextAlignment.RIGHT); // Align amount to the right

			if (transactions.get(i).getAmount() < 0) {
				amountText.setFill(Color.RED);
			} else {
				amountText.setFill(Color.GREEN);
			}

			Text dateText = new Text(transactions.get(i).getDate());
			dateText.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			dateText.setWrappingWidth(100);
			dateText.setTextAlignment(TextAlignment.RIGHT);

			transactionGrid.add(emailText, 0, 0);
			transactionGrid.add(amountText, 1, 0);
			transactionGrid.add(dateText, 2, 0);

			// Add the transaction to the transactions list
			allTransactionsList.getChildren().add(transactionGrid);
		}

		// Create a ScrollPane and add the VBox to it
		ScrollPane scrollPane = new ScrollPane(allTransactionsList);
		dialog.getDialogPane().setPrefHeight(300);
		dialog.getDialogPane().setPrefWidth(530.0);
		dialog.setResizable(true);

		// Add the ScrollPane to the Dialog
		dialog.getDialogPane().setContent(scrollPane);

		// Add a button to close the dialog
		ButtonType closeButtonType = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(closeButtonType);

		Platform.runLater(() -> {
			Button closeButton = (Button) dialog.getDialogPane().lookupButton(closeButtonType);
			closeButton.setPrefHeight(30.0);
			closeButton.setPrefWidth(170.0);
			closeButton.setStyle("-fx-background-color: #F28E1F; -fx-background-radius: 8px; -fx-cursor: hand;");
			closeButton.setTextAlignment(TextAlignment.CENTER);
			closeButton.setTextFill(Color.web("#ffffff"));
			closeButton.setFont(Font.font("Roboto Bold", 16.0));

			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.CENTER); // Center alignment

			buttonBox.getChildren().add(closeButton);
			VBox dialogVBox = new VBox(scrollPane, buttonBox);
			VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));

			dialog.getDialogPane().setContent(dialogVBox);
		});

		// Show the dialog
		dialog.showAndWait();
	}
}
