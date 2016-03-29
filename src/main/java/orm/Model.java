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
    protected String table;
    protected HashMap<Model, Model> join_relations = null;
    final HashMap<Field, String> fields = new HashMap<>();
    public Integer id;

    protected Boolean newInstance = false;


    protected Model() {
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            this.fields.put(field, getSimpleName(field));
        }
    }

    protected Model create() {
        newInstance = true;
        id = save();
        if (id == 0) {
            return null;
        } else {
            newInstance = false;
            return this;
        }
    }


    private String getSimpleName(Field field) {
        String name = field.getType().getSimpleName();
        switch (name) {
            case "Integer":
                name = "Int";
        }
        return name;
    }


    public List<? extends Model> all() {
        return new Select(this).all();
    }

    public Model find(Integer id) {
        return new Select(this).find(id);
    }

    public List<? extends Model> where(HashMap<String, String> conditions) {
        return new Select(this).where(conditions);
    }

    public Integer save() {
        if (this.newInstance) {
            return new Insert(this).insert();
        } else {
            return 0;
        }
    }

    String getTable() {
        return this.table;
    }

    public Model join(HashMap<Model, Model> join_relations) {
        this.join_relations = join_relations;
        return this;
    }
}
// TODO: test it all
// TODO: add relations
