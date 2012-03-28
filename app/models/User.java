package models;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class User extends Model {

    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;

    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;

    @Embedded
    public Role role;
    
    @Required
    @MaxSize(100)
    private String name;

    @Deprecated
    public User(String name, String password, String username, Role role) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public String toString()  {
        return "User(" + username + ")";
    }

    public boolean hasModeratorAccess() {
        return role.hasModeratorAccess();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
