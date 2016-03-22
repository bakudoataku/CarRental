package CarRental.Controller;

import CarRental.Model.Car;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bartosz on 18.03.2016.
 */
public class CarsController implements Initializable {


    @FXML
    private TableView<CarEntity> availableCarsTableView;
    @FXML
    private TableColumn<CarEntity, Integer> id;
    @FXML
    private TableColumn<CarEntity, String> model;
    @FXML
    private TableColumn<CarEntity, Float> price;
    @FXML
    private TableColumn<CarEntity, String> registration;

    private ObservableList<CarEntity> carEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Car> cars = (List<Car>) new Car().all();
        cars.forEach(car -> carEntities.add(new CarEntity(car.id, car.model, car.price, car.registration)));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        registration.setCellValueFactory(new PropertyValueFactory<>("registration"));
        availableCarsTableView.setItems(carEntities);
    }
}
