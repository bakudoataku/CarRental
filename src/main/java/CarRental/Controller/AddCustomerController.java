package CarRental.Controller;

import CarRental.Model.Address;
import CarRental.Model.Customer;
import CarRental.Model.Entities.CustomerEntity;
import fxextensions.fields.IntegerField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-22.
 */
public class AddCustomerController implements Initializable {

    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public IntegerField pesel;
    @FXML
    public TextField license;
    @FXML
    public IntegerField phone;
    @FXML
    public TextField street;
    @FXML
    public TextField city;
    @FXML
    public IntegerField zip;
    @FXML
    public Pane addCustomerForm;

    private ObservableList<CustomerEntity> parentCustomerEntityObservableList;

    void setParentCustomerEntityObservableList(ObservableList<CustomerEntity> parentCustomerEntityObservableList) {
        this.parentCustomerEntityObservableList = parentCustomerEntityObservableList;
    }

    public void clearFormAction() {
        addCustomerForm.getChildren().stream().filter(node -> node instanceof TextField).forEach(node -> ((TextField) node).setText(""));
    }

    public void addCustomerAction() {
        if (addCustomerForm.getChildren().stream().filter(node -> node instanceof TextField).noneMatch(node -> ((TextField) node).getText().equals(""))) {
            Address address = new Address().create(street.getText(), city.getText(), Integer.parseInt(zip.getText()));
            if (address != null) {
                Customer customer = new Customer().create(
                        firstName.getText(),
                        lastName.getText(),
                        Integer.parseInt(pesel.getText()),
                        license.getText(),
                        Integer.parseInt(phone.getText()),
                        address
                );
                if (customer != null) {
                    parentCustomerEntityObservableList.add(new CustomerEntity(customer, address));
                }
            }
            Stage stage = (Stage) addCustomerForm.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
