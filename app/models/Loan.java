package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
@Entity
public class Loan extends Model {

    @Id
    Long id;
    @Constraints.Required
    @OneToOne
    User user;
    @Constraints.Required
    @OneToOne(cascade = CascadeType.ALL)
    Copy copy;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date loanStartDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date loanEndDate;

    public static List<Loan> all() {
        return find.all();
    }

    public static void create(Loan Loan) {
        System.out.println("Loan.create");
        Loan.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Long id) {
        find.ref(id).update();
    }

    public static Finder<Long, Loan> find = new Finder<Long, Loan>(
            Long.class, Loan.class
    );
}
