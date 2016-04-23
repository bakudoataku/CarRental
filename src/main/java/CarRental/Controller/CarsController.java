package CarRental.Controller;

import CarRental.Model.Body;
import CarRental.Model.Brand;
import CarRental.Model.Car;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import orm.Model;

import java.net.URL;
import java.util.HashMap;
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
    private TableColumn<CarEntity, String> body;
    @FXML
    private TableColumn<CarEntity, String> brand;
    @FXML
    private TableColumn<CarEntity, Float> price;
    @FXML
    private TableColumn<CarEntity, String> registration;

    private ObservableList<CarEntity> carEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<Model, Model> joins = new HashMap<>();
        joins.put(new Car(), new Body());
        joins.put(new Body(), new Brand());
        // List<Car> cars = (List<Car>) new Car().join(joins).all();  TODO: checke why double joins does not work.
        List<Car> cars = (List<Car>) new Car().all();
        cars.forEach(car -> carEntities.add(new CarEntity(car)));
        setCellFactories();
        availableCarsTableView.setItems(carEntities);
    }

    private void setCellFactories() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        body.setCellValueFactory(new PropertyValueFactory<>("body"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        registration.setCellValueFactory(new PropertyValueFactory<>("registration"));
    }

    ObservableList<CarEntity> getCarEntities() {
        return carEntities;
    }
}
