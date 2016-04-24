package CarRental.Model.Entities;

import CarRental.Model.Brand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by bartosz on 24/04/16.
 */
public class BrandEntity {

    public final Brand brandModel;
    private final IntegerProperty id;
    private final StringProperty name;

    public BrandEntity(Brand brand) {
        this.id = new SimpleIntegerProperty(brand.id);
        this.name = new SimpleStringProperty(brand.name);
        this.brandModel = brand;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
