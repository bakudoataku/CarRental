package CarRental.Model.Entities;

import javafx.beans.property.*;

/**
 * Created by Bartosz on 18.03.2016.
 */
public class CarEntity {

    private final IntegerProperty id;
    private final StringProperty body;
    private final FloatProperty price;
    private final StringProperty registration;

    public CarEntity(Integer id, String body, Float price, String registration) {
        this.id = new SimpleIntegerProperty(id);
        this.body = new SimpleStringProperty(body);
        this.price = new SimpleFloatProperty(price);
        this.registration = new SimpleStringProperty(registration);
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
}
