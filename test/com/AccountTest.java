package com;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    private static final int ACCOUNT_NUMBER = 1;
    private static final int CORRECT_AMOUNT_FOR_DEPOSIT = 50;
    private static final int CORRECT_AMOUNT_FOR_WITHDRAW = 30;

    private Account account;


    @Before
    public void setUp() {
        account = new Account(ACCOUNT_NUMBER);
    }

    @Test
    public void testDepositWithCorrectData() {
        account.deposit(CORRECT_AMOUNT_FOR_DEPOSIT);
        assertEquals(CORRECT_AMOUNT_FOR_DEPOSIT, account.checkBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositToThrowException() {
        final int INCORRECT_AMOUNT_FOR_DEPOSIT = -1;
        account.deposit(INCORRECT_AMOUNT_FOR_DEPOSIT);
    }


    @Test
    public void testWithdrawWithCorrectData() {
        final int EXPECTED_BALANCE = 20;
        account.deposit(CORRECT_AMOUNT_FOR_DEPOSIT);
        account.withdraw(CORRECT_AMOUNT_FOR_WITHDRAW);
        assertEquals(EXPECTED_BALANCE, account.checkBalance());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawToThrowException() {
        account.withdraw(CORRECT_AMOUNT_FOR_WITHDRAW);
    }

    @Test
    public void testCheckBalanceByDefault() {
        final int DEFAULT_BALANCE = 0;
        assertEquals(DEFAULT_BALANCE, account.checkBalance());
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(ACCOUNT_NUMBER, account.getAccountNumber());
    }

}