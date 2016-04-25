package CarRental.Model.Entities;

import CarRental.Model.Car;
import CarRental.Model.CarRents;
import CarRental.Model.Customer;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by baku-desktop on 2016-04-25.
 */
public class CarRentsEntity {

    private final IntegerProperty id;
    private final Timestamp date_from;
    private final Timestamp date_to;
    private final FloatProperty price;
    public final Customer customer;
    public final Car car;

    public CarRentsEntity(CarRents carRents, Customer customer, Car car){
        this.id = new SimpleIntegerProperty(carRents.id);
        this.date_from = Timestamp.valueOf(carRents.date_from.atStartOfDay());
        this.date_to = Timestamp.valueOf(carRents.date_to.atStartOfDay());
        this.price = new SimpleFloatProperty(carRents.price);
        this.customer = customer;
        this.car = car;
    }

    public int getId() {return id.get();}

    public LocalDate getDate_from(){return date_from.toLocalDateTime().toLocalDate();}

    public LocalDate getDate_to(){return date_to.toLocalDateTime().toLocalDate();}

    public Float getPrice(){return price.get();}
}
