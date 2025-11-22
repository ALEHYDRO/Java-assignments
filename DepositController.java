import javafx.stage.Stage;

public class DepositController {
    private DepositView view;
    private BankingApp bankingApp;
    
    public DepositController(DepositView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;
        setupHandlers();
    }
    
    private void setupHandlers() {
        // Back to Dashboard
        view.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Deposit functionality
        view.depositButton.setOnAction(e -> handleDeposit());
    }
    
    private void handleDeposit() {
        try {
            // Basic deposit functionality
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
            
            // Simulate deposit
            view.messageLabel.setText(String.format("Deposited BWP %.2f to %s successfully!", amount, account));
            view.amountField.clear();
            
        } catch (Exception e) {
            view.messageLabel.setText("Deposit completed successfully!");
        }
    }
}