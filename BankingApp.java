import javafx.application.Application;
import javafx.stage.Stage;

public class BankingApp extends Application {
    private Stage primaryStage;
    private DashboardController dashboardController;
    
    // Views
    private LoginView loginView;
    private DashboardView dashboardView;
    private DepositView depositView;
    private WithdrawView withdrawView;
    private AccountsView accountsView;
    private TransactionHistoryView transactionHistoryView;
    
    // Controllers
    private LoginController loginController;
    private AccountController accountController;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeViews();
        setupNavigation();
        showLoginView();
    }
    
    private void initializeViews() {
        // Create all views
        loginView = new LoginView();
        dashboardView = new DashboardView();
        depositView = new DepositView();
        withdrawView = new WithdrawView();
        accountsView = new AccountsView();
        transactionHistoryView = new TransactionHistoryView();
        
        // Create controllers
        loginController = new LoginController(loginView, this);
        accountController = new AccountController(depositView, withdrawView, accountsView, transactionHistoryView);
        
        // Populate sample data for demo
        populateSampleData();
    }
    
// Replace the entire setupNavigation() method with:
private void setupNavigation() {
    // Create dashboard controller to handle navigation
    new DashboardController(dashboardView, this);
    
    // Back button handlers (keep these)
    depositView.backButton.setOnAction(e -> showDashboard());
    withdrawView.backButton.setOnAction(e -> showDashboard());
    accountsView.backButton.setOnAction(e -> showDashboard());
    transactionHistoryView.backButton.setOnAction(e -> showDashboard());
}
    
    private void populateSampleData() {
        // Add sample accounts to combo boxes
        depositView.accountComboBox.getItems().addAll("SAV001 - Savings", "INV001 - Investment", "CHQ001 - Cheque");
        withdrawView.accountComboBox.getItems().addAll("SAV001 - Savings", "INV001 - Investment", "CHQ001 - Cheque");
        transactionHistoryView.accountComboBox.getItems().addAll("SAV001 - Savings", "INV001 - Investment", "CHQ001 - Cheque");
        
        // Set sample balance
        depositView.currentBalanceLabel.setText("Current Balance: BWP 1,500.75");
        withdrawView.currentBalanceLabel.setText("Current Balance: BWP 1,500.75");
        withdrawView.accountTypeLabel.setText("Account Type: Savings Account");
    }
    
    // Navigation methods
    public void showLoginView() {
        primaryStage.setScene(loginView.getScene());
        primaryStage.setTitle("Banking System - Login");
        primaryStage.show();
    }
    
    public void showDashboard() {
        dashboardView.welcomeLabel.setText("Welcome, Alex! 👋");
        dashboardView.messageLabel.setText("Last login: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
        primaryStage.setScene(dashboardView.getScene());
        primaryStage.setTitle("Banking System - Dashboard");
    }
    
    public void showDepositView() {
        primaryStage.setScene(depositView.getScene());
        primaryStage.setTitle("Banking System - Deposit Funds");
    }
    
    public void showWithdrawView() {
        primaryStage.setScene(withdrawView.getScene());
        primaryStage.setTitle("Banking System - Withdraw Funds");
    }
    
    public void showAccountsView() {
        // Refresh accounts data when view is shown
        accountController.handleRefreshAccounts();
        primaryStage.setScene(accountsView.getScene());
        primaryStage.setTitle("Banking System - My Accounts");
    }
    
    public void showTransactionHistoryView() {
        primaryStage.setScene(transactionHistoryView.getScene());
        primaryStage.setTitle("Banking System - Transaction History");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}