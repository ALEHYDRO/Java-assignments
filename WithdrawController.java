import javafx.stage.Stage;

public class WithdrawController {
    private WithdrawView view;
    private BankingApp bankingApp;
    
    public WithdrawController(WithdrawView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;
        setupHandlers();
    }
    
    private void setupHandlers() {
        // Back to Dashboard
        view.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Withdraw functionality
        view.withdrawButton.setOnAction(e -> handleWithdraw());
    }
    
    private void handleWithdraw() {
    try {
        String account = view.accountComboBox.getValue();
        String amountText = view.amountField.getText();
        
        if (account == null || amountText.isEmpty()) {
            view.messageLabel.setText("Please select account and enter amount!");
            return;
        }
        
        double amount = Double.parseDouble(amountText);
        if (amount <= 0) {
            view.messageLabel.setText("Please enter positive amount!");
            return;
        }
        
        // CHECK IF SAVINGS ACCOUNT - PREVENT WITHDRAWAL
        if (account.contains("Savings")) {
            view.messageLabel.setText("❌ Withdrawals not allowed for Savings accounts!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        // Simulate withdrawal for other accounts
        view.messageLabel.setText("✅ Withdrew BWP " + String.format("%.2f", amount) + " from " + account + " successfully!");
        view.messageLabel.setStyle("-fx-text-fill: green;");
        view.amountField.clear();
        
    } catch (Exception e) {
        view.messageLabel.setText("Error processing withdrawal!");
        view.messageLabel.setStyle("-fx-text-fill: red;");
    }
}
}
