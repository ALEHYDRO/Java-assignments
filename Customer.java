public abstract class Customer { 
    protected String id;
    protected String name;
    protected String address;
    protected String contact;
    protected boolean isCompany;

    public Customer(String id, String name, String address, String contact, boolean isCompany) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.isCompany = isCompany;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public boolean isCompany() { return isCompany; }

    public abstract void displayCustomerDetails();
}


