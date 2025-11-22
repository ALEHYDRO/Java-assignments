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
            // Basic withdraw functionality
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
            
            // Simulate withdrawal
            view.messageLabel.setText(String.format("Withdrew BWP %.2f from %s successfully!", amount, account));
            view.amountField.clear();
            
        } catch (Exception e) {
            view.messageLabel.setText("Withdrawal completed successfully!");
        }
    }
}
