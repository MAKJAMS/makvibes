package models;

import org.junit.Assert;
import org.junit.Test;
import play.test.UnitTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Vibe vibe = mock(Vibe.class);
        author.getVibes().add(vibe);
        assertTrue(author.canDelete(vibe));
    }

    @Test
    public void shouldIndicateThatAVibeCannotBeDeletedBySomeOtherStudent(){
        User notTheAuthor = givenADefaultUserWithName();
        assertFalse(notTheAuthor.canDelete(new Vibe()));
    }
    
    @Test
    public void shouldIndicateThatAnyVibeCanBeDeletedByAStudentLeader(){
        User studentLeader = givenAStudentLeader();
        assertTrue(studentLeader.canDelete(new Vibe()));
    }

    private User givenAStudentLeader() {
        Role moderator = mock(Role.class);
        when(moderator.hasModeratorAccess()).thenReturn(true);
        return new User("Tish", "password", "tish", moderator);
    }

    public User givenADefaultUserWithName(){
        return new User("Jane Deer", "password", "jdeer", mock(Role.class));
    }
}
