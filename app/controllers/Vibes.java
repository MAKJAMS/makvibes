package controllers;

import models.Vibe;
import play.mvc.Controller;

import java.util.List;

import static controllers.Application.connectedUser;

public class Vibes extends Controller{

    public static void latest(){
       List<Vibe> vibes =  Vibe.findAll();
       render(vibes);
    }

    public static void save(Vibe vibe){
       vibe.setPostedBy(connectedUser());
       vibe.create();
       latest();
    }
}
