import java.util.List;

public class AccountController {
    private DepositView depositView;
    private WithdrawView withdrawView;
    private AccountsView accountsView;
    private TransactionHistoryView transactionHistoryView;
    
    // Add DAOs
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    
    public AccountController(DepositView depositView, WithdrawView withdrawView, 
                           AccountsView accountsView, TransactionHistoryView transactionHistoryView) {
        this.depositView = depositView;
        this.withdrawView = withdrawView;
        this.accountsView = accountsView;
        this.transactionHistoryView = transactionHistoryView;
        setupEventHandlers();
        loadAccountData();
    }
    
    private void setupEventHandlers() {
        // Deposit view handlers
        depositView.depositButton.setOnAction(e -> handleDeposit());
        depositView.backButton.setOnAction(e -> handleBackFromDeposit());
        
        // Withdraw view handlers  
        withdrawView.withdrawButton.setOnAction(e -> handleWithdraw());
        withdrawView.backButton.setOnAction(e -> handleBackFromWithdraw());
        
        // Accounts view handlers
        accountsView.refreshButton.setOnAction(e -> handleRefreshAccounts());
        accountsView.backButton.setOnAction(e -> handleBackFromAccounts());
        
        // Transaction history handlers
        transactionHistoryView.searchButton.setOnAction(e -> handleSearchTransactions());
        transactionHistoryView.backButton.setOnAction(e -> handleBackFromTransactions());
    }
    
    private void loadAccountData() {
    // TEMPORARY: Use sample data instead of database
    System.out.println("⚠️ Using sample data (database temporarily disabled)");
    
    // Add sample accounts to combo boxes
    depositView.accountComboBox.getItems().addAll(
        "SAV001 - Savings Account (BWP 1500.75)",
        "INV001 - Investment Account (BWP 5000.00)", 
        "CHQ001 - Cheque Account (BWP 25000.50)"
    );
    
    withdrawView.accountComboBox.getItems().addAll(
        "SAV001 - Savings Account (BWP 1500.75)",
        "INV001 - Investment Account (BWP 5000.00)",
        "CHQ001 - Cheque Account (BWP 25000.50)"
    );
    
    transactionHistoryView.accountComboBox.getItems().addAll(
        "SAV001 - Savings Account (BWP 1500.75)",
        "INV001 - Investment Account (BWP 5000.00)",
        "CHQ001 - Cheque Account (BWP 25000.50)"
    );
    
    // Set sample balance displays
    depositView.currentBalanceLabel.setText("Current Balance: BWP 1500.75");
    withdrawView.currentBalanceLabel.setText("Current Balance: BWP 1500.75");
    withdrawView.accountTypeLabel.setText("Account Type: Savings Account");
}
    
