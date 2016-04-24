package CarRental.Model;

import orm.Model;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class Brand extends Model {

    public String name;

    public Brand() {
        this.table = "brands";
    }

    public Brand create(String name) {
        this.name = name;
        return (Brand) super.create();
    }
}
