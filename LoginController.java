public class LoginController {
    private LoginView view;
    private BankingApp bankingApp;  // Add this field
    
    // Update constructor to accept BankingApp
    public LoginController(LoginView view, BankingApp bankingApp) {
        this.view = view;
        this.bankingApp = bankingApp;  // Store the reference
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        view.loginButton.setOnAction(e -> handleLogin());
        view.registerButton.setOnAction(e -> handleRegister());
        
        // Enter key support for login
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
        
        // Simple authentication
        if (username.equals("admin") && password.equals("password")) {
            view.messageLabel.setText("Login successful! Redirecting...");
            view.messageLabel.setStyle("-fx-text-fill: green;");
            
            // Clear fields
            view.usernameField.clear();
            view.passwordField.clear();
            
            // Navigate to dashboard
            bankingApp.showDashboard();  // Use the bankingApp reference
            
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