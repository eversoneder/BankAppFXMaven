package in.BankAppFXMaven;

import in.BankAppFXMaven.model.DatabaseDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;

public class Main extends Application {
	
	private static MainScene mainScene = MainScene.getInstance();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			DatabaseDAO db = DatabaseDAO.getInstance();
			
			
			mainScene.start(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}