import javafx.stage.Stage;

public class RegistrationController {
    private RegistrationView registrationView;
    private LoginView loginView;
    private Stage primaryStage;
    
    public RegistrationController(RegistrationView registrationView, LoginView loginView, Stage primaryStage) {
        this.registrationView = registrationView;
        this.loginView = loginView;
        this.primaryStage = primaryStage;
        setupHandlers();
    }
    
    private void setupHandlers() {
        registrationView.registerButton.setOnAction(e -> handleRegistration());
        registrationView.backButton.setOnAction(e -> goBackToLogin());
    }
    
    private void handleRegistration() {
        try {
            if (!validateInput()) {
                return;
            }
            
            String username = registrationView.usernameField.getText();
            String customerType = registrationView.customerTypeCombo.getValue();
            String displayName = registrationView.firstNameField.getText();
            
            if (UserManager.usernameExists(username)) {
                registrationView.messageLabel.setText("❌ Username already exists! Please choose another.");
                registrationView.messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            
            boolean success = UserManager.registerUser(
                username,
                registrationView.passwordField.getText(),
                registrationView.emailField.getText(),
                displayName,
                customerType
            );
            
            if (success) {
    
    String confirmationMessage = "✅ REGISTRATION SUCCESSFUL!\n\n" +
                                "Username: " + username + "\n" +
                                "You can now login with your credentials.";
    
    registrationView.messageLabel.setText(confirmationMessage);
    registrationView.messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold; -fx-font-size: 14px;");
    
    // Clear form but keep message visible
    registrationView.usernameField.clear();
    registrationView.firstNameField.clear();
    registrationView.lastNameField.clear();
    registrationView.emailField.clear();
    registrationView.phoneField.clear();
    registrationView.addressArea.clear();
    registrationView.passwordField.clear();
    registrationView.confirmPasswordField.clear();
    
} else {
    registrationView.messageLabel.setText("❌ Registration failed. Please try again.");
    registrationView.messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
}
            
        } catch (Exception e) {
            registrationView.messageLabel.setText("Registration error. Please try again.");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    private boolean validateInput() {
        if (registrationView.usernameField.getText().isEmpty() ||
            registrationView.firstNameField.getText().isEmpty() ||
            registrationView.emailField.getText().isEmpty() ||
            registrationView.passwordField.getText().isEmpty() ||
            registrationView.confirmPasswordField.getText().isEmpty()) {
            
            registrationView.messageLabel.setText("❌ Please fill all required fields!");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        
        if (registrationView.usernameField.getText().length() < 3) {
            registrationView.messageLabel.setText("❌ Username must be at least 3 characters!");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        
        if (registrationView.passwordField.getText().length() < 6) {
            registrationView.messageLabel.setText("❌ Password must be at least 6 characters!");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        
        if (!registrationView.passwordField.getText().equals(registrationView.confirmPasswordField.getText())) {
            registrationView.messageLabel.setText("❌ Passwords do not match! Please re-enter both passwords.");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            registrationView.passwordField.clear();
            registrationView.confirmPasswordField.clear();
            registrationView.passwordField.requestFocus();
            return false;
        }
        
        String email = registrationView.emailField.getText();
        if (!email.contains("@") || !email.contains(".")) {
            registrationView.messageLabel.setText("❌ Please enter a valid email address!");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        
        return true;
    }
    
    private void goBackToLogin() {
        primaryStage.setScene(loginView.getScene());
        registrationView.clearForm();
    }
}