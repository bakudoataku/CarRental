package CarRental.Model;


import orm.Model;

/**
 * Created by Bartosz on 24.03.2016.
 */
public class Address extends Model {

    public String street;
    public String city;
    public Integer zip;


    public Address() {
        this.table = "addresses";
    }

    public Address create(String street, String city, Integer zip) {
        this.newInstance = true;

        this.street = street;
        this.city = city;
        this.zip = zip;
        this.id = save();
        if (id == 0) {
            return null;
        } else {
            newInstance = false;
            return this;
        }
    }
}