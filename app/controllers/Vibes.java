package controllers;

import models.Vibe;
import play.data.validation.Valid;

import java.util.List;

public class Vibes extends Application{

    public static void latest(){
       List<Vibe> vibes =  Vibe.findAllVibes();
       render(vibes);
    }

    public static void save(@Valid Vibe vibe){
        if(validation.hasErrors()) {
            List<Vibe> vibes =  Vibe.findAll();
            render("@latest", vibe, vibes);
        }
       vibe.create();
       latest();
    }
}
