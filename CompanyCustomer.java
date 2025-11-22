public class CompanyCustomer extends Customer {
    private String companyName;
    private String email;
    private String phoneNumber;
    private String address;

    // Default constructor
    public CompanyCustomer() {
        super(generateId(), "", "", "");
    }

    // Constructor for CustomerDAO
    public CompanyCustomer(String customerId, String companyName, String email, String phoneNumber, String address, String customerType) {
        super(customerId, companyName, address, email);
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { 
        this.companyName = companyName; 
        this.name = companyName; // Update parent name
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { 
        this.email = email; 
        this.contact = email; // Update parent contact
    }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }

    public void setAddress(String address) { 
        this.address = address; // This sets parent address
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Company Customer: " + companyName + " - " + email);
    }

    private static String generateId() {
        return "COMP" + System.currentTimeMillis();
    }
}