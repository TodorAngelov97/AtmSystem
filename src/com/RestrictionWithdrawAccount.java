package com;

import com.com.exception.AmountNotInRestrictionException;

public class RestrictionWithdrawAccount extends Account {

    private int limitForWithdraw;

    public RestrictionWithdrawAccount(int accountNumber, int limitForWithdraw) {

        super(accountNumber);
        this.limitForWithdraw = limitForWithdraw;
    }


    //new exception
    public int withdraw(int amount) {
        if (!isAmountInTheRestriction(amount)) {
            throw new AmountNotInRestrictionException("Amount not in restriction");
        }
        super.withdraw(amount);
        return balance;
    }

    private boolean isAmountInTheRestriction(int amount) {
        return amount <= limitForWithdraw;
    }

}
