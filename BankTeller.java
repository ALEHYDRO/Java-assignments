public class BankTeller {
    public Account openAccount(String type, Customer customer, double deposit) {
        switch (type.toLowerCase()) {
            case "savings":
                return new SavingsAccount("SAV" + System.currentTimeMillis(), customer, deposit);
            case "investment":
                return new InvestmentAccount("INV" + System.currentTimeMillis(), customer, deposit);
            case "cheque":
                return new ChequeAccount("CHQ" + System.currentTimeMillis(), customer, deposit);
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }

    public void captureCustomerDetails(Customer customer) {
        customer.displayCustomerDetails();
    }
}
