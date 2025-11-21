package net.javaguids.popin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.javaguids.popin.services.AuthService;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField roleField;

    @FXML
    private Label errorLabel;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleField.getText();

        boolean success = authService.registerUser(username, password, role);

        if (!success) {
            errorLabel.setText("Invalid fields or user already exists.");
            return;
        }

        // Go back to login screen
        goToLogin();
    }

    @FXML
    private void goToLogin() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();

            var loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/net/javaguids/popin/views/login.fxml")
            );
            Stage loginStage = new Stage();
            loginStage.setScene(new javafx.scene.Scene(loader.load()));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
