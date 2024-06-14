package in.BankAppFXMaven.model;

public class Login {

	private String loginId;
	private String userId;
	private String passwordHash;
	private String lastLogin;
	private static Login loginSingletonInstance;

	private Login() {
	}

	public static Login getInstance() {
		if (loginSingletonInstance == null) {
			loginSingletonInstance = new Login();
		}
		return loginSingletonInstance;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	/**
     * Clear the login information at log out
     */
    public void clearLogin() {
    	loginSingletonInstance = null;
    }

}
