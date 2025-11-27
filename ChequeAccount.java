public class ChequeAccount extends Account {
    public ChequeAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, deposit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Error: Insufficient funds.");
            return false;
        } else {
            balance -= amount;
            transactions.add("WITHDRAWAL: P" + amount + " | Balance: P" + balance);
            System.out.println("Withdrew P" + amount + " | New balance: P" + balance);
            return true;
        }
    }
    
    public void accountType() {
        System.out.println("Cheque Account");
    }
}
