package in.BankAppFXMaven.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtility {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            String passwordToHash = "myPassword123";
            String hashedPassword = hashPassword(passwordToHash);
            System.out.println("Original Password: " + passwordToHash);
            System.out.println("Hashed Password: " + hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

	public static boolean checkPassword(String password, String storedHash) {
		// TODO Auto-generated method stub
		//call databaseService
		return false;
	}
}