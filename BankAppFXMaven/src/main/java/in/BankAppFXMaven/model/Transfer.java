package in.BankAppFXMaven.model;

import java.sql.Timestamp;

public class Transfer {

	private int transferID;
	private int fromBankAcc;
	private int toBankAcc;
	private double transferAmount;
	private Timestamp transferDate;
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
	 * @param toBankAcc the toBankAcc to set
	 */
	public void setToBankAcc(int toBankAcc) {
		this.toBankAcc = toBankAcc;
	}
	/**
	 * @return the transferAmount
	 */
	public double getTransferAmount() {
		return transferAmount;
	}
	/**
	 * @param transferAmount the transferAmount to set
	 */
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	/**
	 * @return the TimeStamp
	 */
	public Timestamp getTransferDate() {
		return transferDate;
	}
	/**
	 * @param TimeStamp the TimeStamp to set
	 */
	public void setTransferDate(Timestamp date) {
		this.transferDate = date;
	}
}
