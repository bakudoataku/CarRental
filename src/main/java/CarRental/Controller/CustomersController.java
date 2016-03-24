package CarRental.Controller;

import CarRental.Model.Address;
import CarRental.Model.Customer;
import CarRental.Model.Entities.CustomerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class CustomersController implements Initializable {


    @FXML
    private TableView<CustomerEntity> CustomersTableView;
    @FXML
    private TableColumn<CustomerEntity, Integer> id;
    @FXML
    private TableColumn<CustomerEntity, String> firstName;
    @FXML
    private TableColumn<CustomerEntity, String> lastName;
    @FXML
    private TableColumn<CustomerEntity, Integer> pesel;
    @FXML
    private TableColumn<CustomerEntity, String> licence;
    @FXML
    private TableColumn<CustomerEntity, Integer> phone;
    @FXML
    private TableColumn<CustomerEntity, String> street;
    @FXML
    private TableColumn<CustomerEntity, String> city;
    @FXML
    private TableColumn<CustomerEntity, Integer> zip;

    private ObservableList<CustomerEntity> customerEntityObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Customer> userInfos = (List<Customer>) new Customer().all();
        userInfos.forEach(customer ->
                customerEntityObservableList.add(new CustomerEntity(customer.id, customer.first_name, customer.last_name,
                        customer.pesel, customer.licence, customer.phone, (Address) customer.address.get())));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        licence.setCellValueFactory(new PropertyValueFactory<>("licence"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        CustomersTableView.setItems(customerEntityObservableList);
    }

}
