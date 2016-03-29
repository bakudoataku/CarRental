package orm;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class BelongsTo {
    private Class<? extends Model> relationClass;
    private Model relationObject;
    private Integer relationObjectId;

    public BelongsTo(Class<? extends Model> relationClass) {
        this.relationClass = relationClass;
        relationObjectId = null;
    }

    BelongsTo(Class<? extends Model> relationClass, Integer relationObjectId) {
        this.relationObjectId = relationObjectId;
        this.relationClass = relationClass;
        this.relationObject = null;
    }

    public BelongsTo(Model relationObject) {
        this.relationObject = relationObject;
        this.relationClass = relationObject.getClass();
        try {
            this.relationObjectId = (Integer) relationObject.getClass().getField("id").get(relationObject);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Object get() {
        try {
            if (relationObjectId == null) {
                return relationClass.newInstance();
            } else if (relationObject == null){
                relationObject = relationClass.newInstance().find(relationObjectId);
                return relationObject;
            } else {
                return relationObject;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    Class<? extends Model> getRelationClass() {
        return relationClass;
    }
}
