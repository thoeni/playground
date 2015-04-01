package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
@Entity
public class User extends Model {

    @Id
    String username;
    @Constraints.Required
    String password;

    public static List<User> all() {
        return find.all();
    }

    public static void create(User User) {
        System.out.println("User.create");
        User.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Long id) {
        find.ref(id).update();
    }

    public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(
            Long.class, User.class
    );
}
