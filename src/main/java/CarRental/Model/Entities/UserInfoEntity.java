package CarRental.Model.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class UserInfoEntity {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty social_Number;
    private final StringProperty licence;
    private final StringProperty address;
    private final IntegerProperty phone;
    private final IntegerProperty user;

    public UserInfoEntity(Integer id, String name, String socialNumber, String licence, String address, Integer phone, Integer user){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.social_Number = new SimpleStringProperty(socialNumber);
        this.licence = new SimpleStringProperty(licence);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleIntegerProperty(phone);
        this.user = new SimpleIntegerProperty(user);
    }

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public String getName(){return name.get();}
    public StringProperty nameProperty(){return name;}
    public String getSocial_Number(){return social_Number.get();}
    public StringProperty socialNumberProperty(){return social_Number;}
    public String getLicence(){return licence.get();}
    public StringProperty licenceProperty(){return licence;}
    public String getAddress(){return address.get();}
    public StringProperty addressProperty(){return address;}
    public int getPhone(){return phone.get();}
    public IntegerProperty phoneProperty(){return phone;}
    public int getUser(){return user.get();}
    public IntegerProperty userProperty(){return user;}

}
