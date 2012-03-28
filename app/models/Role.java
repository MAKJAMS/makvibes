package models;

import javax.persistence.Embeddable;

@Embeddable
public class Role{

    protected static final Role DEFAULT = new Role("default");
    private static String MODERATOR_ROLE = "Student Leader";

    public String roleName;

    public Role(String name) {
        this.roleName = name;
    }

    public boolean hasModeratorAccess() {
        return MODERATOR_ROLE.equalsIgnoreCase(roleName);
    }

    @Override
    public String toString() {
        return roleName;
    }
}