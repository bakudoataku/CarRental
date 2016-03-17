package CarRental.Controller;

import CarRental.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by baku-desktop on 2016-03-16.
 *
 */
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    private DashboardController dashboardController;

    @FXML
    public void loginAction() {

        boolean authenticated = User.authenticateUser(loginField.getText(), passwordField.getText());

        if (authenticated) {
            dashboardController = new DashboardController();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            dashboardController.initialize(stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password or login is incorrect!");
            alert.showAndWait();
        }

    }
}
