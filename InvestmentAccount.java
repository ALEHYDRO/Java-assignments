public class InvestmentAccount extends Account implements InterestCalculator {

    private static final double INDIVIDUAL_RATE = 0.050; // 5%
    private static final double COMPANY_RATE = 0.075;    // 7.5%

    public InvestmentAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, Math.max(deposit, 500.0));
    }

    public void CalculateInterest() {
        double rate = owner.isCompany() ? COMPANY_RATE : INDIVIDUAL_RATE;

        double interest = balance * rate;
        balance += interest;

        System.out.println("Investment interest P" + interest +
                " at " + (rate * 100) + "% | New balance: P" + balance);
    }

    public void accountType() {
        System.out.println("Investment Account");
    }
}

