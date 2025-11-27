import javafx.application.Application;
import javafx.stage.Stage;

public class BankingApp extends Application {
    private Stage primaryStage;
    private String currentCustomerId; // Track current user
    
    // Views
    private LoginView loginView;
    private DashboardView dashboardView;
    private RegistrationView registrationView;
    private AccountsView accountsView;
    private DepositView depositView;
    private WithdrawView withdrawView;
    private TransactionHistoryView transactionHistoryView;
    private OpenAccountView openAccountView; 
    
    // Controllers
    private LoginController loginController;
    private DashboardController dashboardController;
    private RegistrationController registrationController;
    private OpenAccountController openAccountController;
    public AccountController accountController;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Banking System");
            
            // Initialize sample accounts FIRST
            AccountManager.initializeSampleAccounts();
            
            // Create all views
            loginView = new LoginView();
            dashboardView = new DashboardView();
            registrationView = new RegistrationView();
            accountsView = new AccountsView();
            depositView = new DepositView();
            withdrawView = new WithdrawView();
            transactionHistoryView = new TransactionHistoryView();
            openAccountView = new OpenAccountView();
            
            // Create controllers
            loginController = new LoginController(loginView, this);
            dashboardController = new DashboardController(dashboardView, this);
            registrationController = new RegistrationController(registrationView, loginView, primaryStage);
            openAccountController = new OpenAccountController(openAccountView, dashboardView, primaryStage, this); // Added 'this'
            accountController = new AccountController(depositView, withdrawView, accountsView, transactionHistoryView, this);
            
            // Set up navigation from login to registration
            loginView.registerButton.setOnAction(e -> {
                primaryStage.setScene(registrationView.getScene());
            });
            
            // Start with login screen
            primaryStage.setScene(loginView.getScene());
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String currentUsername;
    
    // SESSION MANAGEMENT METHODS
    
    /**
     * Check if a user is currently logged in
     */
    public boolean isUserLoggedIn() {
        return currentCustomerId != null && !currentCustomerId.isEmpty();
    }
    /**
     * Handle logout - clear user session
     */
    public void logout() {
        this.currentCustomerId = null;
        this.currentUsername = null;
        
        // CLEAR ALL MESSAGES
        if (loginView != null) loginView.messageLabel.setText("");
        if (dashboardView != null) dashboardView.messageLabel.setText("");
        
        if (accountController != null) {
        accountController.clearCurrentUser();
    }

        // GO BACK TO LOGIN SCREEN
        showLoginView();
    }

    /**
     * Get current customer ID
     */
    public String getCurrentCustomerId() {
        return currentCustomerId;
    }

    /**
     * Get current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Refresh accounts view with current user's data
     */
    public void refreshAccountsView() {
        if (accountsView != null && isUserLoggedIn()) {
            // Check if AccountsView has the loadAccountsForUser method
            // If not, we'll need to call the accountController
            accountController.refreshAccountData();
        }
    }
    
    // NEW: Handle successful login
    public void handleSuccessfulLogin(String customerId, String username) {
        this.currentCustomerId = customerId;
        this.currentUsername = username;
        
        // SET WELCOME MESSAGE ON DASHBOARD
        dashboardView.welcomeLabel.setText("Welcome " + username + "!");
        dashboardView.messageLabel.setText("Login successful! What would you like to do today?");
        
        showDashboard();
    }
    
    // NEW: Handle logout
    public void handleLogout() {
        this.currentCustomerId = null;
        accountController.clearCurrentUser();
        showLoginView();
    }
    
    // Navigation methods
    public void showDashboard() {
        primaryStage.setScene(dashboardView.getScene());
    }
    
    public void showLoginView() {
        primaryStage.setScene(loginView.getScene());
    }
    
    public void showAccountsView() {
        accountController.refreshAccountData();
        primaryStage.setScene(accountsView.getScene());
    }
    
    public void showDepositView() {
        accountController.refreshAccountData();
        primaryStage.setScene(depositView.getScene());
    }
    
    public void showWithdrawView() {
        accountController.refreshAccountData();
        primaryStage.setScene(withdrawView.getScene());
    }
    
    public void showTransactionHistoryView() {
        accountController.refreshAccountData();
        primaryStage.setScene(transactionHistoryView.getScene());
    }
    
    public void showRegistrationView() {
        primaryStage.setScene(registrationView.getScene());
    }
    
    public void showOpenAccountView() {
        primaryStage.setScene(openAccountView.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}