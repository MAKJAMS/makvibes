package models;

import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class TagTest extends UnitTest {
    
    @Before
    public void setUp(){
        Fixtures.deleteAllModels();
    }

    @Test
    public void shouldRetrieveTagFromDBIfItExists() {
        Tag existing = new Tag("IExist").save();
        assertThat(Tag.findOrCreate("IExist"), equalTo(existing));
    }

    @Test
    public void shouldCreateNewTagIfItDoesNotExist() {
        assertThat(Tag.findOrCreate("Extinct"), notNullValue());
    }

    @Test
    public void shouldExtractHashTagsFromText() {
        String textWithHash = "heading to #elections! #march2012 #!*&%";
        Set<Tag> tags = Tag.extractFrom(textWithHash);

        assertThat(tags.size(), is(2));
        assertThat(tags, hasItems(new Tag("elections"), new Tag("march2012")));
    }

    @Test
    public void shouldReturnEmptySetOfTagsWhenTextIsNull() {
        String nothingness = null;
        assertThat(Tag.extractFrom(nothingness).size(), is(0));
    }

    @Test
    public void shouldRetrieveTagCloudInDescendingOrderOfFrequency() {
        createVibes();

        List<String> cloud = Tag.cloud(4);
        assertThat(cloud, is(Arrays.asList("headline", "cricket", "makVibes", "mobileMoney")));
    }

    @Test
    public void shouldRetrieveTagCloudUpToSpecifiedSize() {
        createVibes();

        assertThat(Tag.cloud(1).size(), is(1));
        assertThat(Tag.cloud(4).size(), is(4));
        assertThat(Tag.cloud(7).size(), is(4));
    }

    private void createVibes() {
        createVibesWithTag("cricket", 4);
        createVibesWithTag("headline", 5);
        createVibesWithTag("mobileMoney", 1);
        createVibesWithTag("makVibes", 2);
    }

    private void createVibesWithTag(String tag, int count) {
        for (int repetition = 0; repetition < count; repetition++) {
            Vibe vibe = new Vibe();
            vibe.tagWith(tag);
            vibe.save();
        }
    }
}
