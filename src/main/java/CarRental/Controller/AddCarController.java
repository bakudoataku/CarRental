package CarRental.Controller;

import CarRental.Model.Entities.BodyEntity;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-27.
 */
public class AddCarController implements Initializable{

    @FXML public TextField id;
    @FXML public ComboBox bodies;
    @FXML public TextField registration;
    @FXML public TextField price;
    @FXML public Pane addCarForm;

    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();
    private ObservableList<CarEntity> carEntities = FXCollections.observableArrayList();

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

    public void addCarAction(ActionEvent actionEvent) {
    }

    public void clearFormAction(ActionEvent actionEvent) {
        addCarForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));
    }
}
