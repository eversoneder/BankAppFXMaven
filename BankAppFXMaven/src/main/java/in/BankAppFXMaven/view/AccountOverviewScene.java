package in.BankAppFXMaven.view;

import java.util.Optional;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.LoggedUser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountOverviewScene extends Application {

	private Stage primaryStage;

	private static SignInScene signInSceneSingletonInstance;
	private static WithdrawScene withdrawSceneSingletonInstance;
	private static DepositScene depositSceneSingletonInstance;
	private static TransferScene transferSceneSingletonInstance;
	private static BalanceScene balanceSceneSingletonInstance;
	private static AccInfoScene accInfoSceneSingletonInstance;
	private static DatabaseController dbController;
	private LoggedUser loggedUser;

	private static AccountOverviewScene accOverviewSceneSingletonInstance;

	public AccountOverviewScene() {
	}

	public static AccountOverviewScene getInstance() {
		if (accOverviewSceneSingletonInstance == null) {
			accOverviewSceneSingletonInstance = new AccountOverviewScene();
		}
		return accOverviewSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

		// check the last_login

//		if(login.getLastLogin() == null) {
//			saveUserNameAndSurname();
//		}

		loggedUser = LoggedUser.getInstance();
		dbController = DatabaseController.getInstance();
		
		if (loggedUser.getLogin().getLastLogin() == null ) {

			// insert new user's last_login in db with now TimeStamp and also in the
			// loggedUser
			java.sql.Timestamp timeStamp = dbController.insertNewLastLogin(loggedUser.getUser().getId());
			loggedUser.getLogin().setLastLogin(timeStamp);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Important Information.");
			alert.setHeaderText(null);
			alert.setContentText("Please make sure to securely note down your randomly generated bank account number: '"
					+ loggedUser.getBankAccount().getBankAccNum()
					+ "'. In case you forget your password, this account number will be required for password reset. Remember not to share your bank account number with anyone, as it is not necessary for fund transfers within the app. Your unique email address serves as the means to transfer funds between accounts.");
			alert.showAndWait();

		} else if(loggedUser.getUser().getName() == null || loggedUser.getUser().getSurname() == null) {
			dbController.showNameSurnameDialogAndSave();
		}

		transactionSceneViewBuilder();
	}

	private void transactionSceneViewBuilder() {
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
		Text transactionsTxt = new Text("Account Overview");
		transactionsTxt.setFill(Color.web("#f49820"));
		transactionsTxt.setLayoutX(55.0);
		transactionsTxt.setLayoutY(51.0);
		transactionsTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text whatWouldYouLikeToDoTodayTxt = new Text("Hello " + loggedUser.getUser().getName() + ", what would you like to do today?");
		whatWouldYouLikeToDoTodayTxt.setFill(Color.web("#B3B3B3"));
		whatWouldYouLikeToDoTodayTxt.setLayoutX(55.0);
		whatWouldYouLikeToDoTodayTxt.setLayoutY(75.0);
		whatWouldYouLikeToDoTodayTxt.setFont(Font.font("Roboto Regular", 16.0));

		whiteMiddlePane.getChildren().addAll(transactionsTxt, whatWouldYouLikeToDoTodayTxt);

		// -----------------------------------------------------------------------------
		// Withdraw button
		ImageView withdrawBtnView = new ImageView();
		withdrawBtnView.setFitHeight(100.0);
		withdrawBtnView.setFitWidth(100.0);
		withdrawBtnView.setPickOnBounds(true);
		withdrawBtnView.setPreserveRatio(true);

		Image withdrawImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/withdraw.png"));// this imgs
																										// lives on BIN
																										// folder!
		withdrawBtnView.setImage(withdrawImage);

		Button withdrawButton = new Button();
		withdrawButton.setLayoutX(40.0);
		withdrawButton.setLayoutY(100.0);
		withdrawButton.setGraphic(withdrawBtnView);
		withdrawButton.setStyle("-fx-cursor: hand; -fx-background-color: #F28E1F; -fx-background-radius: 10;");

		withdrawButton.setOnAction(e -> {
			try {
				withdrawSceneSingletonInstance = WithdrawScene.getInstance();
				withdrawSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(withdrawButton);

		// -----------------------------------------------------------------------------
		// Deposit button
		ImageView depositBtnView = new ImageView();
		depositBtnView.setFitHeight(100.0);
		depositBtnView.setFitWidth(100.0);
		depositBtnView.setPickOnBounds(true);
		depositBtnView.setPreserveRatio(true);

		Image depositImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/deposit.png"));// this imgs lives
																									// on BIN
																									// folder!
		depositBtnView.setImage(depositImage);

		Button depositButton = new Button();
		depositButton.setLayoutX(190.0);
		depositButton.setLayoutY(100.0);
		depositButton.setGraphic(depositBtnView);
		depositButton.setStyle("-fx-cursor: hand; -fx-background-color: #F28E1F; -fx-background-radius: 10;");

		depositButton.setOnAction(e -> {
			try {
				depositSceneSingletonInstance = DepositScene.getInstance();
				depositSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(depositButton);

		// -----------------------------------------------------------------------------
		// Transfer button
		ImageView transferBtnView = new ImageView();
		transferBtnView.setFitHeight(100.0);
		transferBtnView.setFitWidth(100.0);
		transferBtnView.setPickOnBounds(true);
		transferBtnView.setPreserveRatio(true);

		Image transferImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/transfer.png"));// this imgs
																										// lives
																										// on BIN
																										// folder!
		transferBtnView.setImage(transferImage);

		Button transferButton = new Button();
		transferButton.setLayoutX(340.0);
		transferButton.setLayoutY(100.0);
		transferButton.setGraphic(transferBtnView);
		transferButton.setStyle("-fx-cursor: hand; -fx-background-color: #F28E1F; -fx-background-radius: 10;");

		transferButton.setOnAction(e -> {
			try {
				transferSceneSingletonInstance = TransferScene.getInstance();
				transferSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(transferButton);

		// -----------------------------------------------------------------------------
		// Balance button
		ImageView balanceBtnView = new ImageView();
		balanceBtnView.setFitHeight(100.0);
		balanceBtnView.setFitWidth(100.0);
		balanceBtnView.setPickOnBounds(true);
		balanceBtnView.setPreserveRatio(true);

		Image balanceImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/balance.png"));// this imgs lives
																									// on BIN
																									// folder!
		balanceBtnView.setImage(balanceImage);

		Button balanceButton = new Button();
		balanceButton.setLayoutX(40.0);
		balanceButton.setLayoutY(240.0);
		balanceButton.setGraphic(balanceBtnView);
		balanceButton.setStyle("-fx-cursor: hand; -fx-background-color: #F28E1F; -fx-background-radius: 10;");

		balanceButton.setOnAction(e -> {
			try {
				balanceSceneSingletonInstance = BalanceScene.getInstance();
				balanceSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(balanceButton);

		// -----------------------------------------------------------------------------
		// Statement button
		ImageView statementBtnView = new ImageView();
		statementBtnView.setFitHeight(100.0);
		statementBtnView.setFitWidth(100.0);
		statementBtnView.setPickOnBounds(true);
		statementBtnView.setPreserveRatio(true);

		Image statementImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/statement.png"));// this imgs
																										// lives
																										// on BIN
																										// folder!
		statementBtnView.setImage(statementImage);

		Button statementButton = new Button();
		statementButton.setLayoutX(190.0);
		statementButton.setLayoutY(240.0);
		statementButton.setGraphic(statementBtnView);
		statementButton.setStyle("-fx-cursor: hand; -fx-background-color: #F28E1F; -fx-background-radius: 10;");

		statementButton.setOnAction(e -> {
			try {
				loggedUser = LoggedUser.getInstance();
				balanceSceneSingletonInstance = BalanceScene.getInstance();
				
				if(BalanceScene.getInstance().isTransactionListExistent()) {
					balanceSceneSingletonInstance.showStatementDialog();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("No records.");
					alert.setHeaderText(null);
					alert.setContentText("You haven't done any transactions yet.");
					alert.showAndWait();
				}
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(statementButton);

		// -----------------------------------------------------------------------------
		// logout button
		ImageView logoutBtnView = new ImageView();
		logoutBtnView.setFitHeight(100.0);
		logoutBtnView.setFitWidth(100.0);
		logoutBtnView.setPickOnBounds(true);
		logoutBtnView.setPreserveRatio(true);

		Image logoutImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/logout.png"));// this imgs lives
																									// on BIN
																									// folder!
		logoutBtnView.setImage(logoutImage);

		Button logoutButton = new Button();
		logoutButton.setLayoutX(340.0);
		logoutButton.setLayoutY(240.0);
		logoutButton.setGraphic(logoutBtnView);
		logoutButton.setStyle("-fx-cursor: hand; -fx-background-color: #F2691F; -fx-background-radius: 10;");

		logoutButton.setOnAction(e -> {
			try {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Logging out");
				alert.setHeaderText(null);
				alert.setContentText("You are about to Log out, are you sure?");
				alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == ButtonType.YES) {
					// handle the user choosing YES
					
					loggedUser.clearUserInfo();
					signInSceneSingletonInstance = SignInScene.getInstance();
					signInSceneSingletonInstance.start(primaryStage);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(logoutButton);

		// -----------------------------------------------------------------------------
		// Account Information button
		ImageView accInfoBtnView = new ImageView();
		accInfoBtnView.setFitHeight(40.0);
//				accInfoBtnView.setFitWidth(100.0);
		accInfoBtnView.setPickOnBounds(true);
		accInfoBtnView.setPreserveRatio(true);

		Image accInfoImage = new Image(getClass().getResourceAsStream("/img/btnsPNG/accinfo.png"));// this imgs lives
																									// on BIN
																									// folder!
		accInfoBtnView.setImage(accInfoImage);

		Button accInfoButton = new Button();
		accInfoButton.setLayoutX(130.0);
		accInfoButton.setLayoutY(380.0);
		accInfoButton.setGraphic(accInfoBtnView);
		accInfoButton.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");

		accInfoButton.setOnAction(e -> {
			try {
				accInfoSceneSingletonInstance = AccInfoScene.getInstance();
				accInfoSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		whiteMiddlePane.getChildren().addAll(accInfoButton);

		anchorPane.getChildren().add(whiteMiddlePane);

		Scene scene = new Scene(anchorPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * @return the loggedUser
	 */
	public LoggedUser getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param loggedUser the loggedUser to set
	 */
	public void setLoggedUser(LoggedUser loggedUser) {
		this.loggedUser = loggedUser;
	}
}
