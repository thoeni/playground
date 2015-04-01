package controllers;

import models.Book;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by thoeni on 2/22/15 with Intellijnce
 */
public class BookController extends Controller {

    static Form<Book> bookForm = Form.form(Book.class);

    public static Result books() {
        return ok(
                views.html.books.render(booksMap(Book.all()), Book.all(), bookForm)
        );
    }

    public static Map<String, String> booksMap(List<Book> books) {
        Function<Book, String> keyMapper = new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return Long.toString(book.id);
            }
        };
        Function<Book, String> valueMapper = new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.title;
            }
        };
        return books.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static Result newBook() {
        System.out.println("newBook called");
        Form<Book> filledForm = bookForm.bindFromRequest();
        if (filledForm.hasErrors())
            return badRequest(
                    views.html.books.render(booksMap(Book.all()), Book.all(), filledForm)
            );
        else {
            System.out.println("creating book");
            Book.create(filledForm.get());
            return redirect("/books");
        }
    }

    public static Result listCopies() {
        String bookId = request().queryString().get("id")[0];
        System.out.printf("Listing copies for book %s%n", bookId);
        return redirect(String.format("/copies/%s", bookId));
    }

    public static Result deleteBook(long id) {
        System.out.println("deleteBook called");
        Book.delete(id);
        return redirect("/books");
    }
}
