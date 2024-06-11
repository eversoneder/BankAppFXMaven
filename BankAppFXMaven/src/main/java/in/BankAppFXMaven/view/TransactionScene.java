package in.BankAppFXMaven.view;

import java.util.Optional;

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

public class TransactionScene extends Application {

	private Stage primaryStage;

	private static SignInScene signInSceneSingletonInstance = SignInScene.getInstance();
	private static WithdrawScene withdrawSceneSingletonInstance = WithdrawScene.getInstance();
	private static DepositScene depositSceneSingletonInstance = DepositScene.getInstance();
	private static TransferScene transferSceneSingletonInstance = TransferScene.getInstance();
	private static BalanceScene balanceSceneSingletonInstance = BalanceScene.getInstance();
	private static AccInfoScene accInfoSceneSingletonInstance = AccInfoScene.getInstance();

	private static TransactionScene transactionsSceneSingletonInstance;

	public TransactionScene() {
	}

	public static TransactionScene getInstance() {
		if (transactionsSceneSingletonInstance == null) {
			transactionsSceneSingletonInstance = new TransactionScene();
		}
		return transactionsSceneSingletonInstance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;

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
		Image backgroundImage = new Image(getClass().getResource("../img/background.png").toExternalForm());
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
		Image logoImage = new Image(getClass().getResourceAsStream("../img/EconoBank-Logo.png"));
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
		Text transactionsTxt = new Text("Transactions");
		transactionsTxt.setFill(Color.web("#f49820"));
		transactionsTxt.setLayoutX(55.0);
		transactionsTxt.setLayoutY(51.0);
		transactionsTxt.setFont(Font.font("Roboto", FontWeight.BOLD, 34.0));

		Text whatWouldYouLikeToDoTodayTxt = new Text("Hello Everson, what would you like to do today?");
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

		Image withdrawImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/withdraw.png"));// this imgs
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

		Image depositImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/deposit.png"));// this imgs lives
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

		Image transferImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/transfer.png"));// this imgs
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

		Image balanceImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/balance.png"));// this imgs lives
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

		Image statementImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/statement.png"));// this imgs
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
				BalanceScene.getAllTransactions();
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

		Image logoutImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/logout.png"));// this imgs lives
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
			    	signInSceneSingletonInstance.start(primaryStage);
			    } else {
			        // handle the user choosing CANCEL or closing the dialog
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

		Image accInfoImage = new Image(getClass().getResourceAsStream("../img/btnsPNG/accinfo.png"));// this imgs lives
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

}
