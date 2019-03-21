package talentboost;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OverdraftAccountTest {
    private OverdraftAccount overdraftAccount;

    @Before
    public void setUp() {
        final int ACCOUNT_NUMBER = 1;
        final int OVERDRAFT = 20;
        overdraftAccount = new OverdraftAccount(ACCOUNT_NUMBER, OVERDRAFT);
    }

    @Test
    public void testWithdrawWithCorrectData() {
        final int CORRECT_WITHDRAW_AMOUNT = -10;
        final int BALANCE = 10;

        overdraftAccount.withdraw(CORRECT_WITHDRAW_AMOUNT);
        assertEquals(BALANCE, overdraftAccount.checkBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawToThrowException() {
        final int INCORRECT_WITHDRAW_AMOUNT = 25;
        overdraftAccount.withdraw(INCORRECT_WITHDRAW_AMOUNT);
    }


}