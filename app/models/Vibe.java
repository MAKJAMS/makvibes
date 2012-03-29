package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Vibe extends Model {

    @Required
    @MaxSize(140)
    private String message;

    private Date postedOn;

    @Required
    @ManyToOne
    private User postedBy;

    @ManyToMany(cascade= CascadeType.PERSIST)
    public Set<Tag> tags;

    public Vibe(){
        this.postedOn = new Date();
        this.tags = new HashSet<Tag>();
    }

    @Override
    public Vibe save(){
        tags.addAll(Tag.extractFrom(message));
        return super.save();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public static List<Vibe> findAllVibes(){
        return Vibe.findAll();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void tagWith(String tag) {
        this.tags.add(Tag.findOrCreate(tag));
    }
}
