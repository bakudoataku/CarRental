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
    final Boolean selectRelated;
    final Class<? extends Model> modelClass;
    final HashMap<Field, String> fields = new HashMap<>();

    private Logger log = LoggerFactory.getLogger(Query.class);

    Query(Model model, Boolean selectRelated) {
        this.model = model;
        this.selectRelated = selectRelated;
        this.modelClass = model.getClass();
        Field[] fields = model.getClass().getFields();
        for (Field field : fields) {
            this.fields.put(field, getSimpleName(field));
        }
    }

    Query(Model model) {
        this(model, false);
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
