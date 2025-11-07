public class IndividualCustomer extends Customer {
    private String dob;
    private String nationalID;
    private String nextOfKin;

    public IndividualCustomer(String id, String name, String address, String contact,
                              String dob, String nationalID, String nextOfKin) {
        super(id, name, address, contact);
        this.dob = dob;
        this.nationalID = nationalID;
        this.nextOfKin = nextOfKin;
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Individual: " + name + " (" + nationalID + ")");
    }
}
