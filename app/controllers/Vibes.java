package controllers;

import models.Tag;
import models.Vibe;
import play.data.validation.Valid;

import java.util.List;

public class Vibes extends Application {

    public static void latest() {
        List<Vibe> vibes = Vibe.findAllVibes();
        List<String> tagCloud = Tag.cloud(10);
        render(vibes, tagCloud);
    }

    public static void save(@Valid Vibe vibe) {
        vibe.setPostedBy(connectedUser());
        if (!validation.hasErrors()) {
            vibe.save();
        }
        latest();
    }

    public static void delete(long id) {
        Vibe vibe = Vibe.findById(id);
        vibe.delete();
        latest();
    }
}
