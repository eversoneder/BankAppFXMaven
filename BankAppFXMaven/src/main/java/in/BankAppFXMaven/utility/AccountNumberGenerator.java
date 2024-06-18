package in.BankAppFXMaven.utility;

import java.util.Random;

import in.BankAppFXMaven.controller.DatabaseController;

public class AccountNumberGenerator {

	public static String generateRandomNumber(DatabaseController db) {
		Random random = new Random();
        String randomNumber;
        do {
            // Generate a random number in the range of 0 to 99999 to complete the 8 digits
            int number = 95000000 + random.nextInt(100000);
            randomNumber = String.valueOf(number);
        } while (db.accNumberExists(randomNumber)); // Check if the number exists in the database
        return randomNumber;
    }
}
