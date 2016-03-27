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
    private Logger log = LoggerFactory.getLogger(Select.class);
    private Statement st;

    Select(Model model) {
        super(model);
        try {
            st = Connector.getInstance().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            st = null;
        }
    }

    List<Model> all() {
        try {
            String query = String.format("SELECT * FROM \"%s\"", model.getTable());
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
            conditions.forEach((k, v) -> where.add("\"" + k + "\"" + "='" + v + "'"));
            String query = String.format("SELECT * FROM \"%s\" WHERE %s", model.getTable(), String.join(" AND ", where));
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
            String query = String.format("SELECT * FROM \"%s\" WHERE id=%d LIMIT 1", model.getTable(), id);
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
        fields.forEach((k, v) -> {
            try {
                if (!v.equals("BelongsTo")) {
                    k.set(modelObject, rs.getClass().getMethod("get" + v, String.class).invoke(rs, k.getName()));
                } else if (v.equals("BelongsTo")) {
                    if (selectRelated) {
                        k.set(modelObject, getRelatedObject(rs, modelObject, k));
                    } else {
                        Class<? extends Model> relationClass = ((BelongsTo) k.get(modelObject)).getRelationClass();
                        Integer id = (Integer) rs.getClass().getMethod("getInt", String.class).invoke(rs, k.getName());
                        k.set(modelObject, new BelongsTo(relationClass, id));
                    }
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return modelObject;
    }

    private Object getRelatedObject(ResultSet rs, Model modelObject, Field k) {
        try {
            Integer id = rs.getInt(k.getName());
            BelongsTo relation = (BelongsTo) k.get(modelObject);
            Class<? extends Model> relatedClass = relation.getRelationClass();
            Object relatedModel = relatedClass.getMethod("find", Integer.class).invoke(relatedClass.newInstance(), id);
            relation.getClass().getMethod("set", Object.class, Integer.class).invoke(relation, relatedModel, id);
            return relation;
        } catch (SQLException | InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}