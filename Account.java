import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Customer owner;
    protected List<String> transactions = new ArrayList<>(); // ADDED TRANSACTION HISTORY

    public Account(String accountNumber, Customer owner, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialDeposit;
        // Records initial deposit
        transactions.add("ACCOUNT CREATED - Initial Deposit: P" + initialDeposit);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("DEPOSIT: P" + amount + " | Balance: P" + balance); // RECORD TRANSACTION
        System.out.println("Deposited P" + amount + " | New balance: P" + balance);
    }

     // MAKE WITHDRAW ABSTRACT - each account type implements its own rules
    public abstract boolean withdraw(double amount);
    
    // ADDED METHOD TO GET TRANSACTIONS
    public List<String> getTransactions() {
        return transactions;
    }

    public double getBalance() { return balance; }
    public abstract void accountType();
}