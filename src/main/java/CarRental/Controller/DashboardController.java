package CarRental.Controller;

import CarRental.Model.Entities.CarEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-17.
 */
public class DashboardController implements Initializable {

    @FXML private Button addCarButton;
    @FXML private TableView<CarEntity> availableCarsTableView;
    @FXML private TableColumn<CarEntity, String> columnId;
    @FXML private TableColumn<CarEntity, String> columnModel;
    @FXML private TableColumn<CarEntity, String> columnPrice;
    @FXML private TableColumn<CarEntity, String> columnRegistration;
    private ObservableList<CarEntity> carEntityObservableList;


    public void addCustomerAction(ActionEvent actionEvent) {
    }

    public void addCarAction(ActionEvent actionEvent) {
    }

    public void rentCarAction(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
