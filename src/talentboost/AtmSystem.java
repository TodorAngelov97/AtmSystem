package talentboost;

import talentboost.exception.InvalidPinException;

public class AtmSystem {

    private UserManager userManager;

    public AtmSystem(UserManager userManager) {
        this.userManager = userManager;
    }


    public void depositToAccount(int userId, int pin, int accountNumber, int amount) {

        Account account = userManager.getAccountFromUser(userId, accountNumber);
        account.deposit(amount);
    }

    public void withdrawFromAccount(int userId, int pin, int accountNumber, int amount) {

        Account account = userManager.getAccountFromUser(userId, accountNumber);
        account.withdraw(amount);
    }

    public int checkBalance(int userId, int pin, int accountNumber) {

        Account account = userManager.getAccountFromUser(userId, accountNumber);
        return account.checkBalance();
    }

    public void changePin(int userId, int oldPin, int newPin) {

        User user = getUser(userId, oldPin);
        user.changePin(newPin);
    }

    private User getUser(int userId, int pin) {

        User user = userManager.getUser(userId);
        isCorrectUsersPin(user, pin);
        return user;
    }

    private boolean isCorrectUsersPin(User user, int pin) {

        if (!user.isCorrectPin(pin)) {
            throw new InvalidPinException("Invalid pin of the user");
        }
        return true;
    }
}

