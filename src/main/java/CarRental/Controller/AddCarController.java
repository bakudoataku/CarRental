package CarRental.Controller;

import CarRental.Model.Body;
import CarRental.Model.Car;
import CarRental.Model.Entities.BodyEntity;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-27.
 */
public class AddCarController implements Initializable {

    @FXML
    public TextField id;
    @FXML
    public ComboBox bodies;
    @FXML
    public TextField registration;
    @FXML
    public TextField price;
    @FXML
    public Pane addCarForm;

    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();
    private ObservableList<CarEntity> carEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    ObservableList<CarEntity> getCarEntities() {
        return carEntities;
    }

    void setCarEntities(ObservableList<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public void addBrandAction(ActionEvent actionEvent) {

    }

    public void addBodyAction(ActionEvent actionEvent) {

    }

    public void addCustomerAction() {
        if (addCarForm.getChildren().stream().filter(node -> node instanceof TextField).noneMatch(node -> ((TextField) node).getText().equals(""))) {
            Car car = new Car().create(Float.parseFloat(price.getText()), registration.getText(), (Body) bodies.getValue());
            if (car != null) {
                carEntities.add(new CarEntity(car));
            }
        }
        Stage stage = (Stage) addCarForm.getScene().getWindow();
        stage.close();
    }

    public void clearFormAction(ActionEvent actionEvent) {
        addCarForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));
    }

    public void addCarAction(ActionEvent actionEvent) {

    }
}
