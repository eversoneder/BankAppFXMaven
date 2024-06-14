package in.BankAppFXMaven;

import in.BankAppFXMaven.model.DatabaseDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;
import in.BankAppFXMaven.view.SignInScene;

public class Main extends Application {
	
	private static MainScene mainScene = MainScene.getInstance();
	@Override
	public void start(Stage primaryStage) {
		try {
			mainScene.start(primaryStage);
//			DatabaseDAO db = DatabaseDAO.getInstance();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}