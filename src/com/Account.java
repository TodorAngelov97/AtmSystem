package com;

public class Account {

    private int balance;
    private int accountNumber;


    public Account(int accountNumber) {
        this.balance = 0;
        this.accountNumber = accountNumber;
    }



    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    public int withdraw(int amount) {
        balance -= amount;
        return balance;
    }

    public int checkBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
