public class ChequeAccount extends Account {
    public ChequeAccount(String accNum, Customer owner, double deposit) {
        super(accNum, owner, deposit);
    }

    
    public void accountType() {
        System.out.println("Cheque Account");
    }
}
