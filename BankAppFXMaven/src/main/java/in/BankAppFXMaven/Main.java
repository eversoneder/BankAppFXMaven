package in.BankAppFXMaven;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

import in.BankAppFXMaven.controller.DatabaseController;
import in.BankAppFXMaven.model.DatabaseDAO;
import in.BankAppFXMaven.model.LoggedUser;
import in.BankAppFXMaven.model.User;
import in.BankAppFXMaven.utility.AccountNumberGenerator;
import in.BankAppFXMaven.utility.HashingUtility;
import in.BankAppFXMaven.utility.TimeStampToYear;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import in.BankAppFXMaven.view.BalanceScene;
import in.BankAppFXMaven.view.MainScene;
import in.BankAppFXMaven.view.SignInScene;
import in.BankAppFXMaven.view.SignUpScene;
import in.BankAppFXMaven.view.TransactionView;

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