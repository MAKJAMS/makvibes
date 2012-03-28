package controllers;

import models.Vibe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.FunctionalTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static play.mvc.Http.Response;

public class VibesTest extends FunctionalTest {

    @Before
    public void setup(){
        Fixtures.deleteAllModels();
        Fixtures.loadModels("data.yml");
    }

    @Test
    public void shouldRedirectToLatestAfterSave() {
        Response response = POST("/vibes");
        assertStatus(302, response);
        assertHeaderEquals("Location", "/vibes", response);
    }

    @Test
    public void shouldSaveMessage() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("vibe.message", "TheMessage");
        POST("/vibes", parameters);

        Vibe savedVibe = Vibe.find("byMessage", "TheMessage").first();
        Assert.assertThat(savedVibe, notNullValue());
    }

    @Test
    public void shouldFindAllMessages(){
        GET("/vibes");
        List<Vibe> vibes = (List<Vibe>) renderArgs("vibes");
        Assert.assertThat(vibes.size(), equalTo(2));
        Assert.assertThat(vibes.get(0).getMessage() , equalTo("Welcome all, how awesome is this?"));
        Assert.assertThat(vibes.get(1).getMessage() , equalTo("What is going to happen on Friday?"));
    }
}