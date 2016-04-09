package CarRental.Model.Entities;

import CarRental.Model.Car;
import javafx.beans.property.*;

/**
 * Created by Bartosz on 18.03.2016.
 */
public class CarEntity {

    private final IntegerProperty id;
    private final StringProperty body;
    private final StringProperty brand;
    private final FloatProperty price;
    private final StringProperty registration;

    public CarEntity(Integer id, String body, String brand, Float price, String registration) {
        this.id = new SimpleIntegerProperty(id);
        this.body = new SimpleStringProperty(body);
        this.brand = new SimpleStringProperty(brand);
        this.price = new SimpleFloatProperty(price);
        this.registration = new SimpleStringProperty(registration);
    }

    public CarEntity(Car car) {
        this.id = new SimpleIntegerProperty(car.id);
        this.body = new SimpleStringProperty(car.getBody().name);
        this.brand = new SimpleStringProperty(car.getBody().getBrand().name);
        this.price = new SimpleFloatProperty(car.price);
        this.registration = new SimpleStringProperty(car.registration);
    }

    public int getId() {
        return id.get();
    }

    public String getBody() {
        return body.get();
    }

    public float getPrice() {
        return price.get();
    }

    public String getRegistration() {
        return registration.get();
    }

    public String getBrand() {
        return brand.get();
    }
}
