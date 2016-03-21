package CarRental.Model;

import orm.Model;

/**
 * Created by sztosz on 3/21/16.
 */
public class Car extends Model {

    public Integer id;
    public String model;
    public Float price;
    public String registration;

    public Car() {
        this.table = "car";
    }
}
