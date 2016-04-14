package CarRental.Controller;

import CarRental.Model.Body;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-04-08.
 */
public class AddBodyController implements Initializable {

    @FXML
    TextField bodyTextField;

    @FXML
    Pane addBodyPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) addBodyPane.getScene().getWindow();
        stage.close();
    }

    public void addNewBody(ActionEvent actionEvent) {
        if(!bodyTextField.equals("")){
            Body body = new Body().create(bodyTextField.getText());
        }
    }
}
