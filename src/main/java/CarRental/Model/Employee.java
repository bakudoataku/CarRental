package CarRental.Model;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import orm.Model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bartosz on 22.03.2016.
 */
public class Employee extends Model {

    private static final Logger log = LoggerFactory.getLogger(Employee.class);

    public String login;
    public String password_hash;


    public Employee() {
        this.table = "employees";
    }

    public static boolean authenticate(String login, String password) {
        List<Employee> employees = (List<Employee>) new Employee().where(new HashMap<String, String>() {{
            put("login", login);
        }});
        return BCrypt.checkpw(password, employees.get(0).password_hash);
    }
}