import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RegistrationView {
    // Common fields
    public TextField usernameField = new TextField();
    public TextField emailField = new TextField();
    public TextField phoneField = new TextField();
    public TextArea addressArea = new TextArea();
    public PasswordField passwordField = new PasswordField();
    public PasswordField confirmPasswordField = new PasswordField();
    public ComboBox<String> customerTypeCombo = new ComboBox<>();
    public Button registerButton = new Button("Register");
    public Button backButton = new Button("Back to Login");
    public javafx.scene.control.Label messageLabel = new javafx.scene.control.Label();
    
    // Individual-specific fields
    public TextField firstNameField = new TextField();
    public TextField lastNameField = new TextField();
    
    // Company-specific fields
    public TextField companyRegNumberField = new TextField();
    
    // Labels
    private javafx.scene.control.Label nameLabel = new javafx.scene.control.Label("First Name:");
    private javafx.scene.control.Label additionalNameLabel = new javafx.scene.control.Label("Last Name:");
    private javafx.scene.control.Label companyRegLabel = new javafx.scene.control.Label("Company Registration Number:");
    private Text scenetitle;
    
    private Scene scene;
    private GridPane grid;
    
    public RegistrationView() {
        setupUI();
    }
    
    private void setupUI() {
        customerTypeCombo.getItems().addAll("Individual", "Company");
        customerTypeCombo.setValue("Individual");
        
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #ecf0f1;");
        
        scenetitle = new Text("Register Individual Profile");
        scenetitle.setFont(Font.font("Tahoma", 20));
        scenetitle.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // Row 1: Customer Type
        grid.add(new javafx.scene.control.Label("Customer Type:"), 0, 1);
        grid.add(customerTypeCombo, 1, 1);
        
        // Row 2: Username
        grid.add(new javafx.scene.control.Label("Username:*"), 0, 2);
        grid.add(usernameField, 1, 2);
        
        // Row 3: First Name / Company Name
        grid.add(nameLabel, 0, 3);
        grid.add(firstNameField, 1, 3);
        
        // Row 4: Last Name / Contact Person
        grid.add(additionalNameLabel, 0, 4);
        grid.add(lastNameField, 1, 4);
        
        // Row 5: Company Registration (HIDDEN by default)
        grid.add(companyRegLabel, 0, 5);
        grid.add(companyRegNumberField, 1, 5);
        
        // Row 6: Email
        grid.add(new javafx.scene.control.Label("Email:*"), 0, 6);
        grid.add(emailField, 1, 6);
        
        // Row 7: Phone
        grid.add(new javafx.scene.control.Label("Phone:"), 0, 7);
        grid.add(phoneField, 1, 7);
        
        // Row 8: Address
        grid.add(new javafx.scene.control.Label("Address:"), 0, 8);
        grid.add(addressArea, 1, 8);
        addressArea.setPrefRowCount(2);
        
        // Row 9: Password
        grid.add(new javafx.scene.control.Label("Password:*"), 0, 9);
        grid.add(passwordField, 1, 9);
        
        // Row 10: Confirm Password
        grid.add(new javafx.scene.control.Label("Confirm Password:*"), 0, 10);
        grid.add(confirmPasswordField, 1, 10);
        
        // Row 11: Message
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        grid.add(messageLabel, 0, 11, 2, 1);
        
        // Row 12: Buttons
        registerButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(registerButton, backButton);
        grid.add(buttonBox, 0, 12, 2, 1);
        
        scene = new Scene(grid, 500, 550);
        
        customerTypeCombo.setOnAction(e -> updateFormForCustomerType());
        updateFormForCustomerType(); // Set initial state
    }
    
    private void updateFormForCustomerType() {
        String customerType = customerTypeCombo.getValue();
        boolean isCompany = customerType.equals("Company");
        
        scenetitle.setText(isCompany ? "Register Company Profile" : "Register Individual Profile");
        nameLabel.setText(isCompany ? "Company Name:*" : "First Name:*");
        additionalNameLabel.setText(isCompany ? "Contact Person:" : "Last Name:");
        
        // Show/hide appropriate fields
        if (isCompany) {
            // Show company fields, hide individual fields
            additionalNameLabel.setVisible(false);
            lastNameField.setVisible(false);
            companyRegLabel.setVisible(true);
            companyRegNumberField.setVisible(true);
        } else {
            // Show individual fields, hide company fields  
            additionalNameLabel.setVisible(true);
            lastNameField.setVisible(true);
            companyRegLabel.setVisible(false);
            companyRegNumberField.setVisible(false);
        }
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void clearForm() {
        usernameField.clear();
        firstNameField.clear();
        lastNameField.clear();
        companyRegNumberField.clear();
        emailField.clear();
        phoneField.clear();
        addressArea.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        customerTypeCombo.setValue("Individual");
        messageLabel.setText("");
        updateFormForCustomerType();
    }
}