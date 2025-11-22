import java.util.*;

public class AccountController {
    private DepositView depositView;
    private WithdrawView withdrawView;
    private AccountsView accountsView;
    private TransactionHistoryView transactionHistoryView;
    private BankingApp bankingApp;
    
    // Remove hardcoded customer ID - use dynamic user tracking
    private String currentCustomerId = null;
    
    public AccountController(DepositView depositView, WithdrawView withdrawView, 
                           AccountsView accountsView, TransactionHistoryView transactionHistoryView,
                           BankingApp bankingApp) {
        this.depositView = depositView;
        this.withdrawView = withdrawView;
        this.accountsView = accountsView;
        this.transactionHistoryView = transactionHistoryView;
        this.bankingApp = bankingApp;
        
        setupEventHandlers();
        initializeEmptyViews(); // Start with empty views until user logs in
    }
    
    // Method to set current user when someone logs in
    public void setCurrentUser(String customerId) {
    this.currentCustomerId = customerId;
    System.out.println("DEBUG: AccountController user set to: " + customerId);
    
    // Debug: Check what accounts exist for this user
    List<SimpleAccount> accounts = AccountManager.getCustomerAccounts(customerId);
    System.out.println("DEBUG: User has " + accounts.size() + " accounts");
    
    loadAccountData();
}
    
    // Method to clear user when someone logs out
    public void clearCurrentUser() {
        this.currentCustomerId = null;
        initializeEmptyViews(); // Reset all views
    }
    
    private void initializeEmptyViews() {
        // Clear all dropdowns and set default messages
        depositView.accountComboBox.getItems().clear();
        withdrawView.accountComboBox.getItems().clear();
        transactionHistoryView.accountComboBox.getItems().clear();
        
        depositView.currentBalanceLabel.setText("Current Balance: --");
        withdrawView.currentBalanceLabel.setText("Current Balance: --");
        withdrawView.accountTypeLabel.setText("Account Type: --");
        
        depositView.messageLabel.setText("Please log in to see your accounts");
        withdrawView.messageLabel.setText("Please log in to see your accounts");
        accountsView.messageLabel.setText("Please log in to see your accounts");
    }
    
    private void setupEventHandlers() {
        // Deposit view handlers
        depositView.depositButton.setOnAction(e -> handleDeposit());
        depositView.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Withdraw view handlers  
        withdrawView.withdrawButton.setOnAction(e -> handleWithdraw());
        withdrawView.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Accounts view handlers
        accountsView.refreshButton.setOnAction(e -> handleRefreshAccounts());
        accountsView.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Transaction history handlers
        transactionHistoryView.searchButton.setOnAction(e -> handleSearchTransactions());
        transactionHistoryView.backButton.setOnAction(e -> bankingApp.showDashboard());
        
        // Update balance display when account selection changes
        depositView.accountComboBox.setOnAction(e -> updateDepositBalanceDisplay());
        withdrawView.accountComboBox.setOnAction(e -> updateWithdrawBalanceDisplay());
    }
    
    private void loadAccountData() {
        // Clear existing data
        depositView.accountComboBox.getItems().clear();
        withdrawView.accountComboBox.getItems().clear();
        transactionHistoryView.accountComboBox.getItems().clear();
        
        // Check if user is logged in
        if (currentCustomerId == null) {
            depositView.messageLabel.setText("Please log in to access your accounts");
            withdrawView.messageLabel.setText("Please log in to access your accounts");
            accountsView.messageLabel.setText("Please log in to access your accounts");
            return;
        }
        
        // Load accounts for current user only
        List<SimpleAccount> customerAccounts = AccountManager.getCustomerAccounts(currentCustomerId);
        
        if (customerAccounts.isEmpty()) {
            // No accounts for this user
            depositView.messageLabel.setText("No accounts found. Please open a new account first.");
            withdrawView.messageLabel.setText("No accounts found. Please open a new account first.");
            accountsView.messageLabel.setText("No accounts found. Please open a new account first.");
            depositView.currentBalanceLabel.setText("Current Balance: BWP 0.00");
            withdrawView.currentBalanceLabel.setText("Current Balance: BWP 0.00");
            accountsView.totalBalanceLabel.setText("Total Balance: BWP 0.00");
            return;
        }
        
        // Add accounts to combo boxes WITHOUT balances in display
        for (SimpleAccount account : customerAccounts) {
            String accountDisplay = String.format("%s - %s", 
                account.getAccountNumber(), 
                account.getAccountType()); // No balance in display
                
            depositView.accountComboBox.getItems().add(accountDisplay);
            withdrawView.accountComboBox.getItems().add(accountDisplay);
            transactionHistoryView.accountComboBox.getItems().add(accountDisplay);
        }
        
        // Reset balance displays until account is selected
        depositView.currentBalanceLabel.setText("Current Balance: --");
        withdrawView.currentBalanceLabel.setText("Current Balance: --");
        withdrawView.accountTypeLabel.setText("Account Type: --");
        
        depositView.messageLabel.setText("Accounts loaded successfully! Select an account to continue.");
        withdrawView.messageLabel.setText("Accounts loaded successfully! Select an account to continue.");
    }
    
    private void updateDepositBalanceDisplay() {
        String selectedAccount = depositView.accountComboBox.getValue();
        if (selectedAccount != null && currentCustomerId != null) {
            String accountNumber = selectedAccount.split(" - ")[0];
            double balance = AccountManager.getBalance(accountNumber);
            depositView.currentBalanceLabel.setText(String.format("Current Balance: BWP %.2f", balance));
        }
    }
    
