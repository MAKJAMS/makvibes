package controllers;

import models.Vibe;
import org.junit.Assert;
import org.junit.Test;
import play.test.FunctionalTest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static play.mvc.Http.Response;

public class VibesTest extends FunctionalTest {

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
}