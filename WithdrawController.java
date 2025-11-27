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
        String selectedAccountDisplay = view.accountComboBox.getValue();
        String amountText = view.amountField.getText();
        
        if (selectedAccountDisplay == null || amountText.isEmpty()) {
            view.messageLabel.setText("❌ Please select account and enter amount!");
            view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            return;
        }
        
        double amount = Double.parseDouble(amountText);
        if (amount <= 0) {
            view.messageLabel.setText("❌ Please enter positive amount!");
            view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            return;
        }
        
        // Extract account number from display text
        String accountNumber = selectedAccountDisplay.split(" - ")[0];
        
        // GET THE ACTUAL ACCOUNT OBJECT
        SimpleAccount selectedAccount = AccountManager.getAccount(accountNumber);
        if (selectedAccount == null) {
            view.messageLabel.setText("❌ Account not found!");
            view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            return;
        }
        
        // CHECK IF IT'S A SAVINGS ACCOUNT
        if (selectedAccount.getAccountType().contains("Savings")) {
            view.messageLabel.setText("❌ Withdrawals are NOT allowed for Savings accounts!");
            view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            return;
        }
        
        // USE THE PROPER WITHDRAW LOGIC
        boolean success = selectedAccount.withdraw(amount);
        
        if (success) {
            view.messageLabel.setText("✅ Withdrew BWP " + String.format("%.2f", amount) + " successfully!");
            view.messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            view.amountField.clear();
            
            // Refresh balance display
            view.currentBalanceLabel.setText("Current Balance: BWP " + String.format("%.2f", selectedAccount.getBalance()));
        } else {
            view.messageLabel.setText("❌ Withdrawal failed! Insufficient funds.");
            view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        }
        
    } catch (NumberFormatException e) {
        view.messageLabel.setText("❌ Please enter a valid amount!");
        view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    } catch (Exception e) {
        view.messageLabel.setText("❌ Error processing withdrawal!");
        view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    }
}
}
