import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TransactionHistoryView {
    
    public ComboBox<String> accountComboBox = new ComboBox<>();
    public DatePicker fromDatePicker = new DatePicker();
    public DatePicker toDatePicker = new DatePicker();
    public Button searchButton = new Button("Search Transactions");
    public Button backButton = new Button("Back to Dashboard");
    public Label messageLabel = new Label();
    public TextArea transactionsArea = new TextArea();
    
    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        
        Text title = new Text("Transaction History");
        title.setFont(Font.font(20));
        title.setStyle("-fx-fill: #2c3e50; -fx-font-weight: bold;");
        
        VBox searchBox = new VBox(15);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(20));
        searchBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: white; -fx-background-radius: 5;");
        searchBox.setPrefWidth(400);
        
        accountComboBox.setPromptText("Select Account");
        accountComboBox.setPrefWidth(200);
        accountComboBox.setStyle("-fx-padding: 5px;");
        fromDatePicker.setStyle("-fx-padding: 5px;");
        toDatePicker.setStyle("-fx-padding: 5px;");
        
        HBox accountRow = new HBox(10);
        accountRow.setAlignment(Pos.CENTER_LEFT);
        accountRow.getChildren().addAll(new Label("Account:"), accountComboBox);
        
        HBox dateRow = new HBox(10);
        dateRow.setAlignment(Pos.CENTER_LEFT);
        dateRow.getChildren().addAll(
            new Label("Date Range:"), fromDatePicker, new Label("to"), toDatePicker
        );
        
        searchButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        HBox searchButtons = new HBox(10);
        searchButtons.setAlignment(Pos.CENTER);
        searchButtons.getChildren().addAll(searchButton);
        
        searchBox.getChildren().addAll(accountRow, dateRow, searchButtons);
        
        VBox transactionsBox = new VBox(10);
        transactionsBox.setAlignment(Pos.CENTER);
        transactionsBox.setPadding(new Insets(10));
        
        Label transactionsLabel = new Label("Transaction History");
        transactionsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        transactionsArea.setPrefSize(450, 250);
        transactionsArea.setEditable(false);
        transactionsArea.setPromptText("Transaction history will appear here...");
        transactionsArea.setStyle("-fx-font-family: 'Monospace'; -fx-border-color: #bdc3c7;");
        
        transactionsBox.getChildren().addAll(transactionsLabel, transactionsArea);
        
        backButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 15px;");
        
        HBox navButtons = new HBox(15);
        navButtons.setAlignment(Pos.CENTER);
        navButtons.getChildren().addAll(backButton);
        
        messageLabel.setStyle("-fx-text-fill: #3498db;");
        
        mainLayout.getChildren().addAll(
            title, searchBox, transactionsBox, navButtons, messageLabel
        );
        
        return new Scene(mainLayout, 550, 600);
    }
}