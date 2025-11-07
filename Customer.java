    public abstract class Customer {
    protected String id;
    protected String name;
    protected String address;
    protected String contact;

    public Customer(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }

    public abstract void displayCustomerDetails();
}

