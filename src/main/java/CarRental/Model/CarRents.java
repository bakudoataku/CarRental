package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

import java.time.LocalDate;

/**
 * Created by baku-desktop on 2016-04-25.
 */
public class CarRents extends Model {

    public LocalDate date_from;
    public LocalDate date_to;
    public Float price;
    public BelongsTo customer = new BelongsTo(Customer.class);
    public BelongsTo car = new BelongsTo(Car.class);

    public CarRents() {
        this.table = "car_rents";
    }

    public CarRents create(LocalDate date_from, LocalDate date_to, Float price, Customer customer, Car car){
        this.date_from = date_from;
        this.date_to = date_to;
        this.price = price;
        this.customer = new BelongsTo(customer);
        this.car = new BelongsTo(car);
        return (CarRents) super.create();
    }
}
