package models;

import com.avaje.ebean.FetchConfig;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
@Entity
public class Copy extends Model {

    @Id
    public Long id;
    @Constraints.Required
    public String owner;
    @ManyToOne(cascade = CascadeType.ALL)
    public Book book;

    public static List<Copy> allCopiesFromBookId(Long bookId) {
        return find
                .fetch("book", new FetchConfig().query())
                .where().eq("book.id", bookId)
                .findList();
    }

    public static Book findBookByCopyId(Long id) {
        return (Copy.find.where().idEq(id).findUnique()).book;
    }

    public static void create(Copy copy) {
        System.out.printf("Creating copy: %s, %s, %s%n", copy.id, copy.owner, copy.book.title);
        copy.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static Finder<Long, Copy> find = new Finder<Long, Copy>(
            Long.class, Copy.class
    );
}
