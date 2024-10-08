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
import javafx.scene.layout.Border;
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
	private static LoggedUser loggedUser;
	private ArrayList<TransactionView> transactionViewList;

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
		transferIconImageView.setLayoutY(94.0);
		transferIconImageView.setPickOnBounds(true);
		transferIconImageView.setPreserveRatio(true);

		// Set the image for the ImageView
		Image withdrawIconImage = new Image(getClass().getResource("/img/balance-icon-grey-lg.png").toExternalForm());
		transferIconImageView.setImage(withdrawIconImage);

		// Add the ImageView to the AnchorPane
		whiteMiddlePane.getChildren().add(transferIconImageView);

		Pane greyBalanceStrip = new Pane();
		greyBalanceStrip.setLayoutX(0);
		greyBalanceStrip.setLayoutY(223.0);
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
		
		dbController = DatabaseController.getInstance();
		boolean transaction = false;
		try {
		transaction = dbController.getTransaction(loggedUser.getBankAccount().getBankAccID());
		}catch(NullPointerException e) {
//			e.printStackTrace();
			System.out.println("line 225, balancescene");
		}
		if(transaction) {
			transactionViewList = getTransactionViewList();
			
			int numTransactions = transactionViewList.size();
			int startIndex = Math.max(numTransactions - 3, 0); // Ensure startIndex is non-negative

			// BalanceScene structure, show the last 3 transactions
			for (int i = startIndex; i < numTransactions; i++) {
			    // HBox that will contain only last 3 transactions
			    HBox transactionBox = new HBox();
			    transactionBox.setSpacing(10);
			    transactionBox.setPrefSize(400, 35); // Set preferred size for the HBox
			    transactionBox.setAlignment(Pos.CENTER); // Align contents to the center vertically
//			    transactionBox.setStyle("-fx-border-color: red; -fx-border-width: 1; -fx-border-style: solid;");

			    Text emailText = new Text(transactionViewList.get(i).getEmail());
			    emailText.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			    emailText.setWrappingWidth(230);
			    emailText.setTextAlignment(TextAlignment.LEFT);
			    emailText.setFill(Color.web("#777777"));

			    Text amountText = new Text(String.format("%,.2f", transactionViewList.get(i).getAmount()));
			    amountText.setFont(Font.font("Roboto", FontWeight.BOLD, 16.0));
			    amountText.setWrappingWidth(80);
			    amountText.setTextAlignment(TextAlignment.RIGHT);

			    if (transactionViewList.get(i).getAmount() < 0) {
			        amountText.setFill(Color.RED);
			    } else {
			        amountText.setFill(Color.GREEN);
			    }

			    Text transactionDate = new Text(transactionViewList.get(i).getDate());
			    transactionDate.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			    transactionDate.setWrappingWidth(90);
			    transactionDate.setTextAlignment(TextAlignment.RIGHT);
			    transactionDate.setFill(Color.web("#777777"));

			    //for testing
//			    emailText.setText("You transferred to everson_spinola@hotmail.com.");
//			    emailText.setText("You deposited");
//			    amountText.setText("1990.95");
//			    transactionDate.setText("05/08/2024");

			    // Wrap each Text node in a VBox to center-align them vertically
			    VBox emailBox = new VBox(emailText);
			    emailBox.setAlignment(Pos.CENTER_LEFT);
			    //for testing
//			    emailBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
			    
			    VBox amountBox = new VBox(amountText);
			    amountBox.setAlignment(Pos.CENTER);
//			    amountBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
			    
			    VBox dateBox = new VBox(transactionDate);
			    dateBox.setAlignment(Pos.CENTER_RIGHT);
//			    dateBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");

			    transactionBox.getChildren().addAll(emailBox, amountBox, dateBox);

			    // Add the transaction to the transactions list
			    transactionsList.getChildren().add(transactionBox);
			}

			transactionsList.setLayoutX(30);
			transactionsList.setLayoutY(277.0);

			whiteMiddlePane.getChildren().add(transactionsList);
		}

