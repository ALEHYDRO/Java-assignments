import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DashboardView {
    public Button viewAccountsButton = new Button("View My Accounts");
    public Button depositButton = new Button("Make a Deposit");
    public Button withdrawButton = new Button("Make a Withdrawal");
    public Button openAccountButton = new Button("Open New Account");
    public Button transactionHistoryButton = new Button("View Transaction History");
    public Button logoutButton = new Button("Logout");
    public Label welcomeLabel = new Label("Welcome to Your Banking Dashboard!");
    public Label messageLabel = new Label();
    
    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30, 50, 50, 50));
        mainLayout.setStyle("-fx-background-color: #ecf0f1;");
        
        Text title = new Text("Banking System");
        title.setFont(Font.font(24));
        title.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        
        welcomeLabel.setFont(Font.font(16));
        welcomeLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        messageLabel.setStyle("-fx-text-fill: #3498db;");
        
        Button[] buttons = {viewAccountsButton, depositButton, withdrawButton, 
                           openAccountButton, transactionHistoryButton, logoutButton};
        
        for (Button btn : buttons) {
            btn.setPrefWidth(200);
            btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-background-radius: 5; -fx-padding: 10px; -fx-font-size: 14px;");
        }
        
        logoutButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; " +
                             "-fx-background-radius: 5; -fx-padding: 10px; -fx-font-size: 14px;");
        
        HBox firstButtonRow = new HBox(15);
        firstButtonRow.setAlignment(Pos.CENTER);
        HBox secondButtonRow = new HBox(15);
        secondButtonRow.setAlignment(Pos.CENTER);
        HBox thirdButtonRow = new HBox(15);
        thirdButtonRow.setAlignment(Pos.CENTER);
        
        firstButtonRow.getChildren().addAll(viewAccountsButton, depositButton);
        secondButtonRow.getChildren().addAll(withdrawButton, openAccountButton);
        thirdButtonRow.getChildren().addAll(transactionHistoryButton, logoutButton);
        
        mainLayout.getChildren().addAll(
            title, welcomeLabel, firstButtonRow, secondButtonRow, thirdButtonRow, messageLabel
        );
        
        return new Scene(mainLayout, 600, 500);
    }
}