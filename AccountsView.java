import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import java.util.List;

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
        mainLayout.setStyle("-fx-background-color: #ecf0f1;");
        
        Text title = new Text("My Accounts");
        title.setFont(Font.font(20));
        title.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        
        totalBalanceLabel.setFont(Font.font(16));
        totalBalanceLabel.setTextFill(Color.GREEN);
        totalBalanceLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10px; -fx-border-color: #27ae60; -fx-border-width: 2px; -fx-background-color: white; -fx-background-radius: 5;");
        
        accountsContainer.setAlignment(Pos.TOP_CENTER);
        accountsContainer.setPadding(new Insets(20));
        accountsContainer.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: white; -fx-background-radius: 5;");
        accountsContainer.setPrefWidth(400);
        
        // Initial message
        Label initialMessage = new Label("Please login to access your accounts");
        initialMessage.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic;");
        accountsContainer.getChildren().add(initialMessage);
        
        refreshButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(refreshButton, backButton);
        
        messageLabel.setStyle("-fx-text-fill: #3498db;");
        
        mainLayout.getChildren().addAll(
            title, totalBalanceLabel, accountsContainer, buttonBox, messageLabel
        );
        
        return new Scene(mainLayout, 500, 500);
    }
    
    // ADD THIS METHOD TO LOAD ACCOUNTS
    public void loadAccountsForUser(String customerId) {
        accountsContainer.getChildren().clear();
        
        if (customerId == null) {
            Label loginMessage = new Label("Please login to access your accounts");
            loginMessage.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic;");
            accountsContainer.getChildren().add(loginMessage);
            totalBalanceLabel.setText("Total Balance: BWP 0.00");
            return;
        }
        
        // Get accounts for the user (you'll need to implement this in AccountManager)
        List<SimpleAccount> userAccounts = AccountManager.getCustomerAccounts(customerId);
        
        if (userAccounts == null || userAccounts.isEmpty()) {
            Label noAccounts = new Label("No accounts found. Open a new account to get started!");
            noAccounts.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic;");
            accountsContainer.getChildren().add(noAccounts);
            totalBalanceLabel.setText("Total Balance: BWP 0.00");
        } else {
            double total = 0;
            for (SimpleAccount account : userAccounts) {
                addAccountDisplay(account.getAccountNumber(), account.getAccountType(), account.getBalance());
                total += account.getBalance();
            }
            totalBalanceLabel.setText("Total Balance: BWP " + String.format("%.2f", total));
        }
    }
    
    public void addAccountDisplay(String accountNumber, String accountType, double balance) {
        HBox accountBox = new HBox(20);
        accountBox.setAlignment(Pos.CENTER_LEFT);
        accountBox.setStyle("-fx-border-color: #3498db; -fx-border-width: 2px; -fx-padding: 15px; -fx-background-color: white; -fx-background-radius: 5;");
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