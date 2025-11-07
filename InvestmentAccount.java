public class InvestmentAccount extends Account implements InterestCalculator {
    private static final double INTEREST_RATE = 0.050;

    public InvestmentAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, Math.max(deposit, 500.0)); // Minimum P500
    }

    
    public void CalculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest of P" + interest + " applied. New balance: P" + balance);
    }

    
    public void accountType() {
        System.out.println("Investment Account");
    }
}

