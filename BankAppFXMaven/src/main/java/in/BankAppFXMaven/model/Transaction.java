package in.BankAppFXMaven.model;

public class Transaction {
    private String email;
    private Double amount;

    public Transaction(String email, Double amount) {
        this.email = email;
        this.amount = amount;
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
}

