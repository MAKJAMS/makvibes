package controllers;

import annotations.Secure;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller {
    
    @Before
    static void addUser() {
        User user = connectedUser();
        if(user != null) {
            renderArgs.put("user", user);
        }
    }
    @Before
    static void checkSecure() {
        Secure secure = getActionAnnotation(Secure.class);
        if (secure != null) {
            if (connectedUser() == null) {
                forbidden();
            }
        }
    }
    protected static User connectedUser() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        } 
        return null;
    }
    
}