    private void handleDeposit() {
        String selectedAccount = depositView.accountComboBox.getValue();
        String amountText = depositView.amountField.getText();
        
        if (selectedAccount == null || selectedAccount.isEmpty()) {
            depositView.messageLabel.setText("Please select an account");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        if (amountText.isEmpty()) {
            depositView.messageLabel.setText("Please enter an amount");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                depositView.messageLabel.setText("Amount must be positive");
                depositView.messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            
            // Extract account number from selection
            String accountNumber = selectedAccount.split(" - ")[0];
            
            // Get current account and update balance
           // TEMPORARY: Simulate deposit without database
System.out.println("Simulating deposit of " + amount + " to " + accountNumber);
double newBalance = 1500.75 + amount; // Simple simulation

depositView.messageLabel.setText("Deposit successful! BWP " + amount + " (simulated)");
depositView.messageLabel.setStyle("-fx-text-fill: green;");
depositView.amountField.clear();
depositView.currentBalanceLabel.setText("Current Balance: BWP " + newBalance);
            
            depositView.messageLabel.setText("Account not found");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
            
        } catch (NumberFormatException e) {
            depositView.messageLabel.setText("Please enter a valid amount");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    private void handleWithdraw() {
        String selectedAccount = withdrawView.accountComboBox.getValue();
        String amountText = withdrawView.amountField.getText();
        
        if (selectedAccount == null || selectedAccount.isEmpty()) {
            withdrawView.messageLabel.setText("Please select an account");
            withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        if (amountText.isEmpty()) {
            withdrawView.messageLabel.setText("Please enter an amount");
            withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                withdrawView.messageLabel.setText("Amount must be positive");
                withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            
            
            String accountNumber = selectedAccount.split(" - ")[0];

// TEMPORARY: Simulate withdrawal without database
System.out.println("Simulating withdrawal of " + amount + " from " + accountNumber);

// Simple balance simulation
double currentBalance = 1500.75; // Sample balance

if (amount > currentBalance) {
    withdrawView.messageLabel.setText("Insufficient funds!");
    withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
    return;
}

double newBalance = currentBalance - amount;

withdrawView.messageLabel.setText("Withdrawal successful! BWP " + amount + " (simulated)");
withdrawView.messageLabel.setStyle("-fx-text-fill: green;");
withdrawView.amountField.clear();
withdrawView.currentBalanceLabel.setText("Current Balance: BWP " + newBalance);
            
        } catch (NumberFormatException e) {
            withdrawView.messageLabel.setText("Please enter a valid amount");
            withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    public void handleRefreshAccounts() {
    System.out.println("Refreshing accounts with sample data...");
    accountsView.accountsContainer.getChildren().clear();
    
    // Use sample data instead of database
    accountsView.addAccountDisplay("SAV001", "Savings Account", 1500.75);
    accountsView.addAccountDisplay("INV001", "Investment Account", 5000.00);
    accountsView.addAccountDisplay("CHQ001", "Cheque Account", 25000.50);
    
    accountsView.totalBalanceLabel.setText("Total Balance: BWP 32500.25");
    accountsView.messageLabel.setText("Accounts loaded with sample data!");
    accountsView.messageLabel.setStyle("-fx-text-fill: blue;");
}
    
    private void handleSearchTransactions() {
    String selectedAccount = transactionHistoryView.accountComboBox.getValue();
    
    if (selectedAccount == null || selectedAccount.isEmpty()) {
        transactionHistoryView.messageLabel.setText("Please select an account");
        transactionHistoryView.messageLabel.setStyle("-fx-text-fill: red;");
        return;
    }
    
    // TEMPORARY: Use sample transaction data
    String accountNumber = selectedAccount.split(" - ")[0];
    
    // Sample transaction data
    String sampleTransactions = 
        "2025-01-15 10:30:00 | DEPOSIT | BWP 1000.00 | Balance: BWP 1000.00 | Initial deposit\n" +
        "2025-01-20 14:15:00 | DEPOSIT | BWP 500.75 | Balance: BWP 1500.75 | Additional deposit\n" +
        "2025-01-25 09:45:00 | WITHDRAWAL | BWP 200.00 | Balance: BWP 1300.75 | Cash withdrawal\n" +
        "2025-02-01 08:00:00 | INTEREST | BWP 7.50 | Balance: BWP 1308.25 | Monthly interest";
    
    transactionHistoryView.transactionsArea.setText(sampleTransactions);
    transactionHistoryView.messageLabel.setText("Loaded sample transaction data");
    transactionHistoryView.messageLabel.setStyle("-fx-text-fill: green;");
}
    
    // Navigation methods
    private void handleBackFromDeposit() {
        depositView.messageLabel.setText("Returning to dashboard...");
    }
    
    private void handleBackFromWithdraw() {
        withdrawView.messageLabel.setText("Returning to dashboard...");
    }
    
    private void handleBackFromAccounts() {
        accountsView.messageLabel.setText("Returning to dashboard...");
    }
    
    private void handleBackFromTransactions() {
        transactionHistoryView.messageLabel.setText("Returning to dashboard...");
    }
}