package CarRental.Model.Entities;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by baku-desktop on 2016-03-17.
 */
public class CarEntity {

    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleStringProperty model = new SimpleStringProperty();
    public SimpleFloatProperty price = new SimpleFloatProperty();
    public SimpleStringProperty registration = new SimpleStringProperty();

    public Integer getId(){
        return id.get();
    }

    public String getModel(){
        return model.get();
    }

    public float getFloat(){
        return price.get();
    }

    public String getRegistration(){
        return registration.get();
    }
}
