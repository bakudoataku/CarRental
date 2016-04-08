package CarRental.Controller;

import CarRental.Model.Entities.CarEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private ObservableList<CarEntity> carEntities;

    void setCarEntities(ObservableList<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    ObservableList<CarEntity> getCarEntities() {
        return carEntities;
    }

    public void addBrandAction(ActionEvent actionEvent) {

    }

    public void addBodyAction(ActionEvent actionEvent) {

    }
}
