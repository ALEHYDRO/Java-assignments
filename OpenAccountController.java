import javafx.stage.Stage;

public class OpenAccountController {
    private OpenAccountView view;
    private DashboardView dashboardView;
    private Stage primaryStage;
    private BankingApp bankingApp; // Add reference to BankingApp
    
    public OpenAccountController(OpenAccountView view, DashboardView dashboardView, Stage primaryStage, BankingApp bankingApp) {
    this.view = view;
    this.dashboardView = dashboardView;
    this.primaryStage = primaryStage;
    this.bankingApp = bankingApp; // Store the reference
    setupHandlers();
}
    
    private void setupHandlers() {
        view.createButton.setOnAction(e -> handleCreateAccount());
        view.backButton.setOnAction(e -> goBackToDashboard());
    }
    
    private void handleCreateAccount() {
    try {
        // Validate input
        if (view.initialDepositField.getText().isEmpty()) {
            view.messageLabel.setText("Please enter initial deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(view.initialDepositField.getText());
        } catch (NumberFormatException e) {
            view.messageLabel.setText("Please enter valid deposit amount!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        String accountType = view.accountTypeCombo.getValue();
        
        // ENFORCE MINIMUM DEPOSITS STRICTLY
        if (accountType.equals("Savings Account") && initialDeposit < 50) {
            view.messageLabel.setText("Savings account requires BWP 50 minimum deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        if (accountType.equals("Investment Account") && initialDeposit < 500) {
            view.messageLabel.setText("Investment account requires BWP 500 minimum deposit!");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        // Only require company info for Cheque accounts
        if (accountType.equals("Cheque Account")) {
            if (view.companyNameField.getText().isEmpty() || view.companyAddressField.getText().isEmpty()) {
                view.messageLabel.setText("Cheque account requires company information!");
                view.messageLabel.setStyle("-fx-text-fulfill: red;");
                return;
            }
        }
        
        // Get current customer ID from BankingApp
        String customerId = bankingApp.getCurrentCustomerId();
        if (customerId == null) {
            view.messageLabel.setText("Error: Please log in first");
            view.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        // Create account
        String accountNumber = generateAccountNumber();
        
        // Create and store the account for the CURRENT USER
        SimpleAccount newAccount = new SimpleAccount(accountNumber, accountType, initialDeposit);
        AccountManager.addAccount(newAccount, customerId);
        
        // SHOW SUCCESS CONFIRMATION
        String message = "✅ " + accountType + " created successfully!\n" +
                        "Account Number: " + accountNumber + "\n" +
                        "Initial Balance: BWP " + String.format("%.2f", initialDeposit);
        
        view.messageLabel.setText(message);
        view.messageLabel.setStyle("-fx-text-fill: green;");
        view.clearForm();
        
    } catch (Exception e) {
        view.messageLabel.setText("Error creating account. Please try again.");
        view.messageLabel.setStyle("-fx-text-fill: red;");
    }
}
    
    private String createAccountMessage(String accountType, String accountNumber, double balance) {
        String message = "";
        
        switch (accountType) {
            case "Savings Account":
                message = String.format(
                    "✅ SAVINGS ACCOUNT CREATED SUCCESSFULLY!%n%n" +
                    "Account Number: %s%n" +
                    "Account Type: Savings Account%n" +
                    "Initial Balance: BWP %.2f%n" +
                    "Monthly Interest: 0.05%%%n" +
                    "Features: Secure savings, no withdrawals%n%n" +
                    "Your account is now active and ready to use!",
                    accountNumber, balance
                );
                break;
                
            case "Investment Account":
                message = String.format(
                    "📈 INVESTMENT ACCOUNT CREATED SUCCESSFULLY!%n%n" +
                    "Account Number: %s%n" +
                    "Account Type: Investment Account%n" +
                    "Initial Balance: BWP %.2f%n" +
                    "Monthly Interest: 5%%%n" +
                    "Features: High returns, withdrawals allowed%n%n" +
                    "Start growing your money today!",
                    accountNumber, balance
                );
                break;
                
            case "Cheque Account":
                String companyName = view.companyNameField.getText();
                message = String.format(
                    "💳 CHEQUE ACCOUNT CREATED SUCCESSFULLY!%n%n" +
                    "Account Number: %s%n" +
                    "Account Type: Cheque Account%n" +
                    "Initial Balance: BWP %.2f%n" +
                    "Company: %s%n" +
                    "Features: Salary deposits, unlimited transactions%n%n" +
                    "Perfect for your business banking needs!",
                    accountNumber, balance, companyName
                );
                break;
        }
        
        return message;
    }
    
    private String generateAccountNumber() {
        return "ACC" + (100000 + (int)(Math.random() * 900000));
    }
    
    private void goBackToDashboard() {
        primaryStage.setScene(dashboardView.getScene());
        view.clearForm();
    }
}