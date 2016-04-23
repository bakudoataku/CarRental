package CarRental.Model.Entities;

import CarRental.Model.Body;
import CarRental.Model.Brand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Bartosz on 18.03.2016.
 */
public class BodyEntity {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty brand;

    public final Body bodyModel;

    public BodyEntity(Body body) {
        this.id = new SimpleIntegerProperty(body.id);
        this.name = new SimpleStringProperty(body.name);
        this.brand = new SimpleStringProperty(((Brand) body.brand.get()).name);
        this.bodyModel = body;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getBrand() {
        return brand.get();
    }

    @Override
    public String toString(){
        return name.getValue();
    }
}
