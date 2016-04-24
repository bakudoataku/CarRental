package CarRental.Controller;

import CarRental.Model.Body;
import CarRental.Model.Brand;
import CarRental.Model.Entities.BodyEntity;
import CarRental.Model.Entities.BrandEntity;
import CarRental.Model.Entities.CarEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-04-08.
 */
public class AddBodyController implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public ComboBox<BrandEntity> brands;
    @FXML
    public Pane addBodyForm;
    @FXML
    public Pane addBodyPane;

    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();
    private ObservableList<BrandEntity> brandEntities = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Brand> brandList = (List<Brand>) new Brand().all();
        brandList.forEach(brand -> brandEntities.add(new BrandEntity(brand)));
        brands.setItems(brandEntities);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) addBodyPane.getScene().getWindow();
        stage.close();
    }

    public void addBodyAction(ActionEvent actionEvent) {
        if (!name.getText().equals("")) {
            Body body = new Body().create(name.getText(), brands.getSelectionModel().getSelectedItem().brandModel);
            if (body != null) {
                bodyEntities.add(new BodyEntity(body));
            }
            Stage stage = (Stage) addBodyForm.getScene().getWindow();
            stage.close();
        }
    }

    public void clearFormAction(ActionEvent actionEvent) {
        addBodyForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));

    }

    void setBodyEntities(ObservableList<BodyEntity> bodyEntities) {
        this.bodyEntities = bodyEntities;
    }

    public void addBrandAction(ActionEvent actionEvent) {
    }
}
