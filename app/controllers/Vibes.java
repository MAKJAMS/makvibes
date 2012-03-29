package controllers;

import models.Tag;
import models.Vibe;

import java.util.List;

public class Vibes extends Application {

    public static void latest(){
       List<Vibe> vibes =  Vibe.findAllVibes();
       List<String> tagCloud = Tag.cloud(10);
       render(vibes, tagCloud);
    }

    public static void save(Vibe vibe){
       vibe.setPostedBy(connectedUser());
       vibe.save();
       latest();
    }
}
