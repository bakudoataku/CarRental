package CarRental.Controller;

import CarRental.Model.Body;
import CarRental.Model.Car;
import CarRental.Model.Entities.BodyEntity;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-27.
 */
public class AddCarController implements Initializable {

    @FXML
    public TextField id;
    @FXML
    public ComboBox<BodyEntity> bodies;
    @FXML
    public TextField registration;
    @FXML
    public TextField price;
    @FXML
    public Pane addCarForm;

    private ObservableList<CarEntity> carEntities = FXCollections.observableArrayList();
    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Body> bodiesList = (List<Body>) new Body().all();
        bodiesList.forEach(body -> bodyEntities.add(new BodyEntity(body)));

        bodies.setItems(bodyEntities);
    }

    ObservableList<CarEntity> getCarEntities() {
        return carEntities;
    }

    void setCarEntities(ObservableList<CarEntity> carEntities) {
        this.carEntities = carEntities;
    }

    public void addBodyAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/View/fxml/AddBodyView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add new body");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFormAction(ActionEvent actionEvent) {
        addCarForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));
    }

    public void addCarAction(ActionEvent actionEvent) {
        if (addCarForm.getChildren().stream().filter(node -> node instanceof TextField).noneMatch(node -> ((TextField) node).getText().equals(""))) {
            Car car = new Car().create(Float.parseFloat(price.getText()), registration.getText(), bodies.getSelectionModel().getSelectedItem().bodyModel);
            if (car != null) {
                carEntities.add(new CarEntity(car));
            }
            Stage stage = (Stage) addCarForm.getScene().getWindow();
            stage.close();
        }
    }
}
