package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Vibe extends Model {

    private String message;
    private Date postedOn;

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

}
