package in.BankAppFXMaven.model;

public class Transaction {
	
	private int transactionID;
	private int bankAccID;
	private String transactionType;
	private java.sql.Timestamp transactionDate;
	private double transactionAmount;
	
	public Transaction() {
		
	}
	
	public Transaction(int bankId, String transactionType, java.sql.Timestamp transactionDate, double transactionAmount) {
		this.bankAccID = bankId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
	}
	/**
	 * @return the transactionID
	 */
	public int getTransactionID() {
		return transactionID;
	}
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
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
	 * @return the transactionType (deposit, withdraw, transfer)
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the transactionDate
	 */
	public java.sql.Timestamp getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(java.sql.Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}

