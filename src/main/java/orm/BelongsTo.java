package orm;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class BelongsTo {
    private Class relation;
    private Object relationObject;

    public BelongsTo(Class relation) {
        this.relation = relation;
    }

    public Object get(){
        return this.relationObject;
    }

    public void set(Object o){
        this.relationObject = o;
    }

    Class getRelationClass() {
        return relation;
    }
}
