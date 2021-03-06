package models;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Model {

    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    private String username;

    @Required
    @MaxSize(15)
    @MinSize(5)
    private String password;

    @Embedded
    private Role role;

    @Required
    @MaxSize(100)
    private String name;

    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
    private List<Vibe> vibes;

    @Deprecated
    public User(String name, String password, String username, Role role) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.role = role;
        vibes = new ArrayList<Vibe>();
    }

    public String toString()  {
        return "User(" + username + ")";
    }

    public boolean hasModeratorAccess() {
        return role.hasModeratorAccess();
    }

    public boolean canDelete(Vibe vibe){
        return hasModeratorAccess() || vibes.contains(vibe);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void useDefaultRole() {
        this.role = Role.DEFAULT;
    }

    public List<Vibe> getVibes() {
        return vibes;
    }
}
