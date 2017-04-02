package cz.csas.netbanking.accounts;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Main account provides information about Account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class MainAccount extends Account {

    @CsExpose
    private String alias;

    @CsExpose
    private String description;

    @CsExpose
    private Amount disposable;

    @CsExpose
    private OverdraftAmount overdraft;

    @CsExpose
    private Date overdraftDueDate;

    @CsExpose
    private List<String> flags;

    @CsExpose
    private List<SubAccount> subaccounts;

    @CsExpose
    private Double debitInterestRate;

    @CsExpose
    private Double penaltyInterestRate;

    @CsExpose
    private Loan loan;

    @CsExpose
    private Saving saving;

    @CsExpose
    private List<TransferReceiver> ownTransferReceivers;

    /**
     * Get user defined account name. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Get account description. Currently account owner name is returned.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get disposable account balance.
     *
     * @return the disposable
     */
    public Amount getDisposable() {
        return disposable;
    }

    /**
     * Get overdraft amount for current account.
     *
     * @return the overdraft
     */
    public OverdraftAmount getOverdraft() {
        return overdraft;
    }

    /**
     * Get due date of overdraft. Only for overdrafts where automatic prolongation is not set.
     *
     * @return the overdraft due date
     */
    public Date getOverdraftDueDate() {
        return overdraftDueDate;
    }

    /**
     * Get array of flags.
     * Possible flags:
     * Flag	                        Description
     * <p/>
     * accountQueryAllowed	        User may see the transaction list for this account.
     * ownTransferAllowed	        Account may be used for transfer between own accounts.
     * domesticTransferAllowed	    Account may be used for domestic payments.
     * collectiveSigning	        Indicates if orders sended from this account have to be signed
     * by at least two users.
     * internationalTransferAllowed	Account may be used for foreign payments.
     * urgentTransferAllowed	    Account may be used for urgent transfers.
     * offline	                    Account is offline (no transactions can be requested), but still
     * visible to the user. e.g. account is currently closing.
     * owner	                    Current user is owner of the account.
     * dcsAllowed	                Account is enabled for "Data Carrier Service".
     * electronicStatementAllowed	User may see the electronic statements list and download
     * statement for this account.
     * directDebitAllowed	        Direct debits are allowed for this account. You can call
     * /netbanking/cz/my/accounts/{id}/directdebits resources for this
     * account. You can setup direct debit with type DIRECT_DEBIT.
     * sipoDirectDebitAllowed	    Direct debits are allowed for this account. You can call
     * /netbanking/cz/my/accounts/{id}/directdebits resources for this
     * account. You can setup direct debit with type SIPO.
     * standingOrderNotAllowed	    Standing orders are not allowed for this account. You can call
     * /netbanking/my/accounts/{id}/standingorders resources for
     * accounts without this flag.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Get list of subaccounts related to the account.
     *
     * @return the list of subaccounts
     */
    public List<SubAccount> getSubaccounts() {
        return subaccounts;
    }

    /**
     * Get basic debit Interest rate, used for Loan and Mortgage account. Value in percentage,
     * e.g. 11,5 will be displayed as 11,5 %..
     *
     * @return the debit interest rate
     */
    public Double getDebitInterestRate() {
        return debitInterestRate;
    }

    /**
     * Get interest rate for loans which apply when repayment is delayed.
     * Value in percentage, e.g. 19,5 will be displayed as 19,5 %..
     *
     * @return the penalty interest rate
     */
    public Double getPenaltyInterestRate() {
        return penaltyInterestRate;
    }

    /**
     * Get loan related to the account.
     *
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Get saving related to the account.
     *
     * @return the saving
     */
    public Saving getSaving() {
        return saving;
    }

    /**
     * Get list of own Transfer Receivers related to the account.
     *
     * @return the list of own transfer receivers
     */
    public List<TransferReceiver> getOwnTransferReceivers() {
        return ownTransferReceivers;
    }
}
