package models;

import org.junit.Assert;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

public class VibeTest extends UnitTest{

    @Test
    public void shouldExtractHashTagsOnSave(){
        Vibe vibeWithTags = new Vibe();
        vibeWithTags.setMessage("heading to #elections");
        vibeWithTags.save();

        Set<Tag> tags = vibeWithTags.getTags();
        Assert.assertThat(tags.size(), is(1));
    }
}
