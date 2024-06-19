package in.BankAppFXMaven.model;

public class BankAccount {
	
	private int userID;
	private int bankAccNum;
	private double bankAccBalance;
	private int bankAccID;
	
	/**
	 * @return the bankAccID
	 */
	public int getBankAccID() {
		return bankAccID;
	}
	/**
	 * @param bankAccID the bankAccID to set
	 */
	public void setBankAccID(int bankAccID) {
		this.bankAccID = bankAccID;
	}
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * @return the bankAccNum
	 */
	public int getBankAccNum() {
		return bankAccNum;
	}
	/**
	 * @param bankAccNum the bankAccNum to set
	 */
	public void setBankAccNum(int bankAccNum) {
		this.bankAccNum = bankAccNum;
	}
	/**
	 * @return the bankAccBalance
	 */
	public double getBankAccBalance() {
		return bankAccBalance;
	}
	/**
	 * @param bankAccBalance the bankAccBalance to set
	 */
	public void setBankAccBalance(double bankAccBalance) {
		this.bankAccBalance = bankAccBalance;
	}
}
