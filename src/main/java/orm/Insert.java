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
    private final List<String> columns = new ArrayList<>();
    private final List<String> values = new ArrayList<>();

    Insert(Model model) {
        this.model = model;
    }

    Integer insert(HashMap<Field, String> fields) {
        Integer id = 0;
        fields.forEach((field, type) -> {
            if (!field.getName().equals("id")) {
                columns.add(field.getName());
                if (type.equals("BelongsTo")) {
                    getBelongsFieldToValue(field);
                } else {
                    getFieldValue(field);
                }
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
        }
        return id;
    }

    private void getBelongsFieldToValue(Field field) {
        try {
            BelongsTo relation = ((BelongsTo) field.get(model));
            Field idField = relation.getRelationClass().getField("id");
            Integer relatedId = (Integer) idField.get(relation.get());
            values.add(relatedId.toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void getFieldValue(Field field) {
        try {
            values.add(field.get(model).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
