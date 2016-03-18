package CarRental.Controller;

import CarRental.Model.DBConnector;
import CarRental.Model.Entities.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bartosz on 18.03.2016.
 *
 */
public class CarsController implements Initializable{


    @FXML
    private TableView<Car> availableCarsTableView;
    @FXML
    private TableColumn<Car, Integer> columnId;
    @FXML
    private TableColumn<Car, String> columnModel;
    @FXML
    private TableColumn<Car, Float> columnPrice;
    @FXML
    private TableColumn<Car, String> columnRegistration;

    private ObservableList<Car> carObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = DBConnector.find("car", new ArrayList<String>() {{
                add("id");
                add("model");
                add("price");
                add("registration");
            }});
            while (rs.next()) {
                Car car = new Car(rs.getInt("id"), rs.getString("model"), rs.getFloat("price"), rs.getString("registration"));
                carObservableList.add(car);
            }

            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            columnRegistration.setCellValueFactory(new PropertyValueFactory<>("registration"));
            availableCarsTableView.setItems(carObservableList);

        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
    }
}
