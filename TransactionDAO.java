import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    
    public void addTransaction(String accountNumber, String type, double amount, double balanceAfter, String description) {
        String sql = "INSERT INTO transactions (transaction_id, account_number, transaction_type, amount, balance_after, description) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String transactionId = "TXN" + System.currentTimeMillis();
            
            pstmt.setString(1, transactionId);
            pstmt.setString(2, accountNumber);
            pstmt.setString(3, type);
            pstmt.setDouble(4, amount);
            pstmt.setDouble(5, balanceAfter);
            pstmt.setString(6, description);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("❌ Error adding transaction: " + e.getMessage());
        }
    }
    
    public List<String> getTransactionsByAccount(String accountNumber) {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String transaction = String.format("%s | %s | BWP %.2f | Balance: BWP %.2f | %s",
                    rs.getTimestamp("transaction_date"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getDouble("balance_after"),
                    rs.getString("description")
                );
                transactions.add(transaction);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting transactions: " + e.getMessage());
        }
        
        return transactions;
    }
}