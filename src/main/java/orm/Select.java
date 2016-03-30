package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Select extends Query {
    private ArrayList<Class> join_classes = new ArrayList<>();
    private Logger log = LoggerFactory.getLogger(Select.class);
    private Statement st;
    private String query;

    Select(Model model) {
        super(model);
        prepareQuery();
        if (model.join_relations != null) {
            prepareJoins();
        }
        try {
            st = Connector.getInstance().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareQuery() {
        ArrayList<String> columns = new ArrayList<>();
        model.fields.forEach((k, v) -> {
            columns.add(String.format("\"%s\".\"%s\" as %s_%s", model.getTable(), k.getName(), model.getTable(), k.getName()));
        });
        if (model.join_relations != null) {
            query = String.format("SELECT %s, %s FROM \"%s\" ", String.join(", ", columns), joinFields(), model.getTable());
        } else {
            query = String.format("SELECT %s FROM \"%s\" ", String.join(", ", columns), model.getTable());
        }
    }

    private String joinFields() {
        String joinFields;
        ArrayList<String> columns = new ArrayList<>();
        model.join_relations.forEach((left, right) -> {
            right.fields.forEach((k, v) -> {
                columns.add(String.format("\"%s\".\"%s\" as %s_%s", right.getTable(), k.getName(), right.getTable(), k.getName()));
            });
        });
        joinFields = String.join(", ", columns);

        return joinFields;
    }

    private void prepareJoins() {
        StringBuilder joins = new StringBuilder();
        model.join_relations.forEach((left, right) -> {
            join_classes.add(right.getClass());
            joins.append(String.format(" JOIN %s ON \"%s\".\"%s\" = \"%s\".\"id\"",
                    right.table,
                    left.table,
                    right.getClass().getSimpleName().toLowerCase(),
                    right.table));
            query += joins.toString();
        });
    }

    List<Model> all() {
        try {
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            return resultSetToArrayList(rs);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    List<Model> where(HashMap<String, String> conditions) {
        try {
            List<String> where = new ArrayList<>();
            conditions.forEach((k, v) -> where.add(String.format("\"%s\"='%s'", String.join("\".\"", model.getTable(), k), v)));
            query = String.format("%s WHERE %s", query, String.join(" AND ", where));
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            return resultSetToArrayList(rs);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    Model find(Integer id) {
        try {
            query = String.format("%s WHERE id=%d LIMIT 1", query, id);
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return resultToModelObject(rs, modelClass.newInstance());
            } else {
                return null;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Model> resultSetToArrayList(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Model> result = new ArrayList<>();
        while (rs.next()) {
            result.add(resultToModelObject(rs, modelClass.newInstance()));
        }
        return result;
    }

    private Model resultToModelObject(ResultSet rs, Model modelObject) {
        modelObject.fields.forEach((k, v) -> {
            try {
                if (!v.equals("BelongsTo")) {
                    k.set(modelObject, rs.getClass().
                            getMethod("get" + v, String.class).
                            invoke(rs, String.join("_", modelObject.getTable(), k.getName())));
                } else if (v.equals("BelongsTo")) {
                    if (join_classes.contains(((BelongsTo) k.get(modelObject)).getRelationClass())) {
                        Model relatedModel = getRelatedObject(rs, modelObject, k);
                        k.set(modelObject, new BelongsTo(relatedModel));
                    } else {
                        Class<? extends Model> relationClass = ((BelongsTo) k.get(modelObject)).getRelationClass();
                        Integer id = (Integer) rs.getClass().
                                getMethod("getInt", String.class).
                                invoke(rs, String.join("_", modelObject.getTable(), k.getName()));
                        k.set(modelObject, new BelongsTo(relationClass, id));
                    }
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return modelObject;
    }

    private Model getRelatedObject(ResultSet rs, Model modelObject, Field k) {
        try {
            return resultToModelObject(rs, (Model) ((BelongsTo) k.get(modelObject)).get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}