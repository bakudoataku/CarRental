package CarRental.Model;

import orm.BelongsTo;
import orm.Model;

/**
 * Created by sztosz on 3/21/16.
 */
public class Car extends Model {

    public Float price;
    public String registration;
    public BelongsTo body = new BelongsTo(Body.class);

    public Car() {
        this.table = "cars";
    }

    public Body getBody(){
        return (Body) body.get();
    }
}
