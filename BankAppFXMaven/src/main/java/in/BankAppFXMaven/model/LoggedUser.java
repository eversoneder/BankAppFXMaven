package in.BankAppFXMaven.model;

public class LoggedUser {

	private static LoggedUser loggedUserSingletonInstance;
	private User user;
	private Login login;
	private BankAccount bankAccount;
	private Statement statement;
	private Transfer transfer;
	private Transaction transaction;
	
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
	 * @return the transfer
	 */
	public Transfer getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
     * Clear the user information at log out
     */
    public void clearUser() {
        this.user = null;
    }
}
