package com;

public interface UserInterface {

    Account getAccount(int accountNumber);

    void addAccount(Account account);

    boolean changePin(int newPin);
}
