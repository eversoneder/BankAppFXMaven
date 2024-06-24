package in.BankAppFXMaven;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.utility.AccountNumberGenerator;
import in.BankAppFXMaven.utility.HashingUtility;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;
import in.BankAppFXMaven.view.SignInScene;
import in.BankAppFXMaven.view.SignUpScene;

public class Main extends Application {
	
	private static SignUpScene singUn = SignUpScene.getInstance();
//	private static DatabaseController db = DatabaseController.getInstance();
	@Override
	public void start(Stage primaryStage) {
		try {
			singUn.start(primaryStage);
			
//			String hashedPassword = HashingUtility.hashPassword("pass1234!");
//			System.out.println(hashedPassword);
			
//			
//			Alert alert = new Alert(Alert.AlertType.INFORMATION);
//			alert.setTitle("Account created.");
//			alert.setHeaderText(null);
//			alert.setContentText("Welcome! Your account has been created! You can now Sign-in.");
//			alert.showAndWait();
			
//			int i = 9;
//			boolean test = db.emailExists("everson_spinola@hotmail.com");
//			System.out.println(test);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}