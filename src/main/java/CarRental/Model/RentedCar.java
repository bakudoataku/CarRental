package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

/**
 * Created by sztosz on 3/21/16.
 */
public class RentedCar extends Model {

    public Float price;
    public String registration;
    public BelongsTo car = new BelongsTo(Car.class);
    public BelongsTo customer = new BelongsTo(Customer.class);

    public RentedCar() {
        this.table = "cars";
    }

    public RentedCar create(Float price, String registration, Body body) {
        this.price = price;
        this.registration = registration;
        this.body = new BelongsTo(body);
        return (RentedCar) super.create();
    }

    public Body getBody() {
        return (Body) body.get();
    }
}


