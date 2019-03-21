package talentboost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AtmSystemTest {
    private static final int MOCKED_USER_ID = 1;
    private static final int MOCKED_USER_PIN = 1234;
    private static final int MOCKED_ACCOUNT_NUMBER = 5;
    private static final int AMOUNT = 100;

    private AtmSystem atmSystem;

    @Mock
    private UserManager userManager;

    @Mock
    private Account account;

    @Mock
    private User user;

    @Before
    public void setUp() {

        atmSystem = new AtmSystem(userManager);
        when(userManager.getUser(MOCKED_USER_ID)).thenReturn(user);
    }


    private void setBehaviour() {

        when(userManager.getAccount(user, MOCKED_ACCOUNT_NUMBER)).thenReturn(account);
        when(user.isCorrectPin(MOCKED_USER_PIN)).thenReturn(true);
    }

    //> or test balance
    @Test
    public void testDepositToAccount() {

        setBehaviour();
        when(account.checkBalance()).thenReturn(AMOUNT);
        atmSystem.depositToAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
        assertEquals(AMOUNT, atmSystem.checkBalance(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER));
    }

    @Test
    public void testWithdrawFromAccount() {

        final int EXPECTED_AMOUNT = 120;
        setBehaviour();
        when(account.checkBalance()).thenReturn(EXPECTED_AMOUNT);
        atmSystem.withdrawFromAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
        assertEquals(EXPECTED_AMOUNT, atmSystem.checkBalance(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER));
    }

    @Test
    public void testCheckBalance() {

        setBehaviour();
        when(account.checkBalance()).thenReturn(AMOUNT);
        assertEquals(AMOUNT, atmSystem.checkBalance(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER));
    }


    //? WHAT TO ASSERT
    @Test
    public void testChangePin() {

        final int NEW_PIN = 1234;
        User user = mock(User.class);
        when(userManager.getUser(MOCKED_USER_ID)).thenReturn(user);
        when(user.isCorrectPin(MOCKED_USER_PIN)).thenReturn(true);
        when(user.changePin(NEW_PIN)).thenReturn(true);
        atmSystem.changePin(MOCKED_USER_ID, MOCKED_USER_PIN, NEW_PIN);
    }
/*
    private static final int MOCKED_USER_ID = 1;
    private static final int MOCKED_USER_PIN = 1234;
    private static final int MOCKED_ACCOUNT_NUMBER = 5;
    private static final int AMOUNT = 100;

    @Spy
    private AtmSystem atmSystem;

    @Mock
    private User userMock;

    @Mock
    private Account accountMock;

    @Before
    public void setUp() {

        when(userMock.getAccount(MOCKED_ACCOUNT_NUMBER)).thenReturn(accountMock);
        when(userMock.isCorrectPin(MOCKED_USER_PIN)).thenReturn(true);
        doReturn(userMock).when(atmSystem).getUser(MOCKED_USER_ID);
    }

    @Test
    public void testDepositToAccountWithCorrectData() {

        atmSystem.depositToAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
        verify(atmSystem, times(1)).depositToAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
    }

    @Test(expected = InvalidUserIDException.class)
    public void testDepositToAccountToThrowInvalidUserIdException() {

        final int INVALID_USER_ID = 123;
        atmSystem.depositToAccount(INVALID_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
    }

//
//    @Test(expected = InvalidAccountNumberException.class)
//    public void testDepositToAccountToThrowInvalidAccountNumber() {
//
//        when()
//        final int INVALID_ACCOUNT_NUMBER = 234;
//        doThrow().when(atmSystem).ge
//        atmSystem.depositToAccount(MOCKED_USER_ID, MOCKED_USER_PIN, INVALID_ACCOUNT_NUMBER, AMOUNT);
//    }


    @Test(expected = InvalidPinException.class)
    public void testDepositToAccountToThrowInvalidPinException() {

        final int INVALID_PIN = -10;
        atmSystem.depositToAccount(MOCKED_USER_ID, INVALID_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
    }


    @Test
    public void testWithdrawFromAccountWithCorrectData() {

        atmSystem.withdrawFromAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
        verify(atmSystem, times(1)).withdrawFromAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER, AMOUNT);
    }

    @Test
    public void testCheckBalanceWithCorrectData() {

        atmSystem.checkBalance(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER);
        verify(atmSystem, times(1)).checkBalance(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_ACCOUNT_NUMBER);
    }

    @Test
    public void testChangePin() {

        final int NEW_USER_PIN = 2;
        atmSystem.changePin(MOCKED_USER_ID, MOCKED_USER_PIN, NEW_USER_PIN);
        verify(atmSystem, times(1)).changePin(MOCKED_USER_ID, MOCKED_USER_PIN, NEW_USER_PIN);
    }


    //FINAL
    @Test
    public void testCreateUserWithCorrectData() {

        atmSystem.createUser(12, 9876);
        verify(atmSystem, times(1)).createUser(12, 9876);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testCreateUserToThrowUserAlreadyExistsException() {

        final int PIN = 9876;
        doReturn(true).when(atmSystem).isUserExists(MOCKED_USER_ID);
        atmSystem.createUser(MOCKED_USER_ID, PIN);
    }


    //one more test
    @Test
    public void testCreateAccountWithCorrectData() {

        final int USER_ID = 67;
        final int PIN = 3456;
        atmSystem.createUser(USER_ID, PIN);
        verify(atmSystem, times(1)).createUser(USER_ID, PIN);
    }

    @Test
    public void testJoinToYourAccountWithCorrectData() {

        final int MOCKED_USER_TO_JOIN_ID = 5678;
        User userToJoinMock = mock(User.class);
        doReturn(userToJoinMock).when(atmSystem).getUser(MOCKED_USER_TO_JOIN_ID);
        atmSystem.joinToYourAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_USER_TO_JOIN_ID, MOCKED_ACCOUNT_NUMBER);
        verify(atmSystem, times(1)).joinToYourAccount(MOCKED_USER_ID, MOCKED_USER_PIN, MOCKED_USER_TO_JOIN_ID, MOCKED_ACCOUNT_NUMBER);
    }
    */
}