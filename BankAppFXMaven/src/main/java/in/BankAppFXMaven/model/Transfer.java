package in.BankAppFXMaven.model;

public class Transfer {

	private int transferID;
	private int transactionID;
	private int fromBankAcc;
	private int toBankAcc;
	
	public Transfer () {
		
	}
	public Transfer (int transactionId, int fromBankAcc, int toBankAcc) {
		this.transactionID = transactionId;
		this.fromBankAcc = fromBankAcc;
		this.toBankAcc = toBankAcc;
	}
	/**
	 * @return the transferID
	 */
	public int getTransferID() {
		return transferID;
	}
	/**
	 * @param transferID the transferID to set
	 */
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}
	/**
	 * @return the fromBankAcc
	 */
	public int getFromBankAcc() {
		return fromBankAcc;
	}
	/**
	 * @param fromBankAcc the fromBankAcc to set
	 */
	public void setFromBankAcc(int fromBankAcc) {
		this.fromBankAcc = fromBankAcc;
	}
	/**
	 * @return the toBankAcc
	 */
	public int getToBankAcc() {
		return toBankAcc;
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
	 * @param toBankAcc the toBankAcc to set
	 */
	public void setToBankAcc(int toBankAcc) {
		this.toBankAcc = toBankAcc;
	}
}
