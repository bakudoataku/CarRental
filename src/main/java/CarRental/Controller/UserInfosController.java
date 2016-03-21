package CarRental.Controller;

import CarRental.Model.Entities.UserInfoEntity;
import CarRental.Model.UserInfo;
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
public class UserInfosController implements Initializable{

    @FXML private TableView<UserInfoEntity> userInfosTableView;
    @FXML private TableColumn<UserInfoEntity, Integer> id;
    @FXML private TableColumn<UserInfoEntity, String> name;
    @FXML private TableColumn<UserInfoEntity, String> socialNumber;
    @FXML private TableColumn<UserInfoEntity, String> licence;
    @FXML private TableColumn<UserInfoEntity, String> address;
    @FXML private TableColumn<UserInfoEntity, Integer> phone;
    @FXML private TableColumn<UserInfoEntity, Integer> user;

    private ObservableList<UserInfoEntity> userInfoEntities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //List<UserInfo> userInfos = (List<UserInfo>) (List<?>) new UserInfo().all();
        //HashMap<String, String> arguments = new HashMap<>();
        //arguments.put("user", "3");
        List<UserInfo> userInfos = (List<UserInfo>) (List<?>) new UserInfo().where(new HashMap<String, String>(){{put("user", "3");}});
        userInfos.forEach(customer -> userInfoEntities.add(new UserInfoEntity(customer.id, customer.name, customer.social_number, customer.licence, customer.address, customer.phone, customer.user)));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        socialNumber.setCellValueFactory(new PropertyValueFactory<>("socialNumber"));
        licence.setCellValueFactory(new PropertyValueFactory<>("licence"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        userInfosTableView.setItems(userInfoEntities);
    }

}
