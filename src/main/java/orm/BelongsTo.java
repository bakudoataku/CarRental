package orm;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class BelongsTo {
    private Class<? extends Model> relation;
    private Object relationObject;

    public BelongsTo(Class<? extends Model> relation) {
        this.relation = relation;
    }

    public Object get(){
        return this.relationObject;
    }

    public void set(Object o){
        this.relationObject = o;
    }

    Class<? extends Model> getRelationClass() {
        return relation;
    }
}
