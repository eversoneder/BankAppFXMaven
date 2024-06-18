package in.BankAppFXMaven;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.utility.AccountNumberGenerator;
import javafx.application.Application;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;
import in.BankAppFXMaven.view.SignInScene;

public class Main extends Application {
	
	private static MainScene mainScene = MainScene.getInstance();
//	private static DatabaseController db = DatabaseController.getInstance();
	@Override
	public void start(Stage primaryStage) {
		try {
			mainScene.start(primaryStage);
			
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