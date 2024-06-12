package in.BankAppFXMaven.model;

public class LoggedUser {

	private static LoggedUser loggedUserSingletonInstance;
	private User user;
	
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
     * Clear the user information at log out
     */
    public void clearUser() {
        this.user = null;
    }
}
