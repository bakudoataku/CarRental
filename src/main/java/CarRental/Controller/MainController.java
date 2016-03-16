package CarRental.Controller;

import CarRental.Model.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
 */
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    public void loginAction() {

        try {
            byte[] passwordBytes = passwordField.getText().getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] encodedPassword  = md.digest(passwordBytes);

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < encodedPassword.length; i++) {
                String hex = Integer.toHexString(0xFF & encodedPassword[i]);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }

            Statement st = DBConnector.getInstance().createStatement();
            System.out.println(encodedPassword);
            System.out.println(sb.toString());
            ResultSet rs = st.executeQuery(String.format("SELECT Id FROM public.Users WHERE Login='%s' AND Password='%s'", loginField.getText(), sb.toString()));

            if(!rs.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password!");

                alert.showAndWait();
            }else System.out.println(rs.getString(1));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException | URISyntaxException e) {
            log.error("jeblo i nie dziala");
            e.printStackTrace();
        }

    }
}
