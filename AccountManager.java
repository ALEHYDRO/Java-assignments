import java.util.*;

public class AccountManager {
    private static Map<String, SimpleAccount> accounts = new HashMap<>();
    private static Map<String, List<SimpleAccount>> customerAccounts = new HashMap<>();
    
    // Initialize sample accounts system
    public static void initializeSampleAccounts() {
        System.out.println("Account manager initialized - sample accounts will be created per user");
    }
    
    // Method to ensure a user has sample accounts (for admin)
    public static void ensureUserHasSampleAccounts(String customerId, String username) {
        if (customerId == null) return;
        
        // Check if user already has accounts
        List<SimpleAccount> userAccounts = getCustomerAccounts(customerId);
        if (userAccounts.isEmpty() && "admin".equals(username)) {
            // Create sample accounts only for admin
            SimpleAccount savings = new SimpleAccount("SAV001", "Savings Account", 1500.75);
            SimpleAccount investment = new SimpleAccount("INV001", "Investment Account", 5000.00);
            SimpleAccount cheque = new SimpleAccount("CHQ001", "Cheque Account", 25000.50);
            
            addAccount(savings, customerId);
            addAccount(investment, customerId);
            addAccount(cheque, customerId);
            
            System.out.println("Sample accounts created for admin: " + customerId);
        }
    }
    
    // Add a new account
    public static void addAccount(SimpleAccount account, String customerId) {
        accounts.put(account.getAccountNumber(), account);
        
        // Add to customer's account list
        if (!customerAccounts.containsKey(customerId)) {
            customerAccounts.put(customerId, new ArrayList<>());
        }
        customerAccounts.get(customerId).add(account);
        
        System.out.println("Account added: " + account.getAccountNumber() + " for customer: " + customerId);
    }
    
    // Get all accounts for a customer
    public static List<SimpleAccount> getCustomerAccounts(String customerId) {
        return customerAccounts.getOrDefault(customerId, new ArrayList<>());
    }
    
    // Get specific account
    public static SimpleAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    // Update account balance
    public static boolean updateBalance(String accountNumber, double amount) {
        SimpleAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }
    
    // Get current balance
    public static double getBalance(String accountNumber) {
        SimpleAccount account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0.0;
    }
    
    // Refresh account lists in controllers
    public static void refreshAccountLists() {
        System.out.println("Account lists refreshed. Total accounts: " + accounts.size());
    }
}