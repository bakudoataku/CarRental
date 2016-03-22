package orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Select {
    private final Model model;
    private Logger log = LoggerFactory.getLogger(Select.class);

    Select(Model model) {
        this.model = model;
    }

    List<Model> all(HashMap<Field, String> fields, Class<? extends Model> modelClass) {
        try {
            Statement st = Connector.getInstance().createStatement();
            String query = String.format("SELECT * FROM \"%s\"", model.getTable());
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            return resultSetToArrayList(fields, modelClass, rs);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    List<Model> where(HashMap<String, String> conditions, HashMap<Field, String> fields, Class<? extends Model> modelClass) {
        try {
            Statement st = Connector.getInstance().createStatement();
            List<String> where = new ArrayList<>();
            conditions.forEach((k, v) -> where.add("\"" + k + "\""+ "='" + v + "'"));
            String query = String.format("SELECT * FROM \"%s\" WHERE %s", model.getTable(), String.join(" AND ", where));
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            return resultSetToArrayList(fields, modelClass, rs);
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    Model find(Integer id, HashMap<Field, String> fields, Class<? extends Model> modelClass) {
        try {
            Statement st = Connector.getInstance().createStatement();
            String query = String.format("SELECT * FROM \"%s\" WHERE id=%d LIMIT 1", model.getTable(), id);
            log.debug("Query {}", query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Model modelObject = modelClass.newInstance();
                resultToModelObject(fields, rs, modelObject);
                return modelObject;
            } else {
                return null;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Model> resultSetToArrayList(HashMap<Field, String> fields, Class<? extends Model> modelClass, ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Model> result = new ArrayList<>();
        while (rs.next()) {
            Model modelObject = modelClass.newInstance();
            resultToModelObject(fields, rs, modelObject);
            result.add(modelObject);
        }
        return result;
    }

    private void resultToModelObject(HashMap<Field, String> fields, ResultSet rs, Model modelObject) {
        fields.forEach((k, v) -> {
            try {
                Method rsMethod = rs.getClass().getMethod("get" + v, String.class);
                k.set(modelObject, rsMethod.invoke(rs, k.getName()));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}