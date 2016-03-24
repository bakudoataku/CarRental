package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bartosz on 24.03.2016.
 */
public class Insert {
    private Logger log = LoggerFactory.getLogger(Insert.class);
    private final Model model;

    Insert(Model model) {
        this.model = model;
    }

    Integer insert(HashMap<Field, String> fields, Class<? extends Model> modelClass) {
        final Boolean[] success = {true};
        Integer id = null;
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        fields.forEach((k, v) -> {
            try {
                if (!v.equals("BelongsTo")) {
                    if (!k.getName().equals("id")) {
                        columns.add(k.getName());
                        values.add(k.get(model).toString());
                    }
                } else if (v.equals("BelongsTo")) {
                    columns.add(k.getName());
                    BelongsTo relation = ((BelongsTo) k.get(model));
                    Class relationObjectClass = relation.getRelationClass();
                    Object relattuinObject = relation.get();
                    Field idField = relationObjectClass.getField("id");
                    Integer relatedId = (Integer) idField.get(relattuinObject);
                    values.add(relatedId.toString());
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                success[0] = false;
            }
        });
        try {
            Statement st = Connector.getInstance().createStatement();
            String query = String.format("INSERT INTO \"%s\" (%s) VALUES ('%s')",
                    model.getTable(),
                    String.join(", ", columns),
                    String.join("', '", values)
            );
            log.debug("Query {}", query);
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success[0] = false;
        }
        if (success[0]) {
            return id;
        } else {
            return 0;
        }
    }
}
