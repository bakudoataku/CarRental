package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class Customer extends Model {

    public Integer id;
    public String first_name;
    public String last_name;
    public Integer pesel;
    public String licence;
    public Integer phone;
    public BelongsTo address = new BelongsTo(Address.class);

    public Customer() {
        this.table = "customers";
    }

    public Customer create(String first_name, String last_name, Integer pesel, String licence, Integer phone, Address address) {
        this.newInstance = true;
        this.first_name = first_name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.licence = licence;
        this.phone = phone;
        this.address.set(address);
        this.id = save();
        if (id == 0) {
            return null;
        } else {
            newInstance = false;
            return this;
        }
    }
}
