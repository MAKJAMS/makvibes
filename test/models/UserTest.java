package models;

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
}
