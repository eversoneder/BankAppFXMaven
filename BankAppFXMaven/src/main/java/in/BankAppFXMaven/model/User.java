package in.BankAppFXMaven.model;

public class User {

	private int id;
	private String name;
	private String surname;
	private String email;

	/**
	 * 
	 * @param id the user_id to set
	 * @param account_number the account_number to set
	 * @param email the email to set
	 * @param password the password to set
	 */
	public User(int id, String name, String surname, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	/**
	 * Empty default constructor
	 */
	public User() {
		
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the user email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public static String getStoredPasswordHash(String username) {
		// TODO Auto-generated method stub
		
//call DatabaseService to send query to get hashed user pass
		return null;
	}
}
