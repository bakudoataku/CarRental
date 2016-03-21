package orm;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Bartosz on 18.03.2016.
 */
class Connector {

    private static Connection connection = null;

    private static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String connectionString = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            return DriverManager.getConnection(connectionString, username, password);
        } catch (ClassNotFoundException | SQLException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = getConnection();
            return connection;
        } else
            return connection;
    }
}
