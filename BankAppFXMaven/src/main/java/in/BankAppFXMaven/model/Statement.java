package in.BankAppFXMaven.model;

public class Statement {

	private int statementID;
	private int bankAccID;
	private String startDate;
	private String endDate;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
