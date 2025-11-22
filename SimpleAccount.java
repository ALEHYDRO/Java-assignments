public class SimpleAccount {
    private String accountNumber;
    private String accountType;
    private double balance;
    
    public SimpleAccount(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    
    // Setters
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public void setBalance(double balance) { this.balance = balance; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (BWP %.2f)", accountNumber, accountType, balance);
    }
}