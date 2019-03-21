package talentboost;

public interface AtmSystemInterface {
    void withdrawFromAccount(int userId, int pin, int accountNumber, int amount);

    void checkBalance(int userId, int pin, int accountNumber);

    void changePin(int userId, int oldPin, int newPin);

    int createAccount(int userId, int pin);

    int createOverdraftAccount(int userId, int pin, int overdraft);

    int createRestrictionWithdrawAccount(int userId, int pin, int restriction);

    void joinToYourAccount(int userId, int pin, int joinedUserId, int accountNumber);
}
