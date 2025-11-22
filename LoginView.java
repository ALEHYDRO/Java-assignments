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
        
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
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
