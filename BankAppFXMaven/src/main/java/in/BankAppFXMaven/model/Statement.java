package in.BankAppFXMaven.model;

import java.util.ArrayList;

import in.BankAppFXMaven.controller.DatabaseController;

public class Statement {

	private int statementID;
	private int bankAccID;
	private ArrayList<Transaction> transactionList = null;
	
	/**
	 * @return the statementID
	 */
	public int getStatementID() {
		return statementID;
	}
	/**
	 * @param statementID the statementID to set
	 */
	public void setStatementID(int statementID) {
		this.statementID = statementID;
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
	 * @return the transactionList
	 */
	public ArrayList<Transaction> getTransactionList() {

		if(transactionList == null) {
			return transactionList = DatabaseController.getInstance().getStatementTransactionList(LoggedUser.getInstance().getStatement());
		}
		return transactionList;
	}
	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
}
