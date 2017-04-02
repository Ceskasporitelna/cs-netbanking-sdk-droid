package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Account balance provides information about balances for account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class AccountBalance extends WebApiEntity {

    @CsExpose
    private Amount balance;

    @CsExpose
    private Amount disposable;

    @CsExpose
    private Amount overdraft;

    /**
     * Get account balance , saved amount for saving, principal outstanding for
     * Loan/Mortgage.
     *
     * @return the account balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Get disposable balance.
     *
     * @return the disposable balance
     */
    public Amount getDisposable() {
        return disposable;
    }

    /**
     * Get overdraft amount.
     *
     * @return the overdraft amount
     */
    public Amount getOverdraft() {
        return overdraft;
    }
}
