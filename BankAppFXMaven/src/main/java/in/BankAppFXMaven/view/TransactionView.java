package in.BankAppFXMaven.view;

public class TransactionView {
    private String email;
    private Double amount;
    private String date;

    public TransactionView(String email, String date, Double amount) {
        this.email = email;
        this.amount = amount;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void setDate(String date) {
    	this .date = date;
    }
    
    public String getDate() {
    	return date;
    }
}
