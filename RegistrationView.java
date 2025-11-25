import java.lang.classfile.Label;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RegistrationView {
    public TextField usernameField = new TextField();
    public TextField emailField = new TextField();
    public TextField phoneField = new TextField();
    public TextArea addressArea = new TextArea();
    public PasswordField passwordField = new PasswordField();
    public PasswordField confirmPasswordField = new PasswordField();
    public ComboBox<String> customerTypeCombo = new ComboBox<>();
    public Button registerButton = new Button("Register");
    public Button backButton = new Button("Back to Login");
    public Label messageLabel = new Label();
    public TextField firstNameField = new TextField();
    public TextField lastNameField = new TextField();
    public TextField companyNameField = new TextField();
    public TextField companyRegNumberField = new TextField();
    private Label nameLabel = new Label("First Name:");
    private Label additionalNameLabel = new Label("Last Name:");
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
        grid.setStyle("-fx-background-color: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        
        scenetitle = new Text("Register Individual Profile");
        scenetitle.setFont(Font.font("Tahoma", 20));
        scenetitle.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        grid.add(scenetitle, 0, 0, 2, 1);
        
        grid.add(new Label("Customer Type:"), 0, 1);
        grid.add(customerTypeCombo, 1, 1);
        
        grid.add(new Label("Username:*"), 0, 2);
        grid.add(usernameField, 1, 2);
        
        grid.add(nameLabel, 0, 3);
        grid.add(firstNameField, 1, 3);
        
        grid.add(additionalNameLabel, 0, 4);
        grid.add(lastNameField, 1, 4);
        
        Label companyRegLabel = new Label("Company Registration:");
        grid.add(companyRegLabel, 0, 5);
        grid.add(companyRegNumberField, 1, 5);
        companyRegNumberField.setVisible(false);
        companyRegLabel.setVisible(false);
        
        grid.add(new Label("Email:*"), 0, 6);
        grid.add(emailField, 1, 6);
        
        grid.add(new Label("Phone:"), 0, 7);
        grid.add(phoneField, 1, 7);
        
        grid.add(new Label("Address:"), 0, 8);
        grid.add(addressArea, 1, 8);
        addressArea.setPrefRowCount(3);
        
        grid.add(new Label("Password:*"), 0, 9);
        grid.add(passwordField, 1, 9);
        
        grid.add(new Label("Confirm Password:*"), 0, 10);
        grid.add(confirmPasswordField, 1, 10);
        
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        grid.add(messageLabel, 0, 11, 2, 1);
        
        registerButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(registerButton, backButton);
        grid.add(buttonBox, 0, 12, 2, 1);
        
        scene = new Scene(grid, 500, 650);
        
        customerTypeCombo.setOnAction(e -> updateFormForCustomerType());
        
        // Style all input fields
        TextField[] fields = {usernameField, emailField, phoneField, firstNameField, lastNameField, companyNameField, companyRegNumberField};
        for (TextField field : fields) {
            field.setStyle("-fx-padding: 5px;");
        }
        passwordField.setStyle("-fx-padding: 5px;");
        confirmPasswordField.setStyle("-fx-padding: 5px;");
        addressArea.setStyle("-fx-padding: 5px;");
        customerTypeCombo.setStyle("-fx-padding: 5px;");
    }
    
    private void updateFormForCustomerType() {
        String customerType = customerTypeCombo.getValue();
        boolean isCompany = customerType.equals("Company");
        
        scenetitle.setText(isCompany ? "Register Company Profile" : "Register Individual Profile");
        nameLabel.setText(isCompany ? "Company Name:*" : "First Name:*");
        additionalNameLabel.setText(isCompany ? "Contact Person:" : "Last Name:");
        
        lastNameField.setVisible(!isCompany);
        additionalNameLabel.setVisible(!isCompany);
        
        companyRegNumberField.setVisible(isCompany);
        grid.getChildren().get(10).setVisible(isCompany);
        
        if (isCompany) {
            lastNameField.clear();
        } else {
            companyRegNumberField.clear();
        }
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void clearForm() {
        usernameField.clear();
        firstNameField.clear();
        lastNameField.clear();
        companyNameField.clear();
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