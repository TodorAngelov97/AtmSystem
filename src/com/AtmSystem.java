package com;

import com.com.exception.*;

import java.util.HashMap;
import java.util.Map;

public class AtmSystem implements AtmSystemInterface {


    private Map<Integer, User> users;
    private int numberOfAccounts;


    public AtmSystem() {

        final int INITIAL_ACCOUNT_NUMBER = -1;
        users = new HashMap<>();
        numberOfAccounts = INITIAL_ACCOUNT_NUMBER;
    }


    //these three methods could be do shorter
    public void depositToAccount(int userId, int pin, int accountNumber, int amount) {

        Account account = getAccountFromUser(userId, pin, accountNumber);
        account.deposit(amount);
    }

    public void withdrawFromAccount(int userId, int pin, int accountNumber, int amount) {

        Account account = getAccountFromUser(userId, pin, accountNumber);
        account.withdraw(amount);
    }

    public void checkBalance(int userId, int pin, int accountNumber) {

        Account account = getAccountFromUser(userId, pin, accountNumber);
        System.out.println(account.checkBalance());
    }


    public void changePin(int userId, int oldPin, int newPin) {

        User user = getUser(userId, oldPin);
        user.changePin(newPin);
    }

    public void createUser(int userId, int pin) {

        if (isUserExists(userId)) {
            throw new UserAlreadyExistsException("User already exist");
        }
        User newUser = new User(userId, pin);
        users.put(userId, newUser);
        System.out.println("Successfully created user");
    }


    public int createAccount(int userId, int pin) {

        ++numberOfAccounts;
        if (isAccountExists(userId, pin, numberOfAccounts)) {
            throw new AccountAlreadyExistsException("Account already exists, userId has to be unique");
        }
        User user = users.get(userId);
        Account newAccount = new Account(numberOfAccounts);
        user.addAccount(newAccount);
        System.out.println("Successfully created user");
        return numberOfAccounts;
    }

    public int createOverdraftAccount(int userId, int pin, int overdraft) {

        ++numberOfAccounts;
        if (isAccountExists(userId, pin, numberOfAccounts)) {
            throw new AccountAlreadyExistsException("Account already exists, userId has to be unique");
        }
        User user = users.get(userId);
        Account newAccount = new OverdraftAccount(numberOfAccounts, overdraft);
        user.addAccount(newAccount);
        System.out.println("Successfully created user");
        return numberOfAccounts;
    }

    public int createRestrictionWithdrawAccount(int userId, int pin, int restriction) {

        ++numberOfAccounts;
        if (isAccountExists(userId, pin, numberOfAccounts)) {
            throw new AccountAlreadyExistsException("Account already exists, userId has to be unique");
        }
        User user = users.get(userId);
        Account newAccount = new RestrictionWithdrawAccount(numberOfAccounts, restriction);
        user.addAccount(newAccount);
        System.out.println("Successfully created user");
        return numberOfAccounts;
    }

    public void joinToYourAccount(int userId, int pin, int joinedUserId, int accountNumber) {

        Account accountToJoin = getAccountFromUser(userId, pin, accountNumber);
        User userToJoin = users.get(joinedUserId);
        userToJoin.addAccount(accountToJoin);
    }


    private Account getAccountFromUser(int userId, int pin, int accountNumber) {
        User user = getUser(userId, pin);
        Account account = user.getAccount(accountNumber);
        return account;
    }

    private boolean validateInput(int userId, int pin, int accountNumber) {

        User user = getUser(userId, pin);
        return isAccountNumberCorrect(user, accountNumber);
    }

    private boolean isAccountNumberCorrect(User user, int accountNumber) {

        if (user.getAccount(accountNumber) == null) {
            throw new InvalidAccountNumberException("Incorrect account number");
        }
        return true;
    }

    private User getUser(int userId, int pin) {

        if (isUserExists(userId)) {
            User user = users.get(userId);
            if (isCorrectUsersPin(user, pin)) {
                return user;
            }
        }
        throw new InvalidUserIDException("Invalid user id exception");
    }

    private boolean isCorrectUsersPin(User user, int pin) {

        if (!user.isCorrectPin(pin)) {
            throw new InvalidPinException("Invalid pin of the user");
        }
        return true;
    }

    private boolean isUserExists(int userID) {
        return users.containsKey(userID);
    }

    private boolean isAccountExists(int userId, int pin, int accountNumber) {

        User user = getUser(userId, pin);
        return user.isAccountExists(accountNumber);
    }


    public static void main(String[] args) {
//        AtmSystem atm = new AtmSystem();
//        atm.createUser(12, 1245);
//        atm.createAccount(12, 1245);
//        atm.checkBalance(12, 1245, 1);
//        atm.depositToAccount(12, 1245, 1, 12);
//        atm.checkBalance(12, 1245, 1);
//        atm.withdrawFromAccount(12, 1245, 1, 10);
//        atm.checkBalance(12, 1245, 1);
//        atm.createOverdraftAccount(12, 1245, 100);
//        // atm.withdrawFromAccount(12, 1245, 34, 110);
//        atm.createRestrictionWithdrawAccount(12, 1245, 110);
//        //  atm.withdrawFromAccount(12, 1245, 35, 120);
    }


}