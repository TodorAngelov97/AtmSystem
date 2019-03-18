package com;

public class OverdraftAccount extends Account {

    private int overdraft;
    private static final int MINUS = -1;


    public OverdraftAccount(int accountNumber, int overdraft) {
        super(accountNumber);
        this.overdraft = overdraft;
    }

    @Override
    public int withdraw(int amount) {
        if (!isBalanceInRestrictionAfterWithdraw(amount)) {
            throw new IllegalArgumentException("Illegal argument, balance should be greater than overdraft after withdraw operation");
        }
        balance -= amount;
        return balance;
    }

    private boolean isBalanceInRestrictionAfterWithdraw(int amount) {
        return balance - amount > MINUS * overdraft;
    }


}
