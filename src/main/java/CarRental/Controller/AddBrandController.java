package CarRental.Controller;

import CarRental.Model.Brand;
import CarRental.Model.Entities.BrandEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bartosz on 24.04.2016.
 */
public class AddBrandController implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public Pane addBrandForm;
    @FXML
    public Pane addBrandPane;

    private ObservableList<BrandEntity> brandEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) addBrandPane.getScene().getWindow();
        stage.close();
    }

    public void addBrandAction(ActionEvent actionEvent) {
        if (!name.getText().equals("")) {
            Brand brand = new Brand().create(name.getText());
            if (brand != null) {
                brandEntities.add(new BrandEntity(brand));
            }
            Stage stage = (Stage) addBrandForm.getScene().getWindow();
            stage.close();
        }
    }

    public void clearFormAction(ActionEvent actionEvent) {
        addBrandForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));

    }

    void setBrandEntities(ObservableList<BrandEntity> bodyEntities) {
        this.brandEntities = bodyEntities;
    }
}
