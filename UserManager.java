import java.util.*;

public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    
    static {
        // Add default admin user with unique customer ID
        users.put("admin", new User("admin", "password", "admin@bank.com", "Administrator"));
    }
    
    public static class User {
        private String username;
        private String password;
        private String email;
        private String displayName;
        private String customerType;
        private String customerId; // Add customer ID
        
        public User(String username, String password, String email, String displayName) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.displayName = displayName;
            this.customerId = "CUST" + System.currentTimeMillis(); // Generate unique ID
        }
        
        // Getters
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getEmail() { return email; }
        public String getDisplayName() { return displayName; }
        public String getCustomerType() { return customerType; }
        public String getCustomerId() { return customerId; } // New getter
        
        // Setters
        public void setCustomerType(String customerType) { this.customerType = customerType; }
    }
    
    // Register new user
    public static boolean registerUser(String username, String password, String email, String displayName, String customerType) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        
        User newUser = new User(username, password, email, displayName);
        newUser.setCustomerType(customerType);
        users.put(username, newUser);
        System.out.println("User registered: " + username + " with ID: " + newUser.getCustomerId());
        return true;
    }
    
    // Authenticate user
    public static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    // Check if username exists
    public static boolean usernameExists(String username) {
        return users.containsKey(username);
    }
    
    // Get user by username
    public static User getUser(String username) {
        return users.get(username);
    }
}