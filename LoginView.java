import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginView {
    public TextField usernameField = new TextField();
    public PasswordField passwordField = new PasswordField();
    public Button loginButton = new Button("Login");
    public Button registerButton = new Button("Register");
    public Label messageLabel = new Label();
    
    public Scene getScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // CHANGE BACK TO WHITE BACKGROUND
        grid.setStyle("-fx-background-color: white;");
        
        usernameField.setStyle("-fx-pref-width: 200px; -fx-padding: 8px;");
        passwordField.setStyle("-fx-pref-width: 200px; -fx-padding: 8px;");
        
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-padding: 8px;");
        registerButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-padding: 8px;");
        
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        
        // CREATE LABELS WITH DARK TEXT COLOR
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        userLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        passLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        
        grid.add(userLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(registerButton, 0, 2);
        grid.add(messageLabel, 0, 3, 2, 1);
        
        return new Scene(grid, 400, 300);
    }
    public Button getRegisterButton() {
        return registerButton;
    }
}