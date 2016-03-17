package CarRental.Controller;

import CarRental.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by baku-desktop on 2016-03-16.
 * Modified by sztosz on 2016-03-17.
 */
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    public void loginAction() {

        boolean authenticated = User.authenticateUser(loginField.getText(), passwordField.getText());

        if (authenticated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logged in");
            alert.setHeaderText(null);
            alert.setContentText("You have been successfully logged in!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password or login is incorrect!");
            alert.showAndWait();
        }

    }
}
