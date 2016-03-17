package CarRental.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by baku-desktop on 2016-03-16.
 * Modified by sztosz on 2016-03-17.
 */
public class DBConnector {

    private static final Logger log = LoggerFactory.getLogger(DBConnector.class);

    private static Connection connection = null;

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require&user=" + username + "&password=" + password;
        return DriverManager.getConnection(dbUrl);
    }

    public static Connection getInstance() throws URISyntaxException, SQLException {
        if (connection == null) {
            connection = getConnection();
            return connection;
        } else
            return connection;
    }

    public static ResultSet find(String table, List<String> values, HashMap<String, String> conditions) throws URISyntaxException, SQLException {
        Statement st = DBConnector.getInstance().createStatement();

        log.debug("QUERY");

        StringBuilder query = findMethod(table, values);
        StringBuilder query_conditions = new StringBuilder();

        query.append("WHERE ");
        conditions.forEach((k, v) -> query_conditions.append(String.format("%s='%s' AND ", k, v)));
        query.append(query_conditions.toString().replaceAll("AND $", " "));

        log.debug(query.toString());
        log.debug("Trying to execute query");

        return st.executeQuery(query.toString());
    }

    public static ResultSet find(String table, List<String> values) throws URISyntaxException, SQLException {
        Statement st = DBConnector.getInstance().createStatement();
        StringBuilder query = findMethod(table, values);

        return st.executeQuery(query.toString());
    }

    private static StringBuilder findMethod(String table, List<String> values) throws URISyntaxException, SQLException{

        log.debug("QUERY");

        StringBuilder query = new StringBuilder("SELECT ");

        log.debug(query.toString());

        StringBuilder query_values = new StringBuilder();

        log.debug(query.toString());

        values.forEach((value) -> query_values.append(String.format("%s, ", value)));
        query.append(query_values.toString().replaceAll(", $", " "));

        log.debug(query.toString());

        query.append(String.format("FROM %s ", table));

        log.debug(query.toString());
        log.debug("Trying to execute query");

        return query;
    }
}
