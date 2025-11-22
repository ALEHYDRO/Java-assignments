import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OpenAccountView {
    public ComboBox<String> accountTypeCombo = new ComboBox<>();
    public TextField initialDepositField = new TextField();
    public TextField companyNameField = new TextField();
    public TextField companyAddressField = new TextField();
    public Button createButton = new Button("Create Account");
    public Button backButton = new Button("Back to Dashboard");
    public Label messageLabel = new Label();
    
    private Scene scene;
    
    public OpenAccountView() {
        setupUI();
    }
    
private void setupUI() {
    // Account types
    accountTypeCombo.getItems().addAll("Savings Account", "Investment Account", "Cheque Account");
    accountTypeCombo.setValue("Savings Account");
    
    // Create company labels and fields
    Label companyNameLabel = new Label("Company Name:");
    Label companyAddressLabel = new Label("Company Address:");
    
    // Form setup
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    
    Text scenetitle = new Text("Open New Account");
    scenetitle.setFont(Font.font("Tahoma", 20));
    grid.add(scenetitle, 0, 0, 2, 1);
    
    grid.add(new Label("Account Type:"), 0, 1);
    grid.add(accountTypeCombo, 1, 1);
    
    grid.add(new Label("Initial Deposit:"), 0, 2);
    grid.add(initialDepositField, 1, 2);
    
    // Company info - we'll control visibility
    grid.add(companyNameLabel, 0, 3);
    grid.add(companyNameField, 1, 3);
    
    grid.add(companyAddressLabel, 0, 4);
    grid.add(companyAddressField, 1, 4);
    
    // Requirements info
    Label requirementsLabel = new Label();
    requirementsLabel.setStyle("-fx-text-fill: blue;");
    grid.add(requirementsLabel, 0, 5, 2, 1);
    
    grid.add(messageLabel, 0, 6, 2, 1);
    
    // Buttons
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createButton, backButton);
    grid.add(buttonBox, 0, 7, 2, 1);
    
    scene = new Scene(grid, 500, 450);
    
    // Update form when account type changes
    accountTypeCombo.setOnAction(e -> updateFormFields(companyNameLabel, companyAddressLabel, requirementsLabel));
    
    // Set initial state
    updateFormFields(companyNameLabel, companyAddressLabel, requirementsLabel);
}

private void updateFormFields(Label companyNameLabel, Label companyAddressLabel, Label requirementsLabel) {
    String accountType = accountTypeCombo.getValue();
    boolean isChequeAccount = accountType.equals("Cheque Account");
    
    // Show/hide company fields only for Cheque accounts
    companyNameLabel.setVisible(isChequeAccount);
    companyNameField.setVisible(isChequeAccount);
    companyAddressLabel.setVisible(isChequeAccount);
    companyAddressField.setVisible(isChequeAccount);
    
    // Clear company fields when not needed
    if (!isChequeAccount) {
        companyNameField.clear();
        companyAddressField.clear();
    }
    
    // Update requirements message
    switch (accountType) {
        case "Savings Account":
            requirementsLabel.setText("• No withdrawals allowed\n• 0.05% monthly interest");
            break;
        case "Investment Account":
            requirementsLabel.setText("• BWP 500 minimum deposit\n• 5% monthly interest\n• Withdrawals allowed");
            break;
        case "Cheque Account":
            requirementsLabel.setText("• For salary deposits\n• Company information required\n• Unlimited transactions");
            break;
    }
}

private void updateFormFields() {
    String accountType = accountTypeCombo.getValue();
    
    // Show company fields only for Cheque accounts
    boolean isChequeAccount = accountType.equals("Cheque Account");
    
    // Get the labels by index (they are at row 3 and 4)
    GridPane grid = (GridPane) scene.getRoot();
    
    // Show/hide company name field and label
    grid.getChildren().get(6).setVisible(isChequeAccount);  // Company Name label
    grid.getChildren().get(7).setVisible(isChequeAccount);  // Company Name field
    grid.getChildren().get(8).setVisible(isChequeAccount);  // Company Address label  
    grid.getChildren().get(9).setVisible(isChequeAccount);  // Company Address field
    
    // Update requirements message
    Label requirementsLabel = (Label) grid.getChildren().get(10);
    switch (accountType) {
        case "Savings Account":
            requirementsLabel.setText("Requirements: No withdrawals allowed, 0.05% monthly interest");
            break;
        case "Investment Account":
            requirementsLabel.setText("Requirements: BWP 500 minimum deposit, 5% monthly interest");
            break;
        case "Cheque Account":
            requirementsLabel.setText("Requirements: For salary deposits - company information required");
            break;
    }
}
    
    public Scene getScene() {
        return scene;
    }
    
    public void clearForm() {
        initialDepositField.clear();
        companyNameField.clear();
        companyAddressField.clear();
        accountTypeCombo.setValue("Savings Account");
        messageLabel.setText("");
    }
}
