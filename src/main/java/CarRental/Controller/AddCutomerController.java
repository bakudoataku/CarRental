package CarRental.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-22.
 */
public class AddCutomerController implements Initializable {

    /*@FXML public TextField firstNameTextField;
    @FXML public TextField lastNameTextField;
    @FXML public TextField peselTextField;
    @FXML public TextField licenseTextField;
    @FXML public TextField streetTextField;
    @FXML public TextField cityTextField;
    @FXML public TextField zipTextField;*/
    @FXML public Pane addCustomerForm;

    public void clearFormAction(){
        for(Node node: addCustomerForm.getChildren()){
            if (node instanceof TextField){
                ((TextField) node).setText("");
            }
        }
    }

    public void addCustomerAction(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
