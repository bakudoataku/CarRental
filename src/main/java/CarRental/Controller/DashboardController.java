package CarRental.Controller;

import CarRental.Model.DBConnector;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by baku-desktop on 2016-03-17.
 */
public class DashboardController {

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

    @FXML
    private void initialize(){

        try {
            ResultSet rs = DBConnector.find("car", new ArrayList<String>() {{
                add("id");
                add("model");
                add("price");
                add("registration");
            }});

            carEntityObservableList = FXCollections.observableArrayList();
            while (rs.next()) {
                CarEntity carEntity = new CarEntity();
                carEntity.id.set(rs.getInt("id"));
                carEntity.model.set(rs.getString("model"));
                carEntity.price.set(rs.getFloat("price"));
                carEntity.registration.set(rs.getString("registration"));

                carEntityObservableList.add(carEntity);
            }

            columnId.setCellValueFactory(new PropertyValueFactory<>("columnId"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("columnModel"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("columnPrice"));
            columnRegistration.setCellValueFactory(new PropertyValueFactory<>("columnRegistration"));

            //Do rozkminienia dlaczego dane nie wyswietlaja sie w tabeli, choc sa przypisane
            availableCarsTableView.setItems(carEntityObservableList);
            availableCarsTableView.refresh();

        }catch(URISyntaxException | SQLException e){
            e.printStackTrace();
        }

    }

    public void initialize(Stage stage)   {

        try {
            String fxmlFile = "/View/fxml/dashboard.fxml";
            Parent rootNode = FXMLLoader.load(getClass().getResource(fxmlFile));

            Scene dashboardScene = new Scene(rootNode);
            stage.setScene(dashboardScene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
