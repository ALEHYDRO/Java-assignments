import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (id, name, address, contact, customer_type) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getContact());
            
            if (customer instanceof IndividualCustomer) {
                pstmt.setString(5, "INDIVIDUAL");
            } else if (customer instanceof CompanyCustomer) {
                pstmt.setString(5, "COMPANY");
            } else {
                pstmt.setString(5, "UNKNOWN");
            }
            
            pstmt.executeUpdate();
            System.out.println("✅ Customer added: " + customer.getName());
            
        } catch (SQLException e) {
            System.err.println("❌ Error adding customer: " + e.getMessage());
        }
    }
    
    public Customer getCustomerById(String id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String customerType = rs.getString("customer_type");
                
                if ("INDIVIDUAL".equals(customerType)) {
                    return new IndividualCustomer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getString("dob"),
                        rs.getString("national_id"),
                        rs.getString("next_of_kin")
                    );
                } else if ("COMPANY".equals(customerType)) {
                    return new CompanyCustomer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getString("company_number"),
                        rs.getString("date_of_incorporation")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting customer: " + e.getMessage());
        }
        
        return null;
    }
    
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String customerType = rs.getString("customer_type");
                Customer customer;
                
                if ("INDIVIDUAL".equals(customerType)) {
                    customer = new IndividualCustomer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getString("dob"),
                        rs.getString("national_id"),
                        rs.getString("next_of_kin")
                    );
                } else {
                    customer = new CompanyCustomer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getString("company_number"),
                        rs.getString("date_of_incorporation")
                    );
                }
                
                customers.add(customer);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting all customers: " + e.getMessage());
        }
        
        return customers;
    }
}