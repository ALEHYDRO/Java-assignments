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
    accountTypeCombo.getItems().addAll("Savings Account", "Investment Account", "Cheque Account");
    accountTypeCombo.setValue("Savings Account");
    
    Label companyNameLabel = new Label("Company Name:");
    Label companyAddressLabel = new Label("Company Address:");
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    grid.setStyle("-fx-background-color: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
    
    Text scenetitle = new Text("Open New Account");
    scenetitle.setFont(Font.font("Tahoma", 20));
    scenetitle.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
    grid.add(scenetitle, 0, 0, 2, 1);
    
    grid.add(new Label("Account Type:"), 0, 1);
    grid.add(accountTypeCombo, 1, 1);
    
    grid.add(new Label("Initial Deposit:"), 0, 2);
    grid.add(initialDepositField, 1, 2);
    
    grid.add(companyNameLabel, 0, 3);
    grid.add(companyNameField, 1, 3);
    
    grid.add(companyAddressLabel, 0, 4);
    grid.add(companyAddressField, 1, 4);
    
    Label requirementsLabel = new Label();
    requirementsLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-weight: bold;");
    grid.add(requirementsLabel, 0, 5, 2, 1);
    
    messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
    grid.add(messageLabel, 0, 6, 2, 1);
    
    createButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
    backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
    
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createButton, backButton);
    grid.add(buttonBox, 0, 7, 2, 1);
    
    scene = new Scene(grid, 500, 450);
    
    // Style input fields
    accountTypeCombo.setStyle("-fx-padding: 5px;");
    initialDepositField.setStyle("-fx-padding: 5px;");
    companyNameField.setStyle("-fx-padding: 5px;");
    companyAddressField.setStyle("-fx-padding: 5px;");
    
    accountTypeCombo.setOnAction(e -> updateFormFields(companyNameLabel, companyAddressLabel, requirementsLabel));
    updateFormFields(companyNameLabel, companyAddressLabel, requirementsLabel);
}

private void updateFormFields(Label companyNameLabel, Label companyAddressLabel, Label requirementsLabel) {
    String accountType = accountTypeCombo.getValue();
    boolean isChequeAccount = accountType.equals("Cheque Account");
    
    companyNameLabel.setVisible(isChequeAccount);
    companyNameField.setVisible(isChequeAccount);
    companyAddressLabel.setVisible(isChequeAccount);
    companyAddressField.setVisible(isChequeAccount);
    
    if (!isChequeAccount) {
        companyNameField.clear();
        companyAddressField.clear();
    }
    
    switch (accountType) {
        case "Savings Account":
            requirementsLabel.setText("• No withdrawals allowed\n• 2.5% monthly interest");
            break;
        case "Investment Account":
            requirementsLabel.setText("• BWP 500 minimum deposit\n• 7.5% monthly interest\n• Withdrawals allowed");
            break;
        case "Cheque Account":
            requirementsLabel.setText("• For salary deposits\n• Company information required\n• Unlimited transactions");
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