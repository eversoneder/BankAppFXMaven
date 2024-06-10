package in.BankAppFXMaven.model;

public class BankAccount {
	private String customerName;
	private String customerID;
	private int balance;
	private int previousTransaction;

	public BankAccount(String customerName, String customerID) {
		this.customerName = customerName;
		this.customerID = customerID;
	}

	public void deposit(int amount) {
		if (amount != 0) {
			balance = balance + amount;
			previousTransaction = amount;
		}
	}

	public void withdraw(int amount) {
		if (amount != 0) {
			balance = balance - amount;
			previousTransaction = -amount;// assign a negative value so getPreviousTransaction knows the last
											// transaction was a withdrawn.
		} else if (amount > balance) {
			System.out.println("Sorry, you have only " + balance + "that can be withdrawn.");
		} else {
			System.out.println("Sorry, you don't have money to withdraw.");
		}
	}

	public boolean checkBalance() {
		if (balance == 0) {
			System.out.println("\nSorry, you don't have money to withdraw.\n");
			return false;
		} else {
			return true;
		}
	}

	public String getPreviousTransaction() {
		if (previousTransaction > 0) {
			return "Deposit of " + Integer.toString(previousTransaction) + "€.";
		} else if (previousTransaction < 0) {// modifies negative to positive number
			return "Withdrawn of " + Math.abs(previousTransaction) + "€.";
		} else {
			return "No transaction has occurred yet.";
		}
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public int getBalance() {
		return this.balance;
	}
}
