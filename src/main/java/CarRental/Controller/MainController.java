package CarRental.Controller;

import CarRental.Model.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public MainController(){
        dashboardController = new DashboardController();
    }

    @FXML
    public void loginAction() {

        try {
            byte[] passwordBytes = passwordField.getText().getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] encodedPassword  = md.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte anEncodedPassword : encodedPassword) {
                String hex = Integer.toHexString(0xFF & anEncodedPassword);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            Statement st = DBConnector.getInstance().createStatement();

            ResultSet rs = st.executeQuery(String.format("SELECT Id FROM public.User WHERE Login='%s' AND password_hash='%s'", loginField.getText(), sb.toString()));

            if(!rs.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password!");

                alert.showAndWait();
            }else {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                dashboardController.init(stage);
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException | URISyntaxException e) {
            log.error("jeblo i nie dziala");
            e.printStackTrace();
        }

    }
}
