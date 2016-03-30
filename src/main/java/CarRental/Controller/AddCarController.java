package CarRental.Controller;

import CarRental.Model.Entities.BodyEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-27.
 */
public class AddCarController implements Initializable{

    @FXML ChoiceBox bodySelect;
    @FXML TextField id;
    @FXML TextField price;

    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
