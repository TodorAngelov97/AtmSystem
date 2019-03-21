package talentboost;

public class OverdraftAccount extends Account {

    private int overdraft;
    private static final int MINUS = -1;


    public OverdraftAccount(int accountNumber, int overdraft) {
        super(accountNumber);
        this.overdraft = overdraft;
    }


    @Override
    protected boolean isBalanceEnoughForWithdraw(int amount) {
        return balance - amount > MINUS * overdraft;
    }


}
