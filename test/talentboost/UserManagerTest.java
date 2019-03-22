package talentboost;

import org.junit.Before;
import org.junit.Test;
import talentboost.exception.InvalidUserIDException;
import talentboost.exception.UserAlreadyExistsException;

import static org.junit.Assert.*;

public class UserManagerTest {

    private UserManager userManager;


    @Before
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void testCreateAccountWithCorrectData() {

        final int USER_ID = 10;
        final int PIN = 1234;
        userManager.createUser(USER_ID, PIN);
        int accountNumber = userManager.createAccount(USER_ID);
        assertNotNull(userManager.getAccount(userManager.getUser(USER_ID), accountNumber));
    }


    @Test
    public void testGetUserWithCorrectData() {

        final int USER_ID = 10;
        final int PIN = 1234;
        userManager.createUser(USER_ID, PIN);
        assertNotNull(userManager.getUser(USER_ID));
    }


    @Test(expected = InvalidUserIDException.class)
    public void testGetUserToThrowInvalidUserIdException() {

        final int USER_ID = 10;
        final int PIN = 1234;
        final int INVALID_USER_ID = 1;
        userManager.createUser(USER_ID, PIN);
        userManager.getUser(INVALID_USER_ID);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testCreateUserToThrowUserAlreadyExistsException() {

        final int USER_ID = 10;
        final int PIN = 1234;
        final int INVALID_USER_ID = 1;
        userManager.createUser(USER_ID, PIN);
        userManager.createUser(USER_ID, INVALID_USER_ID);
    }

    @Test
    public void testJoinToYourAccount() {

        final int USER_ID = 10;
        final int JOINED_USER_ID = 11;
        final int PIN = 1234;
        final int JOINED_PIN = 1235;
        userManager.createUser(USER_ID, PIN);
        userManager.createUser(JOINED_USER_ID, JOINED_PIN);
        final int accountNumber = userManager.createAccount(USER_ID);
        userManager.joinToYourAccount(USER_ID, JOINED_USER_ID, accountNumber);
        assertNotNull(userManager.getAccount(userManager.getUser(JOINED_USER_ID), accountNumber));
    }


}