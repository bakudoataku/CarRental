package CarRental.Controller;

import CarRental.Model.Entities.CarEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-17.
 */
public class DashboardController implements Initializable {

    @FXML
    private Button addCarButton;
    @FXML
    private TableView<CarEntity> availableCarsTableView;
    @FXML
    private TableColumn<CarEntity, String> columnId;
    @FXML
    private TableColumn<CarEntity, String> columnModel;
    @FXML
    private TableColumn<CarEntity, String> columnPrice;
    @FXML
    private TableColumn<CarEntity, String> columnRegistration;
    @FXML
    private GridPane customers;
    @FXML
    private CustomersController customersController;
    private ObservableList<CarEntity> carEntities;

    public void addCustomerAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/View/fxml/addCustomerView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AddCustomerController addCustomerController = loader.getController();
            addCustomerController.setCustomerEntities(customersController.getCustomerEntities());
            stage.setTitle("Add new customer");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCarAction(ActionEvent actionEvent) {
    }

    public void rentCarAction(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
