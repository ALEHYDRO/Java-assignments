import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WithdrawView {
    public ComboBox<String> accountComboBox = new ComboBox<>();
    public TextField amountField = new TextField();
    public Button withdrawButton = new Button("Make Withdrawal");
    public Button backButton = new Button("Back to Dashboard");
    public Label messageLabel = new Label();
    public Label currentBalanceLabel = new Label("Current Balance: --");
    public Label accountTypeLabel = new Label("Account Type: --");
    
    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #ecf0f1;");
        
        Text title = new Text("Withdraw Funds");
        title.setFont(Font.font(20));
        title.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER_LEFT);
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(25));
        formGrid.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: white; -fx-background-radius: 5;");
        
        accountComboBox.setPromptText("Select Account");
        accountComboBox.setPrefWidth(250);
        accountComboBox.setStyle("-fx-padding: 5px;");
        amountField.setPromptText("Enter amount");
        amountField.setStyle("-fx-padding: 5px;");
        
        formGrid.add(new Label("Select Account:"), 0, 0);
        formGrid.add(accountComboBox, 1, 0);
        formGrid.add(new Label("Amount to Withdraw:"), 0, 1);
        formGrid.add(amountField, 1, 1);
        formGrid.add(new Label("BWP"), 2, 1);
        formGrid.add(currentBalanceLabel, 1, 2);
        formGrid.add(accountTypeLabel, 1, 3);
        
        withdrawButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(15);
        buttonGrid.add(withdrawButton, 0, 0);
        buttonGrid.add(backButton, 1, 0);
        
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        
        mainLayout.getChildren().addAll(
            title, formGrid, buttonGrid, messageLabel
        );
        
        return new Scene(mainLayout, 500, 450);
    }
}