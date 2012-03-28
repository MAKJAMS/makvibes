package models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Role{

    private static String moderatorRole = "Student Leader";

    public String roleName;

    public Role(String name) {
        this.roleName = name;
    }

    public boolean hasModeratorAccess() {
        return moderatorRole.equalsIgnoreCase(roleName);
    }
}