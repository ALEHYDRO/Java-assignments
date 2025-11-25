public class SavingsAccount extends Account implements InterestCalculator {
    private static final double INTEREST_RATE = 0.025; // 2.5%

    public SavingsAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, Math.max(deposit, 50.0));
    }

    public void CalculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Savings interest P" + interest + " | New balance: P" + balance);
    }

    public void accountType() {
        System.out.println("Savings Account");
    }
}

