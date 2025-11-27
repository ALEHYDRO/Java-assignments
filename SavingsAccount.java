public class SavingsAccount extends Account implements InterestCalculator {
    private static final double INTEREST_RATE = 0.025; // 2.5%

    public SavingsAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, Math.max(deposit, 50.0));
    }

    // COMPLETELY PREVENT WITHDRAWALS
    @Override
    public boolean withdraw(double amount) {
        // Savings accounts CANNOT withdraw - always return false
        System.out.println("❌ ERROR: Withdrawals are not allowed for Savings accounts!");
        return false;
    }

    public void CalculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        transactions.add("INTEREST: P" + interest + " | Balance: P" + balance);
        System.out.println("Savings interest P" + interest + " | New balance: P" + balance);
    }

    public void accountType() {
        System.out.println("Savings Account");
    }
}