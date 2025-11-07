public class CompanyCustomer extends Customer {
    private String companyNumber;
    private String dateOfIncorporation;

    public CompanyCustomer(String id, String name, String address, String contact,
                           String companyNumber, String dateOfIncorporation) {
        super(id, name, address, contact);
        this.companyNumber = companyNumber;
        this.dateOfIncorporation = dateOfIncorporation;
    }

    
    public void displayCustomerDetails() {
        System.out.println("Company: " + name + " (" + companyNumber + ")");
    }
}
