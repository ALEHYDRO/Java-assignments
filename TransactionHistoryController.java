import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

public class TransactionHistoryController {
    private TransactionHistoryView view;
    private BankingApp bankingApp;
    
    public TransactionHistoryController(TransactionHistoryView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;
        setupHandlers();
    }
    
    private void setupHandlers() {
        // Back to Dashboard
        view.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Search functionality
        view.searchButton.setOnAction(e -> handleSearch());
    }
    
    private void handleSearch() {
        try {
            String account = view.accountComboBox.getValue();
            if (account == null) {
                view.messageLabel.setText("Please select an account!");
                return;
            }
            
            // GET TRANSACTIONS FROM YOUR EXISTING SYSTEM
            List<String> transactions = getSampleTransactions(account);
            
            if (transactions.isEmpty()) {
                view.transactionsArea.setText("No transactions found for " + account + "\n\nSample transactions will appear here when you perform transactions.");
            } else {
                StringBuilder transactionText = new StringBuilder();
                transactionText.append("Transaction History for ").append(account).append(":\n\n");
                for (String transaction : transactions) {
                    transactionText.append("• ").append(transaction).append("\n");
                }
                view.transactionsArea.setText(transactionText.toString());
            }
            
            view.messageLabel.setText("✅ Transactions loaded successfully!");
            view.messageLabel.setStyle("-fx-text-fill: green;");
            
        } catch (Exception e) {
            view.messageLabel.setText("Error loading transactions!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    // TEMPORARY METHOD - REPLACE WITH YOUR ACTUAL TRANSACTION DATA
    private List<String> getSampleTransactions(String account) {
        List<String> transactions = new ArrayList<>();
        
        // Add sample transactions based on account type
        if (account.contains("Savings")) {
            transactions.add("2025-11-01: ACCOUNT OPENED - Initial Deposit: P100.00");
            transactions.add("2025-11-15: INTEREST - Amount: P0.05 | Balance: P100.05");
            transactions.add("2025-11-20: DEPOSIT - Amount: P50.00 | Balance: P150.05");
        } else if (account.contains("Investment")) {
            transactions.add("2025-11-01: ACCOUNT OPENED - Initial Deposit: P500.00");
            transactions.add("2025-11-15: INTEREST - Amount: P25.00 | Balance: P525.00");
            transactions.add("2025-11-20: WITHDRAWAL - Amount: P100.00 | Balance: P425.00");
            transactions.add("2025-11-25: DEPOSIT - Amount: P200.00 | Balance: P625.00");
        } else if (account.contains("Cheque")) {
            transactions.add("2025-11-01: ACCOUNT OPENED - Initial Deposit: P1000.00");
            transactions.add("2025-11-05: WITHDRAWAL - Amount: P200.00 | Balance: P800.00");
            transactions.add("2025-11-10: DEPOSIT - Amount: P1500.00 | Balance: P2300.00");
            transactions.add("2025-11-15: WITHDRAWAL - Amount: P500.00 | Balance: P1800.00");
        } else {
            // Generic sample transactions
            transactions.add("2025-11-01: ACCOUNT OPENED - Initial Deposit: P100.00");
            transactions.add("2025-11-10: DEPOSIT - Amount: P50.00 | Balance: P150.00");
            transactions.add("2025-11-20: WITHDRAWAL - Amount: P25.00 | Balance: P125.00");
        }
        
        return transactions;
    }
}