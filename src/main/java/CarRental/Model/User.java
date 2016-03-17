package CarRental.Model;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by sztosz on 3/17/16.
 *
 */
public class User {
    public static boolean authenticateUser(String login, String password) {
        try {
            ResultSet rs = DBConnector.find("public.user", Collections.singletonList("id"), new HashMap<String, String>() {{
                put("login", login);
                put("password_hash", encodePassword(password));
            }});
            return rs.next();
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String encodePassword(String password) {
        try {
            byte[] passwordBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] encodedPassword = md.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte anEncodedPassword : encodedPassword) {
                String hex = Integer.toHexString(0xFF & anEncodedPassword);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
