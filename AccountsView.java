import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class AccountsView {
    public Button refreshButton = new Button("Refresh");
    public Button backButton = new Button("Back to Dashboard");
    public Label totalBalanceLabel = new Label("Total Balance: BWP 0.00");
    public Label messageLabel = new Label();
    public VBox accountsContainer = new VBox(10);
    
    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        
        Text title = new Text("My Accounts");
        title.setFont(Font.font(20));
        
        totalBalanceLabel.setFont(Font.font(16));
        totalBalanceLabel.setTextFill(Color.GREEN);
        totalBalanceLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10px; -fx-border-color: #27ae60; -fx-border-width: 2px;");
        
        accountsContainer.setAlignment(Pos.TOP_CENTER);
        accountsContainer.setPadding(new Insets(20));
        accountsContainer.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: #ecf0f1;");
        accountsContainer.setPrefWidth(400);
        
        Label sampleAccount = new Label("No accounts found. Accounts will appear here.");
        sampleAccount.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic;");
        accountsContainer.getChildren().add(sampleAccount);
        
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(refreshButton, backButton);
        
        messageLabel.setStyle("-fx-text-fill: #3498db;");
        
        mainLayout.getChildren().addAll(
            title, totalBalanceLabel, accountsContainer, buttonBox, messageLabel
        );
        
        return new Scene(mainLayout, 500, 500);
    }
    
    public void addAccountDisplay(String accountNumber, String accountType, double balance) {
        HBox accountBox = new HBox(20);
        accountBox.setAlignment(Pos.CENTER_LEFT);
        accountBox.setStyle("-fx-border-color: #95a5a6; -fx-border-width: 1px; -fx-padding: 10px; -fx-background-color: white;");
        accountBox.setPrefWidth(350);
        
        VBox accountInfo = new VBox(5);
        Label accNumber = new Label("Account: " + accountNumber);
        Label accType = new Label("Type: " + accountType);
        Label accBalance = new Label("Balance: BWP " + String.format("%.2f", balance));
        accBalance.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        accountInfo.getChildren().addAll(accNumber, accType, accBalance);
        accountBox.getChildren().add(accountInfo);
        
        accountsContainer.getChildren().add(accountBox);
    }
}
