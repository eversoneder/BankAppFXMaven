package in.BankAppFXMaven.model;

public class LoggedUser {

	private static LoggedUser loggedUserSingletonInstance;
	private User user;
	private Login login;
	private BankAccount bankAccount;
	private Statement statement;

	public static LoggedUser getInstance() {
		if (loggedUserSingletonInstance == null) {
			loggedUserSingletonInstance = new LoggedUser();
		}
		return loggedUserSingletonInstance;
	}

	/**
	 * Private constructor to prevent external instantiation
	 */
	private LoggedUser() {
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

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

	/**
	 * Clear the user information at log out
	 */
	public void clearUserInfo() {
		if (loggedUserSingletonInstance != null) {
			loggedUserSingletonInstance.user = null;
			loggedUserSingletonInstance.login = null;
			loggedUserSingletonInstance.bankAccount = null;
			loggedUserSingletonInstance.statement = null;
			loggedUserSingletonInstance = null;
		}
	}
}
