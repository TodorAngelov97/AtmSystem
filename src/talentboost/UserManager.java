package talentboost;

import talentboost.exception.AccountAlreadyExistsException;
import talentboost.exception.InvalidUserIDException;
import talentboost.exception.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private int numberOfAccounts;
    private Map<Integer, User> users;

    public UserManager() {

        final int INITIAL_ACCOUNT_NUMBER = -1;
        this.users = new HashMap<>();
        numberOfAccounts = INITIAL_ACCOUNT_NUMBER;
    }

    public int createAccount(int userId, int pin) {

        ++numberOfAccounts;
        validateAccountExists(userId, pin, numberOfAccounts);
        User user = getUser(userId);
        Account newAccount = new Account(numberOfAccounts);
        user.addAccount(newAccount);
        System.out.println("Successfully created account");
        return numberOfAccounts;
    }

    public int createOverdraftAccount(int userId, int pin, int overdraft) {

        validateAccountExists(userId, pin, numberOfAccounts);
        User user = getUser(userId);
        Account newAccount = new OverdraftAccount(numberOfAccounts, overdraft);
        user.addAccount(newAccount);
        System.out.println("Successfully created user");
        return numberOfAccounts;
    }

    public int createRestrictionWithdrawAccount(int userId, int pin, int restriction) {

        ++numberOfAccounts;
        validateAccountExists(userId, pin, numberOfAccounts);
        User user = users.get(userId);
        Account newAccount = new RestrictionWithdrawAccount(numberOfAccounts, restriction);
        user.addAccount(newAccount);
        System.out.println("Successfully created user");
        return numberOfAccounts;
    }

    private void validateAccountExists(int userId, int pin, int accountNumber) {

        User user = getUser(userId);
        if (user.isAccountExists(accountNumber)) {
            throw new AccountAlreadyExistsException("Account already exists, userId has to be unique");
        }
    }

    public User getUser(int userId) {
        if (!isUserExists(userId)) {
            throw new InvalidUserIDException("Invalid user id exception");
        }
        return users.get(userId);
    }


    public void createUser(int userId, int pin) {

        if (isUserExists(userId)) {
            throw new UserAlreadyExistsException("User already exist");
        }
        User newUser = new User(userId, pin);
        users.put(userId, newUser);
        System.out.println("Successfully created user");
    }


    public void joinToYourAccount(int userId, int joinedUserId, int accountNumber) {

        User user = getUser(userId);
        Account accountToJoin = getAccount(user, accountNumber);
        User userToJoin = getUser(joinedUserId);
        userToJoin.addAccount(accountToJoin);
    }

    boolean isUserExists(int userID) {
        return users.containsKey(userID);
    }


    public Account getAccount(User user, int accountNumber) {

        Account account = user.getAccount(accountNumber);
        return account;
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        AtmSystem atmSystem = new AtmSystem(userManager);
        userManager.createUser(1, 1);
        int accountNumber = userManager.createOverdraftAccount(1, 1, 100);
        //atmSystem.depositToAccount(1, 1, accountNumber, 100);
        atmSystem.withdrawFromAccount(1, 1, accountNumber, 80);
        System.out.println(atmSystem.checkBalance(1, 1, accountNumber));

        AtmSystem atmSystemPro = new AtmSystem(userManager);
        atmSystemPro.depositToAccount(1, 1, accountNumber, 200);
        atmSystem.withdrawFromAccount(1, 1, accountNumber, 31);

        System.out.println(atmSystemPro.checkBalance(1, 1, accountNumber));

    }
}

