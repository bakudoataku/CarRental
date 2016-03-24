package CarRental.Model;


import orm.Model;

/**
 * Created by Bartosz on 24.03.2016.
 */
public class Address extends Model {

    public Integer id;
    public String name;
    public String street;
    public String city;
    public Integer zip;


    public Address() {
        this.table = "addresses";
    }

}