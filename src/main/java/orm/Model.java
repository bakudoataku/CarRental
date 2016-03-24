package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bartosz on 18.03.2016.
 */
public abstract class Model {

    private static final Logger log = LoggerFactory.getLogger(Model.class);

    private HashMap<Field, String> fields = new HashMap<>();

    protected String table;

    protected Boolean newInstance = false;


    protected Model() {
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            this.fields.put(field, getSimpleName(field));
        }
    }

    public List<? extends Model> all() {
        return new Select(this).all(fields, this.getClass());
    }

    public Model find(Integer id) {
        return new Select(this).find(id, fields, this.getClass());
    }

    protected List<? extends Model> where(HashMap<String, String> conditions) {
        return new Select(this).where(conditions, fields, this.getClass());
    }

    protected Integer save() {
        if (this.newInstance) {
            return new Insert(this).insert(fields, this.getClass());
        } else {
            return 0;
        }
    }

    private String getSimpleName(Field field) {
        String name = field.getType().getSimpleName();
        switch (name) {
            case "Integer":
                name = "Int";
                break;
            default:
                break;
        }
        return name;
    }

    String getTable() {
        return this.table;
    }
}
// TODO: test it all
// TODO: add relations
