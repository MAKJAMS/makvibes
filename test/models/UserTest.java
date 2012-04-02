package models;

import org.junit.Assert;
import org.junit.Test;
import play.test.UnitTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserTest extends UnitTest{

    @Test
    public void shouldIndicateThatAStudentLeaderHasModeratorAccess() {
        Role role = mock(Role.class);
        User user = new User("user", "password", "username", role);
        user.hasModeratorAccess();
        verify(role).hasModeratorAccess();
    }

    @Test
    public void shouldUseDefaultRole() {
        User user = new User("user", "password", "username", null);
        user.useDefaultRole();
        Assert.assertEquals("default", user.getRole().toString());
    }

    @Test
    public void shouldIndicateThatAVibeCanBeDeletedByTheAuthor(){
        User author = givenADefaultUserWithName();
        Vibe vibe = new Vibe();
        author.vibes.add(vibe);

        assertTrue(author.canDelete(vibe));
    }

    @Test
    public void shouldIndicateThatAVibeCannotBeDeletedBySomeOtherStudent(){
        User notTheAuthor = givenADefaultUserWithName();
        assertFalse(notTheAuthor.canDelete(new Vibe()));
    }
    
    public User givenADefaultUserWithName(){
        return new User("Jane Deer", "password", "jdeer", Role.DEFAULT);
    }
}
