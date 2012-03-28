package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Vibe extends Model {

    @Required
    @MaxSize(140)
    private String message;

    private Date postedOn;

    @Required
    private String author;

    public Vibe(){
        this.postedOn = new Date();
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

    public static List<Vibe> findAllVibes(){
        return Vibe.findAll();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
