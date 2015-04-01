package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by thoeni on 2/21/15 with Intellijnce
 */
@Entity
public class Book extends Model implements Serializable {

    @Id
    @Constraints.Required
    public long id;

    @Constraints.Required
    public String author;

    @Constraints.Required
    public String title;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Copy> copies;

    public static List<Book> all() {
        return find.orderBy().asc("title").findList();
    }

    public static Book findBookById(Long bookId) {
        return Book.find.where().idEq(bookId).findUnique();
    }

    public static void create(Book book) {
        System.out.println("Book.create");
        book.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Long id) {
        find.ref(id).update();
    }

    public static Finder<Long, Book> find = new Finder<Long, Book>(
            Long.class, Book.class
    );

    public String getIdString() {
        return Long.toString(id);
    }

    public String getTitle() {
        return title;
    }
}
