import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WithdrawView {

    public ComboBox<String> accountComboBox = new ComboBox<>();
    public TextField amountField = new TextField();
    public Button withdrawButton = new Button("Withdraw");
    public Button backButton = new Button("Back");
    public Label messageLabel = new Label();
    public Label currentBalanceLabel = new Label("Current Balance: --");
    public Label accountTypeLabel = new Label("Account Type: --");

    public Scene getScene() {

        VBox mainLayout = new VBox(25);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #f4f6f7;");

        Text title = new Text("Withdraw Funds");
        title.setFont(Font.font("Arial", 24));

        // Card design container
        VBox card = new VBox(20);
        card.setPadding(new Insets(25));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; "
                + "-fx-border-radius: 10; -fx-border-color: #dcdcdc; -fx-border-width: 1px;");
        card.setPrefWidth(380);

        // Account selector
        Label accLabel = new Label("Select Account");
        accLabel.setStyle("-fx-font-weight: bold;");
        accountComboBox.setPrefWidth(250);

        // Amount input
        Label amtLabel = new Label("Amount");
        amtLabel.setStyle("-fx-font-weight: bold;");
        amountField.setPromptText("Enter amount");

        // Extra info
        currentBalanceLabel.setStyle("-fx-text-fill:#2c3e50;");
        accountTypeLabel.setStyle("-fx-text-fill:#2c3e50;");

        // Put into layout
        card.getChildren().addAll(
            accLabel, accountComboBox,
            amtLabel, amountField,
            currentBalanceLabel,
            accountTypeLabel
        );

        // Buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        withdrawButton.setPrefWidth(140);
        withdrawButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        
        backButton.setPrefWidth(140);
        backButton.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white;");

        buttonBox.getChildren().addAll(withdrawButton, backButton);

        messageLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        mainLayout.getChildren().addAll(title, card, buttonBox, messageLabel);

        return new Scene(mainLayout, 480, 450);
    }
}
