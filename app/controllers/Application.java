package controllers;

import models.Task;
import play.data.*;
import models.*;
import play.mvc.*;

import views.html.*;
import views.html.helper.form;

public class Application extends Controller {

    static Form<Task> taskForm = Form.form(Task.class);

    public static Result index() {
        return redirect("/tasks");
    }

    public static Result tasks() {
        return ok(
                views.html.index.render(Task.all(), taskForm)
        );
    }

    public static Result newTask() {
        System.out.println("newTask called");
        Form<Task> filledForm = taskForm.bindFromRequest();
        if (filledForm.hasErrors())
            return badRequest(
                    views.html.index.render(Task.all(), filledForm)
            );
        else {
            System.out.println("creating task");
            Task.create(filledForm.get());
            return redirect("/tasks");
        }
    }

    public static Result deleteTask(long id) {
        System.out.println("deleteTask called");
        Task.delete(id);
        return redirect("/tasks");
    }

}
