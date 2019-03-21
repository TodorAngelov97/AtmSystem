package talentboost;

import talentboost.exception.InvalidAccountNumberException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    private static final int ACCOUNT_NUMBER = 1;
    private User user;

    @Mock
    private Map<Integer, Account> accounts;

    @Before
    public void setUp() {

        final int USER_ID = 1;
        final int PIN = 1234;
        user = new User(USER_ID, PIN);
    }

    @Test
    public void testGetAccountWithCorrectAccountNumber() {

        Account account = new Account(ACCOUNT_NUMBER);
        when(accounts.containsKey(ACCOUNT_NUMBER)).thenReturn(true);
        when(accounts.get(ACCOUNT_NUMBER)).thenReturn(account);
        assertEquals(account, user.getAccount(ACCOUNT_NUMBER));
    }


    @Test(expected = InvalidAccountNumberException.class)
    public void testGetAccountToThrowException() {
        user.getAccount(ACCOUNT_NUMBER);
    }


    @Test
    public void testAddAccount() {

        Account account = new Account(ACCOUNT_NUMBER);
        user.addAccount(account);
        when(accounts.containsKey(ACCOUNT_NUMBER)).thenReturn(true);
        when(accounts.get(ACCOUNT_NUMBER)).thenReturn(account);
        assertEquals(account, user.getAccount(ACCOUNT_NUMBER));
    }

    @Test
    public void testChangePinWhenPinIsCorrect() {
        assertTrue(user.changePin(1222));
    }


    @Test
    public void testChangePinWhenPinIsIncorrect() {
        assertFalse(user.changePin(1234));
    }

    @Test
    public void isAccountExistsWhenAccountExists() {

        when(accounts.containsKey(ACCOUNT_NUMBER)).thenReturn(true);
        assertTrue(user.isAccountExists(ACCOUNT_NUMBER));
    }

    @Test
    public void isAccountExistsWhenAccountDoNotExists() {
        assertFalse(user.isAccountExists(ACCOUNT_NUMBER));
    }
}