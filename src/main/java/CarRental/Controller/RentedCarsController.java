package CarRental.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

/**
 * Created by bartosz on 07/05/16.
 */
public class RentedCarsController {
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

    private ObservableList<RentedCarEntity> carEntities = FXCollections.observableArrayList();

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
