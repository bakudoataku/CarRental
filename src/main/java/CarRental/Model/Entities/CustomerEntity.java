package CarRental.Model.Entities;

import CarRental.Model.Address;
import CarRental.Model.Customer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class CustomerEntity {

    private final IntegerProperty id;
    private final StringProperty first_name;
    private final StringProperty last_name;
    private final IntegerProperty pesel;
    private final StringProperty licence;
    private final IntegerProperty phone;
    private final StringProperty street;
    private final StringProperty city;
    private final IntegerProperty zip;

    public CustomerEntity(Integer id, String first_name, String last_name, Integer pesel, String licence, Integer phone, Address address) {
        this.id = new SimpleIntegerProperty(id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.pesel = new SimpleIntegerProperty(pesel);
        this.licence = new SimpleStringProperty(licence);
        this.phone = new SimpleIntegerProperty(phone);
        this.street = new SimpleStringProperty(address.street);
        this.city = new SimpleStringProperty(address.city);
        this.zip = new SimpleIntegerProperty(address.zip);
    }

    public CustomerEntity(Customer customer, Address address) {
        this.id = new SimpleIntegerProperty(customer.id);
        this.first_name = new SimpleStringProperty(customer.first_name);
        this.last_name = new SimpleStringProperty(customer.last_name);
        this.pesel = new SimpleIntegerProperty(customer.pesel);
        this.licence = new SimpleStringProperty(customer.licence);
        this.phone = new SimpleIntegerProperty(customer.phone);
        this.street = new SimpleStringProperty(address.street);
        this.city = new SimpleStringProperty(address.city);
        this.zip = new SimpleIntegerProperty(address.zip);
    }

    public int getId() {
        return id.get();
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public int getPesel() {
        return pesel.get();
    }

    public String getLicence() {
        return licence.get();
    }

    public int getPhone() {
        return phone.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getCity() {
        return city.get();
    }

    public int getZip() {
        return zip.get();
    }
}
