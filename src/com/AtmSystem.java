package com;

import com.com.exception.AccountAlreadyExistsException;
import com.com.exception.InvalidAccountNumberException;
import com.com.exception.InvalidUserIDException;
import com.com.exception.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class AtmSystem {

    //Map<Integer,User>   -->HashMAp key accountNumber
    //Map<Integer,User> -->HashMap key ID
    //Set<User> >
    private Map<Integer, User> users;


    public AtmSystem() {
        users = new HashMap<>();
    }

    private boolean validateInput(int userId, int pin, int accountNumber) {
        return !checkUserIsNull(userId, pin) && isAccountNumberCorrect(getUser(userId, pin), accountNumber);
    }


    public void depositToAccount(int userId, int pin, int accountNumber, int amount) {

        if (validateInput(userId, pin, accountNumber)) {
            getUser(userId, pin).getAccount(accountNumber).deposit(amount);
        }
    }

    public void withdrawFromAccount(int userId, int pin, int accountNumber, int amount) {

        if (validateInput(userId, pin, accountNumber)) {
            getUser(userId, pin).getAccount(accountNumber).withdraw(amount);
        }
    }

    public void checkBalance(int userId, int pin, int accountNumber) {

        if (validateInput(userId, pin, accountNumber)) {
            System.out.println(getUser(userId, pin).getAccount(accountNumber).checkBalance());
        }
    }


    public void changePin(int userId, int oldPin, int newPin) {
        if (!checkUserIsNull(userId, oldPin)) {
            getUser(userId, oldPin).changePin(newPin);
        }
    }

    private boolean isAccountNumberCorrect(User user, int accountNumber) {
        if (user.getAccount(accountNumber) == null) {
            throw new InvalidAccountNumberException("Incorrect account number");
        }
        return true;
    }


    private User getUser(int userId, int pin) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            if (isCorrectUsersPin(user, pin)) {
                return user;
            }
        }
        return null;
    }


    private boolean checkUserIsNull(int userID, int pin) {
        if (getUser(userID, pin) == null) {
            throw new InvalidUserIDException("Incorrect userID");
        }
        return false;
    }

    private boolean isCorrectUsersPin(User user, int pin) {
        if (!user.isCorrectPin(pin)) {
            return false;
        }
        return true;
    }

    private boolean isUserExists(int userID) {
        return users.containsKey(userID);
    }

    public void createUser(int userId, int pin) {
        //verification for existing userID
        if (isUserExists(userId)) {
            throw new UserAlreadyExistsException("User already exist");
        }
        users.put(userId, new User(userId, pin));
        System.out.println("Successfully created user");
    }

    private boolean isAccountExists(int userId, int pin, int accountNumber) {
        return getUser(userId, pin).isAccountExists(accountNumber);
    }

    public void createAccount(int userId, int pin, int accountNumber) {
        if (isAccountExists(userId, pin, accountNumber)) {
            throw new AccountAlreadyExistsException("Account already exists, userId has to be unique");
        }
        users.get(userId).addAccount(new Account(accountNumber));
        System.out.println("Successfully created user");

    }

    public static void main(String[] args) {
        AtmSystem atm = new AtmSystem();
        atm.createUser(12, 1245);
        atm.createAccount(12, 1245, 1);
        atm.checkBalance(12, 1245, 1);
        atm.depositToAccount(12, 1245, 1, 12);
        atm.checkBalance(12, 1245, 1);
        atm.withdrawFromAccount(12, 1245, 1, 10);
        atm.checkBalance(12, 1245, 1);

    }


}