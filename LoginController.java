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
    
    UserManager.User user = UserManager.authenticate(username, password);
    
    if (user != null) {
        // CLEAR MESSAGE AFTER SUCCESSFUL LOGIN
        view.messageLabel.setText("Login successful! Welcome, " + user.getDisplayName());
        view.messageLabel.setStyle("-fx-text-fill: green;");
        
        view.usernameField.clear();
        view.passwordField.clear();
        
        // Pass both customer ID and username to BankingApp
        bankingApp.handleSuccessfulLogin(user.getCustomerId(), username);
        
        // CLEAR THE MESSAGE AFTER A DELAY SO IT DOESN'T SHOW ON LOGOUT
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    javafx.application.Platform.runLater(() -> {
                        view.messageLabel.setText("");
                    });
                }
            }, 
            3000 // Clear after 3 seconds
        );
        
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