    private void updateWithdrawBalanceDisplay() {
        String selectedAccount = withdrawView.accountComboBox.getValue();
        if (selectedAccount != null && currentCustomerId != null) {
            String accountNumber = selectedAccount.split(" - ")[0];
            double balance = AccountManager.getBalance(accountNumber);
            withdrawView.currentBalanceLabel.setText(String.format("Current Balance: BWP %.2f", balance));
            
            // Also show account type
            SimpleAccount account = AccountManager.getAccount(accountNumber);
            if (account != null) {
                withdrawView.accountTypeLabel.setText("Account Type: " + account.getAccountType());
            }
        }
    }
    
    private void handleDeposit() {
        // Check if user is logged in
        if (currentCustomerId == null) {
            depositView.messageLabel.setText("Please log in first");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
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
            
            // Update balance in AccountManager
            boolean success = AccountManager.updateBalance(accountNumber, amount);
            
            if (success) {
                double newBalance = AccountManager.getBalance(accountNumber);
                depositView.messageLabel.setText(String.format("Deposit successful! New balance: BWP %.2f", newBalance));
                depositView.messageLabel.setStyle("-fx-text-fill: green;");
                depositView.amountField.clear();
                updateDepositBalanceDisplay(); // Refresh display
            } else {
                depositView.messageLabel.setText("Account not found");
                depositView.messageLabel.setStyle("-fx-text-fill: red;");
            }
            
        } catch (NumberFormatException e) {
            depositView.messageLabel.setText("Please enter a valid amount");
            depositView.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    private void handleWithdraw() {
        // Check if user is logged in
        if (currentCustomerId == null) {
            withdrawView.messageLabel.setText("Please log in first");
            withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
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
            double currentBalance = AccountManager.getBalance(accountNumber);
            
            // Check sufficient funds
            if (amount > currentBalance) {
                withdrawView.messageLabel.setText("Insufficient funds!");
                withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            
            // Update balance in AccountManager (negative amount for withdrawal)
            boolean success = AccountManager.updateBalance(accountNumber, -amount);
            
            if (success) {
                double newBalance = AccountManager.getBalance(accountNumber);
                withdrawView.messageLabel.setText(String.format("Withdrawal successful! New balance: BWP %.2f", newBalance));
                withdrawView.messageLabel.setStyle("-fx-text-fill: green;");
                withdrawView.amountField.clear();
                updateWithdrawBalanceDisplay(); // Refresh display
            }
            
        } catch (NumberFormatException e) {
            withdrawView.messageLabel.setText("Please enter a valid amount");
            withdrawView.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    public void handleRefreshAccounts() {
        accountsView.accountsContainer.getChildren().clear();
        
        // Check if user is logged in
        if (currentCustomerId == null) {
            accountsView.messageLabel.setText("Please log in to view your accounts");
            accountsView.messageLabel.setStyle("-fx-text-fill: blue;");
            accountsView.totalBalanceLabel.setText("Total Balance: --");
            return;
        }
        
        // Load real accounts from AccountManager
        List<SimpleAccount> customerAccounts = AccountManager.getCustomerAccounts(currentCustomerId);
        
        if (customerAccounts.isEmpty()) {
            accountsView.messageLabel.setText("No accounts found. Please open a new account first.");
            accountsView.messageLabel.setStyle("-fx-text-fill: blue;");
            accountsView.totalBalanceLabel.setText("Total Balance: BWP 0.00");
            return;
        }
        
        double totalBalance = 0;
        for (SimpleAccount account : customerAccounts) {
            accountsView.addAccountDisplay(account.getAccountNumber(), account.getAccountType(), account.getBalance());
            totalBalance += account.getBalance();
        }
        
        accountsView.totalBalanceLabel.setText(String.format("Total Balance: BWP %.2f", totalBalance));
        accountsView.messageLabel.setText("Accounts loaded successfully!");
        accountsView.messageLabel.setStyle("-fx-text-fill: green;");
    }
    
    private void handleSearchTransactions() {
        // Check if user is logged in
        if (currentCustomerId == null) {
            transactionHistoryView.messageLabel.setText("Please log in first");
            transactionHistoryView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        String selectedAccount = transactionHistoryView.accountComboBox.getValue();
        
        if (selectedAccount == null || selectedAccount.isEmpty()) {
            transactionHistoryView.messageLabel.setText("Please select an account");
            transactionHistoryView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        // For now, use sample transaction data
        String accountNumber = selectedAccount.split(" - ")[0];
        
        String sampleTransactions = 
            "2025-01-15 10:30:00 | DEPOSIT | BWP 1000.00 | Balance: BWP 1000.00 | Initial deposit\n" +
            "2025-01-20 14:15:00 | DEPOSIT | BWP 500.75 | Balance: BWP 1500.75 | Additional deposit\n" +
            "2025-01-25 09:45:00 | WITHDRAWAL | BWP 200.00 | Balance: BWP 1300.75 | Cash withdrawal\n" +
            "2025-02-01 08:00:00 | INTEREST | BWP 7.50 | Balance: BWP 1308.25 | Monthly interest";
        
        transactionHistoryView.transactionsArea.setText(sampleTransactions);
        transactionHistoryView.messageLabel.setText("Transaction history loaded");
        transactionHistoryView.messageLabel.setStyle("-fx-text-fill: green;");
    }
    
    public void refreshAccountData() {
        loadAccountData();
    }
    
    // Call this when returning to deposit/withdraw views
    public void onViewActivated() {
        loadAccountData();
    }
}