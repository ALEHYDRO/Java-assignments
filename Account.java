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

    public boolean withdraw(double amount) { // CHANGE TO RETURN BOOLEAN
        if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
            return false;
        } else {
            balance -= amount;
            transactions.add("WITHDRAWAL: P" + amount + " | Balance: P" + balance); // RECORD TRANSACTION
            System.out.println("Withdrew P" + amount + " | New balance: P" + balance);
            return true;
        }
    }

    // ADDED METHOD TO GET TRANSACTIONS
    public List<String> getTransactions() {
        return transactions;
    }

    public double getBalance() { return balance; }
    public abstract void accountType();
}