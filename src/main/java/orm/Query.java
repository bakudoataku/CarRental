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

    private Logger log = LoggerFactory.getLogger(Query.class);

    Query(Model model) {
        this.model = model;
        this.modelClass = model.getClass();
    }
}
