package models;

import org.junit.Assert;
import org.junit.Test;
import play.test.UnitTest;

public class RoleTest extends UnitTest{

    @Test
    public void shouldIndicateThatAStudentLeaderHasModeratorAccess() {
        Role role = new Role("Student Leader");
        Assert.assertTrue(role.hasModeratorAccess());
    }

    @Test
    public void shouldIndicateThatADefaultUserHasModeratorAccess() {
        Role role = new Role("Default");
        Assert.assertFalse(role.hasModeratorAccess());
    }
}
