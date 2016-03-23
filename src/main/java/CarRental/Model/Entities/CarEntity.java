package CarRental.Model.Entities;

import javafx.beans.property.*;

/**
 * Created by Bartosz on 18.03.2016.
 */
public class CarEntity {

    private final IntegerProperty id;
    private final StringProperty model;
    private final FloatProperty price;
    private final StringProperty registration;

    public CarEntity(Integer id, String model, Float price, String registration) {
        this.id = new SimpleIntegerProperty(id);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleFloatProperty(price);
        this.registration = new SimpleStringProperty(registration);
    }

    public int getId() {
        return id.get();
    }

    public String getModel() {
        return model.get();
    }

    public float getPrice() {
        return price.get();
    }

    public String getRegistration() {
        return registration.get();
    }
}
