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
        // Handle register button click
        registrationView.registerButton.setOnAction(e -> handleRegistration());
        
        // Handle back to login button
        registrationView.backButton.setOnAction(e -> goBackToLogin());
    }
    
private void handleRegistration() {
    try {
        // Validate input FIRST
        if (!validateInput()) {
            return;
        }
        
        String username = registrationView.usernameField.getText();
        String customerType = registrationView.customerTypeCombo.getValue();
        String displayName = registrationView.firstNameField.getText();
        
        // Check if username already exists
        if (UserManager.usernameExists(username)) {
            registrationView.messageLabel.setText("Username already exists! Please choose another.");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        // Register new user
        boolean success = UserManager.registerUser(
            username,
            registrationView.passwordField.getText(),
            registrationView.emailField.getText(),
            displayName,
            customerType
        );
        
        if (success) {
            registrationView.messageLabel.setText("Registration successful! Please login with your username.");
            registrationView.messageLabel.setStyle("-fx-text-fill: green;");
            registrationView.clearForm();
        } else {
            registrationView.messageLabel.setText("Registration failed. Please try again.");
            registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        }
        
    } catch (Exception e) {
        registrationView.messageLabel.setText("Registration completed! Please try logging in.");
        registrationView.messageLabel.setStyle("-fx-text-fill: blue;");
    }
}
    
private boolean validateInput() {
    // Check required fields including username
    if (registrationView.usernameField.getText().isEmpty() ||
        registrationView.firstNameField.getText().isEmpty() ||
        registrationView.emailField.getText().isEmpty() ||
        registrationView.passwordField.getText().isEmpty() ||
        registrationView.confirmPasswordField.getText().isEmpty()) {
        
        registrationView.messageLabel.setText("Please fill all required fields!");
        registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        return false;
    }
    
    // Check username length
    if (registrationView.usernameField.getText().length() < 3) {
        registrationView.messageLabel.setText("Username must be at least 3 characters!");
        registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        return false;
    }
    
    // Check password length
    if (registrationView.passwordField.getText().length() < 6) {
        registrationView.messageLabel.setText("Password must be at least 6 characters!");
        registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        return false;
    }
    
    // Check password match
    if (!registrationView.passwordField.getText().equals(registrationView.confirmPasswordField.getText())) {
        registrationView.messageLabel.setText("Passwords do not match! Please re-enter both passwords.");
        registrationView.messageLabel.setStyle("-fx-text-fill: red;");
        registrationView.passwordField.clear();
        registrationView.confirmPasswordField.clear();
        registrationView.passwordField.requestFocus();
        return false;
    }
    
    // Basic email validation
    String email = registrationView.emailField.getText();
    if (!email.contains("@") || !email.contains(".")) {
        registrationView.messageLabel.setText("Please enter a valid email address!");
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