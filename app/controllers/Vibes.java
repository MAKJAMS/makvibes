package controllers;

import models.Vibe;
import play.mvc.Controller;

import java.util.List;

public class Vibes extends Application{
    
    public static void latest(){
       List<Vibe> vibes =  Vibe.findAll();
       render(vibes);
    }

    public static void save(Vibe vibe){
       vibe.create();
       latest();
    }
}
