package talentboost;

import talentboost.exception.AmountNotInRestrictionException;

public class RestrictionWithdrawAccount extends Account {

    private int limitForWithdraw;

    public RestrictionWithdrawAccount(int accountNumber, int limitForWithdraw) {

        super(accountNumber);
        this.limitForWithdraw = limitForWithdraw;
    }


    //new exception
    public int withdraw(int amount) {
        if (!isBalancePositiveAfterWithdraw(amount)) {
            throw new AmountNotInRestrictionException("Amount not in restriction");
        }
        super.withdraw(amount);
        return balance;
    }

    @Override
    protected boolean isBalancePositiveAfterWithdraw(int amount) {
        return amount <= limitForWithdraw;
    }

}
