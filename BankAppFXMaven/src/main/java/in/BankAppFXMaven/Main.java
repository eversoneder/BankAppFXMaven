package in.BankAppFXMaven;

import in.BankAppFXMaven.model.SimpleDbConnection;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SimpleDbConnection db = SimpleDbConnection.getInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}