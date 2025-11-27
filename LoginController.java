public class LoginController {
    private LoginView view;
    private BankingApp bankingApp;
    
    public LoginController(LoginView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        view.loginButton.setOnAction(e -> handleLogin());
        view.registerButton.setOnAction(e -> handleRegister());
        view.passwordField.setOnAction(e -> handleLogin());
    }
    
    private void handleLogin() {
    String username = view.usernameField.getText();
    String password = view.passwordField.getText();
    
    if (username.isEmpty() || password.isEmpty()) {
        view.messageLabel.setText("Please enter both username and password");
        view.messageLabel.setStyle("-fx-text-fill: red;");
        return;
    }
    
    UserManager.User user = UserManager.authenticate(username, password);
    
    if (user != null) {
        // Clear message immediately
        view.messageLabel.setText("");
        view.usernameField.clear();
        view.passwordField.clear();
        
        // CRITICAL FIX: Set current user in AccountController
        if (bankingApp.accountController != null) {
            bankingApp.accountController.setCurrentUser(user.getCustomerId());
        }
        
        // Ensure sample accounts are created for admin
        AccountManager.ensureUserHasSampleAccounts(user.getCustomerId(), username);
        
        // Pass to BankingApp
        bankingApp.handleSuccessfulLogin(user.getCustomerId(), username);
        
    } else {
        view.messageLabel.setText("Invalid username or password");
        view.messageLabel.setStyle("-fx-text-fill: red;");
    }
}
    
    private void handleRegister() {
        view.messageLabel.setText("Registration feature coming soon!");
        view.messageLabel.setStyle("-fx-text-fill: blue;");
    }
}