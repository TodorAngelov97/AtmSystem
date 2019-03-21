package talentboost;

import talentboost.exception.InvalidAccountNumberException;

import java.util.HashMap;
import java.util.Map;

public class User implements UserInterface {
    private int pin;
    private int userId;
    private Map<Integer, Account> accounts;


    public User(int userId, int pin) {
        this.pin = pin;
        this.userId = userId;
        this.accounts = new HashMap<>();
    }


    public Account getAccount(int accountNumber) {
        if (!isAccountExists(accountNumber)) {
            throw new InvalidAccountNumberException("Invalid account number");
        }
        return accounts.get(accountNumber);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public boolean isCorrectPin(int pin) {
        return this.pin == pin;
    }

    public boolean changePin(int newPin) {
        if (isCorrectPin(newPin)) {
            return false;
        }
        pin = newPin;
        return true;
    }

    public boolean isAccountExists(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }
}
