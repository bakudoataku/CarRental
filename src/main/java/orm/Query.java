package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by sztosz on 3/27/16.
 */
class Query {
    protected final Model model;
    final Class<? extends Model> modelClass;
    final HashMap<Field, String> fields = new HashMap<>();
    private Logger log = LoggerFactory.getLogger(Query.class);

    Query(Model model) {
        this.model = model;
        this.modelClass = model.getClass();
        Field[] fields = model.getClass().getFields();
        for (Field field : fields) {
            this.fields.put(field, getSimpleName(field));
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

}
