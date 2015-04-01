package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoeni on 2/21/15 with Intellijnce
 */

@Entity
public class Task extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String label;

    public static List<Task> all() {
        return find.all();
    }

    public static void create(Task task) {
        System.out.println("Task.create");
        task.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static Finder<Long, Task> find = new Finder<Long, Task>(
            Long.class, Task.class
    );
}
