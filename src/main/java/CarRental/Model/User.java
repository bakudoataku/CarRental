package CarRental.Model;

import CarRental.Services.UserServices;
import orm.Model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sztosz on 3/17/16.
 */
public class User extends Model {

    public String login;
    public String password_hash;

    public User() {
        this.table = "users";
    }

    public static boolean authenticated(String username, String password) {
        List<User> users = (List<User>) (List<?>) new User().where(new HashMap<String, String>() {{
            put("login", username);
            put("password_hash", encodePassword(password));
        }});
        return !users.isEmpty();
    }

    private static String encodePassword(String password) {
        try {
            byte[] passwordBytes = password.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            byte[] encodedPassword = messageDigest.digest(passwordBytes);

            StringBuilder encodedPasswordStringBuilder = new StringBuilder();
            for (byte character : encodedPassword) {
                String hex = Integer.toHexString(0xFF & character);
                if (hex.length() == 1) encodedPasswordStringBuilder.append('0');
                encodedPasswordStringBuilder.append(hex);
            }
            return encodedPasswordStringBuilder.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
