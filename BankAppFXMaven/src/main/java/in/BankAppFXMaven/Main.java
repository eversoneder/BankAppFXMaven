package in.BankAppFXMaven;

import javafx.application.Application;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.MainScene;

public class Main extends Application {
	
	private static MainScene mainScene = MainScene.getInstance();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mainScene.start(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
