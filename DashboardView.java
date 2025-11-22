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
    public Label messageLabel = new Label(); // Added missing message label
    
    public Scene getScene() {
    
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30, 50, 50, 50));
        
        Text title = new Text("Banking System");
        title.setFont(Font.font(24));
        
        welcomeLabel.setFont(Font.font(16));
        welcomeLabel.setStyle("-fx-font-weight: bold;");
        
        messageLabel.setStyle("-fx-text-fill: blue;"); // Style for messages
        
        HBox firstButtonRow = new HBox(15);
        firstButtonRow.setAlignment(Pos.CENTER);
        
        HBox secondButtonRow = new HBox(15);
        secondButtonRow.setAlignment(Pos.CENTER);
        
        HBox thirdButtonRow = new HBox(15);
        thirdButtonRow.setAlignment(Pos.CENTER);
        
        viewAccountsButton.setPrefWidth(200);
        depositButton.setPrefWidth(200);
        withdrawButton.setPrefWidth(200);
        openAccountButton.setPrefWidth(200);
        transactionHistoryButton.setPrefWidth(200);
        logoutButton.setPrefWidth(200);
        
        firstButtonRow.getChildren().addAll(viewAccountsButton, depositButton);
        secondButtonRow.getChildren().addAll(withdrawButton, openAccountButton);
        thirdButtonRow.getChildren().addAll(transactionHistoryButton, logoutButton);
        
        mainLayout.getChildren().addAll(
            title, welcomeLabel, firstButtonRow, secondButtonRow, thirdButtonRow, messageLabel
        );
        
        return new Scene(mainLayout, 600, 500);
    }
}
