import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    public void addAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, customer_id, account_type, balance) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, account.accountNumber);
            pstmt.setString(2, account.owner.getId());
            
            if (account instanceof SavingsAccount) {
                pstmt.setString(3, "SAVINGS");
            } else if (account instanceof InvestmentAccount) {
                pstmt.setString(3, "INVESTMENT");
            } else if (account instanceof ChequeAccount) {
                pstmt.setString(3, "CHEQUE");
            } else {
                pstmt.setString(3, "UNKNOWN");
            }
            
            pstmt.setDouble(4, account.getBalance());
            pstmt.executeUpdate();
            
            System.out.println("✅ Account added: " + account.accountNumber);
            
        } catch (SQLException e) {
            System.err.println("❌ Error adding account: " + e.getMessage());
        }
    }
    
    public List<Account> getAccountsByCustomerId(String customerId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.*, c.id as cust_id, c.name, c.address, c.contact, c.customer_type " +
                    "FROM accounts a JOIN customers c ON a.customer_id = c.id " +
                    "WHERE a.customer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                // Create customer first
                Customer customer = new IndividualCustomer(
                    rs.getString("cust_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact"),
                    "", "", "" // Simplified for demo
                );
                
                // Create account based on type
                String accountType = rs.getString("account_type");
                Account account;
                
                switch (accountType) {
                    case "SAVINGS":
                        account = new SavingsAccount(
                            rs.getString("account_number"),
                            customer,
                            rs.getDouble("balance")
                        );
                        break;
                    case "INVESTMENT":
                        account = new InvestmentAccount(
                            rs.getString("account_number"),
                            customer,
                            rs.getDouble("balance")
                        );
                        break;
                    case "CHEQUE":
                        account = new ChequeAccount(
                            rs.getString("account_number"),
                            customer,
                            rs.getDouble("balance")
                        );
                        break;
                    default:
                        continue;
                }
                
                accounts.add(account);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting accounts: " + e.getMessage());
        }
        
        return accounts;
    }
    
    public void updateAccountBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            
            System.out.println("✅ Account balance updated: " + accountNumber);
            
        } catch (SQLException e) {
            System.err.println("❌ Error updating account balance: " + e.getMessage());
        }
    }
}