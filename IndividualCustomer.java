public class IndividualCustomer extends Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    // Default constructor - calls super with generated ID
    public IndividualCustomer() {
        super(generateId(), "", "", "");
    }

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
        this.name = firstName + " " + lastName; // Update the name in parent class
    }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
        this.name = firstName + " " + lastName; // Update the name in parent class
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { 
        this.email = email; 
        this.contact = email; // Update contact in parent class
    }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }

    public void setAddress(String address) { 
        this.address = address; // This sets the parent class address field
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Individual Customer: " + firstName + " " + lastName + " - " + email);
    }

    private static String generateId() {
        return "CUST" + System.currentTimeMillis();
    }

    // Add this constructor to your existing IndividualCustomer class
public IndividualCustomer(String customerId, String firstName, String lastName, String email, String phoneNumber, String address, String customerType) {
    super(customerId, firstName + " " + lastName, address, email); // Assuming email is contact
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
}
}