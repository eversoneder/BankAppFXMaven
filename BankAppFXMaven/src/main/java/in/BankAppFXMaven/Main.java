package in.BankAppFXMaven;

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
	
	private static BalanceScene balanceScene = BalanceScene.getInstance();
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			balanceScene.start(primaryStage);
			
			
			//hard code for testing
			
//			//SENDERS-------------------------------------------------------------------
//			String[] sender = { "AlbertoOswaldo92@gmail.com", "user2@example.com", "user3@example.com", "user4@example.com",
//					"user5@example.com", "user6@example.com", "user8@example.com", "user9@example.com", "user10@example.com",};
//			//------------------------------------------------------------------------
//			//AMOUNTS-----------------------------------------------------------------
//			double[] amount = { 90.00, 50.00, -200.50, -75.90, 80.00, 90.00, 50.00, 1200.50, -75.90 };
//			//------------------------------------------------------------------------
//			//DATES-------------------------------------------------------------------
//			java.sql.Timestamp newTimeStamp = new java.sql.Timestamp(System.currentTimeMillis());
//			String date1 = TimeStampToYear.timeStampToLocalDateString(newTimeStamp);
//			// timeStampToYearString receives LocalDate and return String
//			String date2 = TimeStampToYear.addDaysToDateString(newTimeStamp, 2);
//			String date3 = TimeStampToYear.addDaysToDateString(newTimeStamp, 3);
//			String date4 = TimeStampToYear.addDaysToDateString(newTimeStamp, 5);
//			String date5 = TimeStampToYear.addDaysToDateString(newTimeStamp, 7);
//			String date6 = TimeStampToYear.addDaysToDateString(newTimeStamp, 2);
//			String date7 = TimeStampToYear.addDaysToDateString(newTimeStamp, 3);
//			String date8 = TimeStampToYear.addDaysToDateString(newTimeStamp, 5);
//			String date9 = TimeStampToYear.addDaysToDateString(newTimeStamp, 7);
//			String dates[] = {date1, date2, date3, date4, date5, date6, date7, date8, date9};
//			//------------------------------------------------------------------------
//			
//			ArrayList<TransactionView> transactions = new ArrayList<TransactionView>();
//			for(int i = 0; i < sender.length; i++) {
//			TransactionView t = new TransactionView(sender[i], dates[i], amount[i]);
//			transactions.add(t);
//			}
//			
//			BalanceScene.showStatementDialog(transactions);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}