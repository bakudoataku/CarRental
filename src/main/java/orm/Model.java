package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bartosz on 18.03.2016.
 */
public abstract class Model {

    private static final Logger log = LoggerFactory.getLogger(Model.class);
    protected String table;
    public Integer id;

    protected Boolean newInstance = false;


    protected Model() {
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

    public List<? extends Model> all() {
        return new Select(this).all();
    }

    public Model find(Integer id) {
        return new Select(this).find(id);
    }

    protected List<? extends Model> where(HashMap<String, String> conditions) {
        return new Select(this).where(conditions);
    }

    protected Integer save() {
        if (this.newInstance) {
            return new Insert(this).insert();
        } else {
            return 0;
        }
    }

    String getTable() {
        return this.table;
    }
}
// TODO: test it all
// TODO: add relations
