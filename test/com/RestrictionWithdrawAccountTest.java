package com;

import com.com.exception.AmountNotInRestrictionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestrictionWithdrawAccountTest {

    private static final int ACCOUNT_NUMBER = 1;
    private static final int LIMIT_FOR_WITHDRAW = 100;
    private static final int CORRECT_WITHDRAW_AMOUNT = 80;
    private static final int INCORRECT_WITHDRAW_AMOUNT = 110;
    private static final int DEPOSIT_AMOUNT = 200;
    private static final int BALACE = 120;

    private RestrictionWithdrawAccount restrictionWithdrawAccount;

    @Before
    public void setUp() {
        restrictionWithdrawAccount = new RestrictionWithdrawAccount(1, 100);
        restrictionWithdrawAccount.deposit(DEPOSIT_AMOUNT);
    }

    @Test
    public void testWithdrawWithCorrectData() {
        restrictionWithdrawAccount.withdraw(CORRECT_WITHDRAW_AMOUNT);
        assertEquals(BALACE, restrictionWithdrawAccount.checkBalance());
    }

    @Test(expected = AmountNotInRestrictionException.class)
    public void testWithdrawToThrowException() {
        restrictionWithdrawAccount.withdraw(INCORRECT_WITHDRAW_AMOUNT);
    }

}