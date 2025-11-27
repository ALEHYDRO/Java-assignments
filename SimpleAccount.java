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
    
    public boolean withdraw(double amount) {
    // Check for Savings account restriction
    if (this.accountType != null && this.accountType.contains("Savings")) {
        System.out.println("❌ Withdrawals not allowed for Savings accounts");
        return false;
    }
    
    // Check sufficient funds
    if (amount > balance) {
        System.out.println("❌ Insufficient funds");
        return false;
    }
    
    // Process withdrawal
    balance -= amount;
    System.out.println("✅ Withdrawn: BWP " + amount + " | New Balance: BWP " + balance);
    return true;
}


    @Override
    public String toString() {
        return String.format("%s - %s (BWP %.2f)", accountNumber, accountType, balance);
    }
}