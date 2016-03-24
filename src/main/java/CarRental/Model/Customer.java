package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class Customer extends Model {

    public Integer id;
    public String name;
    public String first_name;
    public String last_name;
    public Integer pesel;
    public String licence;
    public Integer phone;
    public BelongsTo address = new BelongsTo(Address.class);

    public Customer() {
        this.table = "customers";
    }
}
