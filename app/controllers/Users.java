package controllers;

import models.User;
import models.Vibe;
import play.data.validation.Valid;

import java.util.List;

public class Users extends Application {

	public static void index(){
        List<Vibe> vibes =  Vibe.findAllVibes();
        render(vibes);
	}

	public static void register() {
        render();
    }
    
    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", user, verifyPassword);
        }
        user.create();
        saveUserDetailsInSession(user);
        flash.success("Welcome, " + user.name);
        Vibes.latest();
    }
    
    public static void login(String username, String password) {
        User user = User.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            saveUserDetailsInSession(user);
            flash.success(flashMessage(user));
            Vibes.latest();
        }
        flash.put("username", username);
        flash.error("Login failed");
        index();
    }
    private static String flashMessage(User user) {
        StringBuffer flashMessage = new StringBuffer("Welcome, ");
        flashMessage.append(user.name);
        return flashMessage.toString();
    }

    public static void logout() {
        session.clear();
        index();
    }

    
    private static void saveUserDetailsInSession(User user){
        session.put("user", user.username);
        session.put("logged", user.id);
    }

}
