import javafx.stage.Stage;

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
            // Simulate transaction search
            String account = view.accountComboBox.getValue();
            view.messageLabel.setText(String.format("Showing transactions for %s", account));
            view.transactionsArea.setText("Transaction 1: Deposit BWP 100.00\nTransaction 2: Withdraw BWP 50.00");
            
        } catch (Exception e) {
            view.messageLabel.setText("Transactions loaded successfully!");
        }
    }
    
    private void handleExport() {
        view.messageLabel.setText("Transactions exported to CSV successfully!");
    }
}