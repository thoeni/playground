package controllers;

import models.Book;
import models.Copy;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
public class CopyController extends Controller {

    static Form<Copy> copyForm = Form.form(Copy.class);

    public static Result copies(long bookId) {
        Book copyBook = Book.findBookById(bookId);
        System.out.printf("copyBook> %s%n", copyBook.title);
        return ok(
                views.html.copies.render(copyBook, Copy.allCopiesFromBookId(bookId), copyForm)
        );
    }

    public static Result newCopy() {
        System.out.println("newCopy called");
        Form<Copy> filledForm = copyForm.bindFromRequest();
        Book currentBook = Book.findBookById(filledForm.get().book.id);
        if (filledForm.hasErrors())
            return badRequest(
                    views.html.copies.render(currentBook, Copy.allCopiesFromBookId(currentBook.id), filledForm)
            );
        else {
            System.out.printf("Copy.create for book> %s, with owner %s%n", currentBook.title, filledForm.get().owner);
            filledForm.get().book = currentBook;
            Copy.create(filledForm.get());
            return redirect(String.format("/copies/%d", currentBook.id));
        }
    }

    public static Result deleteCopy(long id) {
        System.out.printf("Deleting %s%n", id);
        Book currentBook = Copy.findBookByCopyId(id);
        Copy.delete(id);
        return redirect(String.format("/copies/%d", currentBook.id));
    }
}