//00000000000000000000000000000000000000000000000000000000000000000

		Button backBtn = new Button("Back");
		backBtn.setLayoutX(80.0);
		backBtn.setLayoutY(425.0);
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
				
				if(isTransactionListExistent()) {
					showStatementDialog();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("No records.");
					alert.setHeaderText(null);
					alert.setContentText("You haven't done any transactions yet.");
					alert.showAndWait();
				}

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
	
	public boolean isTransactionListExistent() {
		try {
			boolean transaction = false;
			try {
				// exist transactions? if true, call statement.getTransactionList()
				transaction = dbController
						.getTransaction(loggedUser.getBankAccount().getBankAccID());
			} catch (NullPointerException e5) {
//			e5.printStackTrace();
				System.out.println("line 388, signinscene");
			}
			System.out.println(transaction);
			if (transaction) {
				return true;
			}

		} catch (NullPointerException e3) {
//			e3.printStackTrace();
			System.out.println("No transactions yet.");
		}
		return false;
	}

	/**
	 * Creates and returns an TransactionView ArrayList. Each TransactionView in it
	 * consists of a sender message(email), amount and date. eg of a sorted
	 * TransactionView. "everson_spinola@hotmail.com transferred to you €50,00
	 * 04/08/2024 22:41:25"
	 * 
	 * @return TransactionView with ready transaction list data to display on
	 *         front-end GUI
	 */
	public ArrayList<TransactionView> getAndSortTransactionListDataToGUI() {

		loggedUser = LoggedUser.getInstance();
		dbController = DatabaseController.getInstance();

		// get data from db to pass to TransactionView
		ArrayList<Transaction> dbTransactions = loggedUser.getStatement().getTransactionList();
		ArrayList<TransactionView> transactionViewList = new ArrayList<TransactionView>();

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

				// get transaction_id from transfer record to query transfer table & get
				// 'from_acc_id'
				// or 'to_acc_id'
				int transactionId = dbTransactions.get(i).getTransactionID();
				Transfer transfer = dbController.getTransferByTransactionId(transactionId);

				// if negative, set sender = "You transferred..."
				if (dbTransactions.get(i).getTransactionAmount() < 0) {

					// get receiver from transfer.transaction_id
					int toBankAcc = transfer.getToBankAcc();
					BankAccount receiverBankAcc = dbController.getBankAccByUserID(toBankAcc);
					int receiverUserId = receiverBankAcc.getUserID();
					User receiverUser = dbController.getUserById(receiverUserId);
					String receiverEmail = receiverUser.getEmail();

					currentSender = "You transferred to " + receiverEmail + ".";// get recipient's email

					// if positive, set sender = "You received..."
				} else {

					// get receiver from transfer.transaction_id
					int toBankAcc = transfer.getFromBankAcc();
					BankAccount senderBankAcc = dbController.getBankAccByUserID(toBankAcc);
					int senderUserId = senderBankAcc.getUserID();
					User senderUser = dbController.getUserById(senderUserId);
					String senderEmail = senderUser.getEmail();

					currentSender = "Transfer received from" + senderEmail + ".";
				}
				break;
			}

			TransactionView transactionView = new TransactionView(currentSender, date, doubleAmount);
//					TransactionView t = new TransactionView(sender[i], amounts[i], dates[i]);
			transactionViewList.add(transactionView);
		}

		return transactionViewList;
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
		transactionViewList = getTransactionViewList();

		// Add all transactions to the VBox
		for (int i = 0; i < transactionViewList.size(); i++) {
			GridPane transactionGrid = new GridPane();
			transactionGrid.setHgap(10);

			Text emailText = new Text(transactionViewList.get(i).getEmail());
			emailText.setFont(Font.font("Roboto", FontWeight.NORMAL, 16.0));
			emailText.setWrappingWidth(250);

			Text amountText = new Text(String.format("%,.2f", transactionViewList.get(i).getAmount()));
			amountText.setFont(Font.font("Roboto", FontWeight.BOLD, 16.0));
			amountText.setWrappingWidth(100);
//			amountText.setTextAlignment(TextAlignment.RIGHT); // Align amount to the right

			if (transactionViewList.get(i).getAmount() < 0) {
				amountText.setFill(Color.RED);
			} else {
				amountText.setFill(Color.GREEN);
			}

			Text dateText = new Text(transactionViewList.get(i).getDate());
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

	/**
	 * @return an existing TransactionView ArrayList instance or create new and
	 *         return it
	 */
	private ArrayList<TransactionView> getTransactionViewList() {
		return transactionViewList = getAndSortTransactionListDataToGUI();
	}
}
