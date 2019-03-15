package com;

public class Account {

    protected int balance;
    private int accountNumber;


    public Account(int accountNumber) {
        this.balance = 0;
        this.accountNumber = accountNumber;
    }


    public int deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Illegal argument, amount should be positive");
        }
        balance += amount;
        return balance;
    }

    public int withdraw(int amount) {
        if (isBalancePositivAfterWithdraw(amount)) {
            throw new IllegalArgumentException("Illegal argument, balance should be positive after withdraw operation");
        }
        balance -= amount;
        return balance;
    }

    private boolean isBalancePositivAfterWithdraw(int amount) {
        return balance - amount < 0;
    }

    public int checkBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
