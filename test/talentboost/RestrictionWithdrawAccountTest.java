package talentboost;

import talentboost.exception.AmountNotInRestrictionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestrictionWithdrawAccountTest {
    private RestrictionWithdrawAccount restrictionWithdrawAccount;

    @Before
    public void setUp() {
        final int ACCOUNT_NUMBER = 1;
        final int LIMIT_FOR_WITHDRAW = 100;
        final int DEPOSIT_AMOUNT = 200;
        restrictionWithdrawAccount = new RestrictionWithdrawAccount(ACCOUNT_NUMBER, LIMIT_FOR_WITHDRAW);
        restrictionWithdrawAccount.deposit(DEPOSIT_AMOUNT);
    }

    @Test
    public void testWithdrawWithCorrectData() {
        final int CORRECT_WITHDRAW_AMOUNT = 80;
        restrictionWithdrawAccount.withdraw(CORRECT_WITHDRAW_AMOUNT);
        final int BALANCE = 120;
        assertEquals(BALANCE, restrictionWithdrawAccount.checkBalance());
    }

    @Test(expected = AmountNotInRestrictionException.class)
    public void testWithdrawToThrowException() {
        final int INCORRECT_WITHDRAW_AMOUNT = 110;
        restrictionWithdrawAccount.withdraw(INCORRECT_WITHDRAW_AMOUNT);
    }

}