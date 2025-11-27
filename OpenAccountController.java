import java.util.List;

import javafx.stage.Stage;

public class OpenAccountController {
    private OpenAccountView view;
    private DashboardView dashboardView;
    private Stage primaryStage;
    private BankingApp bankingApp;
    
    public OpenAccountController(OpenAccountView view, DashboardView dashboardView, Stage primaryStage, BankingApp bankingApp) {
        this.view = view;
        this.dashboardView = dashboardView;
        this.primaryStage = primaryStage;
        this.bankingApp = bankingApp;
        setupHandlers();
    }
    
    private void setupHandlers() {
        view.createButton.setOnAction(e -> handleCreateAccount());
        view.backButton.setOnAction(e -> goBackToDashboard());
    }
    
    private void handleCreateAccount() {
    try {
        // CHECK IF USER IS LOGGED IN
        if (!bankingApp.isUserLoggedIn()) {
            view.messageLabel.setText("❌ Please login first to create an account!");
            view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        // Validate input
        if (view.initialDepositField.getText().isEmpty()) {
            view.messageLabel.setText("❌ Please enter initial deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(view.initialDepositField.getText());
        } catch (NumberFormatException e) {
            view.messageLabel.setText("❌ Please enter valid deposit amount!");
            view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        String accountType = view.accountTypeCombo.getValue();
        
        // ENFORCE MINIMUM DEPOSITS
        if (accountType.equals("Savings Account") && initialDeposit < 50) {
            view.messageLabel.setText("❌ Savings account requires BWP 50 minimum deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        if (accountType.equals("Investment Account") && initialDeposit < 500) {
            view.messageLabel.setText("❌ Investment account requires BWP 500 minimum deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }
        
        // Only require company info for Cheque accounts
        if (accountType.equals("Cheque Account")) {
            if (view.companyNameField.getText().isEmpty() || view.companyAddressField.getText().isEmpty()) {
                view.messageLabel.setText("❌ Cheque account requires company information!");
                view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                return;
            }
        }
        
        // Create account - FIX: Generate account number first
        String accountNumber = generateAccountNumber();
        String customerId = bankingApp.getCurrentCustomerId();
        
        // FIX: Create account object - remove duplicate declaration
        SimpleAccount newAccount = new SimpleAccount(accountNumber, accountType, initialDeposit);
        boolean success = AccountManager.addAccount(newAccount, customerId);
        
        if (success) {
    // SHOW SUCCESS IN GUI
    String message = "✅ ACCOUNT CREATED SUCCESSFULLY!\n" +
                    "Account: " + accountNumber + " (" + accountType + ")\n" +
                    "Initial Balance: BWP " + String.format("%.2f", initialDeposit);
    
    view.messageLabel.setText(message);
    view.messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold; -fx-font-size: 14px;");
    
    // Clear only the input fields, keep message
    view.initialDepositField.clear();
    view.companyNameField.clear();
    view.companyAddressField.clear();
    
    // REFRESH ACCOUNTS VIEW
    bankingApp.refreshAccountsView();
    
} else {
    view.messageLabel.setText("❌ Failed to create account. Please try again.");
    view.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
}
        
    } catch (Exception e) {
        view.messageLabel.setText("❌ Error creating account. Please try again.");
        view.messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    }
}

private String generateAccountNumber() {
    return "ACC" + (100000 + (int)(Math.random() * 900000));
}
    
    private void goBackToDashboard() {
        primaryStage.setScene(dashboardView.getScene());
        view.clearForm();
    }
}