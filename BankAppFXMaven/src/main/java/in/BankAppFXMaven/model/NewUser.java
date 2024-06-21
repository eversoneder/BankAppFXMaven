package in.BankAppFXMaven.model;

/**
 * NewUser class is used in the sign up page, uploading the user.email & login.password_hash
 */
public class NewUser {
	
	//user saves only email from sign up page, when first logs in, app asks name & surname
			//login saves user_id & password_hash
			//bank_account saves only user_id
			//statement saves only bank_account_id

	private Login login;
	private User user;
	private BankAccount bankAccount;
	private Statement statement;

	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the bankAccount
	 */
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * @param statement the statement to set
	 */
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
}
