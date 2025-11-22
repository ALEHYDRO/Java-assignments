import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TransactionHistoryView {
    
    // Public fields for controller access
    public ComboBox<String> accountComboBox = new ComboBox<>();
    public DatePicker fromDatePicker = new DatePicker();
    public DatePicker toDatePicker = new DatePicker();
    public Button searchButton = new Button("Search Transactions");
    public Button backButton = new Button("Back to Dashboard");
    public Label messageLabel = new Label();
    
    // Table for transactions (would use TableView in real implementation)
    public TextArea transactionsArea = new TextArea();
    
    public Scene getScene() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));
        
        // Title
        Text title = new Text("Transaction History");
        title.setFont(Font.font(20));
        
        // Search Criteria Section
        VBox searchBox = new VBox(15);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(20));
        searchBox.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px; -fx-background-color: #f9f9f9;");
        searchBox.setPrefWidth(400);
        
        // Account selection
        HBox accountRow = new HBox(10);
        accountRow.setAlignment(Pos.CENTER_LEFT);
        accountComboBox.setPromptText("Select Account");
        accountComboBox.setPrefWidth(200);
        accountRow.getChildren().addAll(new Label("Account:"), accountComboBox);
        
        // Date range
        HBox dateRow = new HBox(10);
        dateRow.setAlignment(Pos.CENTER_LEFT);
        fromDatePicker.setPromptText("From Date");
        toDatePicker.setPromptText("To Date");
        dateRow.getChildren().addAll(
            new Label("Date Range:"), fromDatePicker, new Label("to"), toDatePicker
        );
        
        // Search buttons - REMOVED exportButton
        HBox searchButtons = new HBox(10);
        searchButtons.setAlignment(Pos.CENTER);
        searchButtons.getChildren().addAll(searchButton); // Only search button
        
        searchBox.getChildren().addAll(accountRow, dateRow, searchButtons);
        
        // Transactions Display
        VBox transactionsBox = new VBox(10);
        transactionsBox.setAlignment(Pos.CENTER);
        transactionsBox.setPadding(new Insets(10));
        
        Label transactionsLabel = new Label("Transaction History");
        transactionsLabel.setStyle("-fx-font-weight: bold;");
        
        transactionsArea.setPrefSize(450, 250);
        transactionsArea.setEditable(false);
        transactionsArea.setPromptText("Transaction history will appear here...");
        transactionsArea.setStyle("-fx-font-family: 'Monospace';");
        
        transactionsBox.getChildren().addAll(transactionsLabel, transactionsArea);
        
        // Navigation buttons
        HBox navButtons = new HBox(15);
        navButtons.setAlignment(Pos.CENTER);
        navButtons.getChildren().addAll(backButton);
        
        // Message area
        messageLabel.setStyle("-fx-text-fill: #3498db;");
        
        // Add everything to main layout
        mainLayout.getChildren().addAll(
            title, searchBox, transactionsBox, navButtons, messageLabel
        );
        
        return new Scene(mainLayout, 550, 600);
    }
}