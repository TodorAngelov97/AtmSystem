package talentboost;

import talentboost.exception.AmountNotInRestrictionException;

public class RestrictionWithdrawAccount extends Account {

    private int limitForWithdraw;

    public RestrictionWithdrawAccount(int accountNumber, int limitForWithdraw) {

        super(accountNumber);
        this.limitForWithdraw = limitForWithdraw;
    }

    @Override
    protected boolean isBalanceEnoughForWithdraw(int amount) {
        return amount <= limitForWithdraw;
    }

}
