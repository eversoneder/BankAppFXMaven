package in.BankAppFXMaven.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainScene extends Application {

	private Stage primaryStage;
	private static MainScene mainSceneSingletonInstance;
	private static SignUpScene signUpSceneSingletonInstance = SignUpScene.getInstance();
	private static SignInScene signInSceneSingletonInstance = SignInScene.getInstance();

	private MainScene() {
	}

	public static MainScene getInstance() {
		if (mainSceneSingletonInstance == null) {
			mainSceneSingletonInstance = new MainScene();
		}
		return mainSceneSingletonInstance;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		mainSceneViewBuilder();
	}

	public void mainSceneViewBuilder() {

//ANCHOR PANE ITEMS----------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
		anchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
		anchorPane.setPrefHeight(720.0);
		anchorPane.setPrefWidth(500.0);

//ImageView for the Background img
		ImageView backgroundImageView = new ImageView();
		backgroundImageView.setFitHeight(720.0);
		backgroundImageView.setFitWidth(500.0);
		backgroundImageView.setPickOnBounds(true);

//Set the bg image in the ImageView
		Image backgroundImage = new Image(getClass().getResource("/img/background.png").toExternalForm());
		backgroundImageView.setImage(backgroundImage);

//Add the ImageView to the AnchorPane
		anchorPane.getChildren().add(backgroundImageView);

// Create the Text nodes
		Text text4 = new Text("Welcome to");
		text4.setFill(Color.WHITE);
		text4.setLayoutX(30.0);
		text4.setLayoutY(74.0);
		text4.setFont(Font.font("Roboto Black", 34.0));

		Text text5 = new Text("Don't have an account yet?!");
		text5.setFill(Color.WHITE);
		text5.setLayoutX(138.0);
		text5.setLayoutY(587.0);
		text5.setFont(Font.font("Roboto Regular", 18.0));

// Create the ImageView
		ImageView logoView = new ImageView();
		logoView.setFitHeight(141.0);
		logoView.setFitWidth(440.0);
		logoView.setLayoutX(30.0);
		logoView.setLayoutY(104.0);
		logoView.setPickOnBounds(true);
		logoView.setPreserveRatio(true);

// Set the image for the ImageView
		Image logoImage = new Image(getClass().getResourceAsStream("/img/EconoBank-Logo.png"));
		logoView.setImage(logoImage);

// Create the Button
		Button signUpBtn = new Button("Sign up!");
		signUpBtn.setLayoutX(89.0);
		signUpBtn.setLayoutY(603.0);
		signUpBtn.setPrefHeight(65.0);
		signUpBtn.setPrefWidth(320.0);
		signUpBtn.setStyle("-fx-background-color: white; -fx-background-radius: 10px; -fx-cursor: hand;");
		signUpBtn.setTextAlignment(TextAlignment.CENTER);
		signUpBtn.setTextFill(Color.web("#ff8820"));
		signUpBtn.setFont(Font.font("Roboto Bold", 24.0));
		// action to start the SignUpScene from MainScene through signUpBtn
		signUpBtn.setOnAction(e -> {
			try {
				signUpSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

// add texts, logo and sign up button to the anchor pane
		anchorPane.getChildren().add(text4);
		anchorPane.getChildren().add(text5);
		anchorPane.getChildren().add(logoView);
		anchorPane.getChildren().add(signUpBtn);

//---------------------------------------------------------------------------------------------------
//end of ANCHORPANE ITEMS----------------------------------------------------------------------------

//WHITE PANE ITEMS==================================================================================
//===================================================================================================
//White round edges Pane
		Pane whiteMiddlePane = new Pane();
		whiteMiddlePane.setLayoutX(12.0);
		whiteMiddlePane.setLayoutY(266.0);
		whiteMiddlePane.setPrefHeight(280.0);
		whiteMiddlePane.setPrefWidth(475.0);
		whiteMiddlePane.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");

//Text nodes from within white pane
		Text text1 = new Text("Manage your expenses");
		text1.setFill(Color.web("#f49820"));
		text1.setLayoutX(26.0);
		text1.setLayoutY(51.0);
		text1.setFont(Font.font("Roboto Regular", 22.0));

		Text text2 = new Text("Seamlessly & intuitively");
		text2.setFill(Color.web("#f49820"));
		text2.setLayoutX(26.0);
		text2.setLayoutY(95.0);
		text2.setFont(Font.font("Roboto Bold", 34.0));

		Text text3 = new Text("Already have an account?");
		text3.setFill(Color.web("#686868"));
		text3.setLayoutX(134.0);
		text3.setLayoutY(157.0);
		text3.setFont(Font.font("Roboto Regular", 18.0));

//Sign in Button
		Button signInBtn = new Button("Sign in!");
		signInBtn.setLayoutX(78.0);
		signInBtn.setLayoutY(177.0);
		signInBtn.setPrefHeight(65.0);
		signInBtn.setPrefWidth(320.0);
		signInBtn.setStyle(
				"-fx-background-radius: 10px; -fx-background-color: linear-gradient(to right, #FFB129, #EE751D); -fx-cursor: hand;");
		signInBtn.setTextAlignment(TextAlignment.CENTER);
		signInBtn.setTextFill(Color.WHITE);
		signInBtn.setFont(Font.font("Roboto Bold", 24.0));
		signInBtn.setOnAction(e -> {
			try {
				signInSceneSingletonInstance.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

//Add the Text nodes and Button to the White Pane
		whiteMiddlePane.getChildren().addAll(text1, text2, text3, signInBtn);
		anchorPane.getChildren().add(whiteMiddlePane);
//====================================================================================================
//END OF "WHITE PANE ITEMS============================================================================

//ALL was put into AnchorPane (ALL -> AnchorPane -> Scene -> primaryStage to finally .show()
		Scene scene = new Scene(anchorPane);
		primaryStage.setTitle("EconoBank");
		primaryStage.setScene(scene);

//Logo icon for taskbar icon area
		Image logoIcon = new Image(getClass().getResourceAsStream("/img/EconoBank-Icon-Circle.png"));
		primaryStage.getIcons().add(logoIcon);

		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
