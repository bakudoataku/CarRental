package CarRental.Model.Entities;

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

    public BodyEntity(Integer id, String name, String brand) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
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
