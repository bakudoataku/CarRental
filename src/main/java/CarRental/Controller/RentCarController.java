package CarRental.Controller;

import CarRental.Model.Address;
import CarRental.Model.Body;
import CarRental.Model.Brand;
import CarRental.Model.Customer;
import CarRental.Model.Entities.BodyEntity;
import CarRental.Model.Entities.BrandEntity;
import CarRental.Model.Entities.CustomerEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-04-25.
 */
public class RentCarController implements Initializable {

    @FXML Pane rentCarPane;
    @FXML ChoiceBox<CustomerEntity> customerSelect;
    @FXML ChoiceBox<BodyEntity> bodySelect;
    @FXML ChoiceBox<BrandEntity> brandSelect;
    @FXML DatePicker fromDate;
    @FXML DatePicker toDate;
    @FXML Label statusLabel;

    private ObservableList<CustomerEntity> customerEntities = FXCollections.observableArrayList();
    private ObservableList<BodyEntity> bodyEntities = FXCollections.observableArrayList();
    private ObservableList<BrandEntity> brandEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Customer> customerList = (List<Customer>) new Customer().all();
        customerList.forEach(customer -> customerEntities.add(new CustomerEntity(customer, (Address) customer.address.get())));
        customerSelect.setItems(customerEntities);

        List<Brand> brandList = (List<Brand>) new Brand().all();
        brandList.forEach(brand -> brandEntities.add(new BrandEntity(brand)));
        brandSelect.setItems(brandEntities);

        brandSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!= null){
                    BrandEntity selectedValue = (BrandEntity)brandSelect.getValue();
                    bodyEntities.clear();
                    List<Body> bodyList = (List<Body>) new Body().where(new HashMap<String, String>(){{
                        put("brand", String.valueOf(selectedValue.getId()));
                    }});
                    bodyList.forEach(body -> bodyEntities.add(new BodyEntity(body)));
                    bodySelect.setItems(bodyEntities);
                }
            }
        });
    }

    public void clearForm(ActionEvent actionEvent) {
        rentCarPane.getChildren().stream().filter(node -> node instanceof DatePicker).forEach(node -> ((DatePicker) node).setValue(null));
    }

    public void rentCar(ActionEvent actionEvent) {
        if(fromDate.getValue().isAfter(toDate.getValue())){
            statusLabel.setText("bledna data");
        }
    }
}
