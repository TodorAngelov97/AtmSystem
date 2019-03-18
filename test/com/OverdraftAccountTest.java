package com;

import com.com.exception.AmountNotInRestrictionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OverdraftAccountTest {
    private static final int ACCOUNT_NUMBER = 1;
    private static final int OVERDRAFT = 20;
    private static final int CORRECT_WITHDRAW_AMOUNT = -10;
    private static final int BALANCE = 10;
    private static final int INCORRECT_WITHDRAW_AMOUNT = 25;

    private OverdraftAccount overdraftAccount;

    @Before
    public void setUp() {
        overdraftAccount = new OverdraftAccount(ACCOUNT_NUMBER, OVERDRAFT);
    }

    @Test
    public void testWithdrawWithCorrectData() {
        overdraftAccount.withdraw(CORRECT_WITHDRAW_AMOUNT);
        assertEquals(BALANCE, overdraftAccount.checkBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawToThrowException() {
        overdraftAccount.withdraw(INCORRECT_WITHDRAW_AMOUNT);
    }


}