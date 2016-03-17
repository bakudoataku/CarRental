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
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by baku-desktop on 2016-03-17.
 */
public class DashboardController {

    @FXML Button addCarButton;
    @FXML TableView availableCarsTableView;
    private ObservableList<CarEntity> carEntityObservableList;


    public void addCustomerAction(ActionEvent actionEvent) {
    }

    public void addCarAction(ActionEvent actionEvent) {
    }

    public void rentCarAction(ActionEvent actionEvent) {
    }

    public void init(Stage stage)   {
        try {
            String fxmlFile = "/View/fxml/dashboard.fxml";
            Parent rootNode = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene dashboardScene = new Scene(rootNode);
            stage.setScene(dashboardScene);
            //addCarButton.setVisible(false);

        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("select id, model, price, registration from \"car\"");
            CarEntity carEntity;
            carEntityObservableList = FXCollections.observableArrayList();
            while(rs.next()){
                carEntity = new CarEntity();
                carEntity.id.set(rs.getInt("id"));
                carEntity.model.set(rs.getString("model"));
                carEntity.price.set(rs.getFloat("price"));
                carEntity.registration.set(rs.getString("registration"));

                carEntityObservableList.add(carEntity);
            }

            availableCarsTableView = new TableView();
        availableCarsTableView.setItems(carEntityObservableList);

        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
