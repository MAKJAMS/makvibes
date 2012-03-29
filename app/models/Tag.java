package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Tag extends Model {
    private static final Pattern TAG_FINDER = Pattern.compile("\\#(\\w+)");

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public static Tag findOrCreate(String name) {
        Tag tag = Tag.find("byName", name).first();
        if (tag == null) {
            tag = new Tag(name);
        }
        return tag;
    }


    public static List<String> cloud(int count) {
        List<String> result = Tag.find(
                 "select tag.name as tag from Vibe vibe " +
                 "join vibe.tags as tag " +
                 "group by tag.name " +
                 "order by count(vibe.id) desc").fetch(count);
        return result;
    }

    public static Set<Tag> extractFrom(String text) {
        Set<Tag> matchedTags = new HashSet<Tag>();
        if (text != null) {
            Matcher matcher = TAG_FINDER.matcher(text);

            while (matcher.find()) {
                matchedTags.add(Tag.findOrCreate(matcher.group(1)));
            }
        }
        return matchedTags;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tag))
            return false;

        Tag tag = (Tag) other;
        if (!name.equals(tag.name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }
}
