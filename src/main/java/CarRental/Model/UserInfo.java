package CarRental.Model;

import orm.Model;

/**
 * Created by baku-desktop on 2016-03-21.
 */
public class UserInfo extends Model {

    public Integer id;
    public String name;
    public String social_number;
    public String licence;
    public String address;
    public Integer phone;
    public Integer user;

    public UserInfo(){this.table = "user_infos";}

}
