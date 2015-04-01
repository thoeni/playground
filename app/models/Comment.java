package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
@Entity
public class Comment extends Model {

    @Id
    Long id;
    public String text;
    @OneToOne(cascade = CascadeType.ALL)
    public Book book;

    public int rating;

    public static List<Comment> all() {
        return find.all();
    }

    public static void create(Comment Comment) {
        System.out.println("Comment.create");
        Comment.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Long id) {
        find.ref(id).update();
    }

    public static Model.Finder<Long, Comment> find = new Model.Finder<Long, Comment>(
            Long.class, Comment.class
    );
}
