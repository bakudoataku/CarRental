package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

import java.util.HashMap;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class Body extends Model {

    public String name;
    public BelongsTo brand = new BelongsTo(Brand.class);


    public Body() {
        this.table = "bodies";
    }

    public Brand getBrand(){
        return (Brand) brand.get();
    }
}
