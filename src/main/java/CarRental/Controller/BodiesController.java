package CarRental.Controller;

import CarRental.Model.Body;
import CarRental.Model.Brand;
import CarRental.Model.Car;
import CarRental.Model.Entities.BodyEntity;
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
public class BodiesController implements Initializable {


    @FXML
    private TableView<BodyEntity> bodiesTableView;
    @FXML
    private TableColumn<BodyEntity, Integer> id;
    @FXML
    private TableColumn<BodyEntity, String> name;
    @FXML
    private TableColumn<BodyEntity, String> brand;

    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<Model, Model> joins = new HashMap<>();
        joins.put(new Body(), new Brand());
        List<Body> bodies = (List<Body>) new Body().join(joins).all();
        bodies.forEach(body -> bodyEntities.add(new BodyEntity(body.id, body.name, body.getBrand().name)));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        bodiesTableView.setItems(bodyEntities);
    }
}
