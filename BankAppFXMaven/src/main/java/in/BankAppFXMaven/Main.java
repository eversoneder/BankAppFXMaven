package in.BankAppFXMaven;

import in.BankAppFXMaven.model.SimpleDbConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;

public class Main extends Application {
	
	private static MainScene mainScene = MainScene.getInstance();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SimpleDbConnection db = SimpleDbConnection.getInstance();
			
			
			mainScene.start(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}