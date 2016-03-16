package CarRental.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by baku-desktop on 2016-03-16.
 */
public class DBConnector {

    private static Connection connection = null;

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()+ "?sslmode=require&user="+ username + "&password=" + password;
        return DriverManager.getConnection(dbUrl);
    }

    public static Connection getInstance() throws URISyntaxException, SQLException {
        if(connection == null){
            connection = getConnection();
            return connection;
        }else
            return connection;
    }


}
